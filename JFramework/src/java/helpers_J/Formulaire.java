package helpers_J;

import etu1933.framework.Mapping;
import etu1933.framework.Singleton;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;

import static helpers_J.MyCast.MyCasting;

/**
 *
 * @author johan
 */
public class Formulaire
{

    public static boolean formulaire_object(Mapping mapping, HashMap<String,Object> Singletons, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if(request.getParameterNames().hasMoreElements())
        {
            Class<?> class_temp = Class.forName(mapping.getClassName());
            Object instance = Singleton.getOrInitSingleton(Singletons,  class_temp.getName());
            if(instance == null)instance = class_temp.getDeclaredConstructor().newInstance();
            Enumeration<String> parameterNames = request.getParameterNames();

            while(parameterNames.hasMoreElements())
            {
                String paramName = parameterNames.nextElement();
                MyCast.verified_casting_and_set(class_temp, paramName, instance, request.getParameter(paramName));
            }

            Method method = getMethod(class_temp, mapping.getMethod());
            String[] methodParamsName = methodParamsName(method);
            Object[] args = methodArgs(method.getParameterTypes(), methodParamsName, request);
            method.invoke(instance, args);
            return true;
        }
        return false;
    }
    
    private static Method getMethod(Class<?> cls, String methodName)
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
    
    private static String[] methodParamsName(Method method)
    {
        try {
            return ArgumentNamesExtractor.getArgumentNames(method);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object[] methodArgs(Class<?>[] paramsTypes, String[] paramsName, HttpServletRequest request) throws Exception
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
                else ans[i] = MyCasting(paramsTypes[i], value);
            }
        }
        return ans;
    }
}
