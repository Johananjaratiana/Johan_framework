package helpers_J;

import etu1933.framework.Mapping;
import etu1933.framework.Singleton;
import etu1933.framework.view.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;
import java.util.HashMap;
import helpers_J.MyCast;

/**
 *
 * @author johan
 */
public class MethodLoader
{
    public static ModelView load_function(String user_session_name,Mapping mapping, HashMap<String,Object> Singletons, HttpServletRequest request, HttpServletResponse response)throws Exception
    {
        if(mapping == null)return null;

        try
        {
            Class<?> class_temp = Class.forName(mapping.getClassName());
            Method method = getMethod(class_temp, mapping.getMethod());
            Object instance = Singleton.getOrInitSingleton(Singletons, class_temp.getName());

            // Chaque classe devrait avoir une constructeur vide
            if(instance == null)instance = class_temp.getDeclaredConstructor().newInstance();

            // Les classes qui ont besion de session doivent creer une HashMap<String, Object> sessionname :
            MySession.SetRequiredSession(user_session_name, instance, method,request);

            Object[] args = null;
            ModelView modelview = null;

            if (request.getParameterNames().hasMoreElements())
            {
                if (instance == null) instance = class_temp.getDeclaredConstructor().newInstance();
                Enumeration<String> parameterNames = request.getParameterNames();

                while (parameterNames.hasMoreElements())
                {
                    String paramName = parameterNames.nextElement();
                    MyCast.verified_casting_and_set(class_temp, paramName, instance, request.getParameter(paramName));
                }

                String[] methodParamsName = methodParamsName(method);
                args = methodArgs(method.getParameterTypes(), methodParamsName, request);
                modelview = (ModelView) method.invoke(instance, args);
            }
            else
            {
                modelview = (ModelView) method.invoke(instance);
            }
            return modelview;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }
    
    public static Method getMethod(Class<?> cls, String methodName)
    {
        for (Method m : cls.getDeclaredMethods())
        {
            if (m.getName().equals(methodName))
            {
                return m;
            }
        }
        return null;
    }
    
    private static String[] methodParamsName(Method method)throws Exception
    {
        try
        {
            Parameter[] parameters = method.getParameters();
            String[] ans = new String[parameters.length];
            for (int i = 0 ; i < parameters.length ; i++)
            {
                ans[i] = parameters[i].getName();
            }
            return ans;
//            return ArgumentNamesExtractor.getArgumentNames(method);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    private static Object[] methodArgs(Class<?>[] paramsTypes, String[] paramsName, HttpServletRequest request)throws Exception
    {
        Object[] ans = new Object[paramsTypes.length];
        String value;
        Object targetObject = null;
        for(int i = 0  ; i < paramsTypes.length ; i++)
        {
            if (paramsTypes[i].isArray())
            {
                String[] parameterValues = request.getParameterValues(paramsName[i] + "[]");
                if(parameterValues == null)ans[i] = null;
                else
                {
                    Class<?> componentType = paramsTypes[i].getComponentType();
                    Object array = Array.newInstance(componentType, parameterValues.length);
                    for (int j = 0; j < parameterValues.length; j++)
                    {
                        targetObject = MyCast.MyCasting(componentType, parameterValues[j]);
                        Array.set(array, j, targetObject);
                    }
                    ans[i] = array;
                }
            }
            else
            {
                value = request.getParameter(paramsName[i]);
                if(value == null)ans[i] = null;
                else
                {
                    try
                    {
                        ans[i] = MyCast.MyCasting(paramsTypes[i], value);
                    }
                    catch (Exception e)
                    {
                        throw new Exception(e.getMessage());
                    }
                }
            }
        }
        return ans;
    }
}
