package helpers_J;

import annotation_J.ParameterName;
import etu1933.framework.Mapping;
<<<<<<< Updated upstream
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Collections;
import java.util.Enumeration;
=======

>>>>>>> Stashed changes
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Enumeration;

import static helpers_J.MyCast.MyCasting;

/**
 *
 * @author johan
 */
public class Formulaire {

    public static boolean formulaire_object(Mapping mapping, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if(request.getParameterNames().hasMoreElements())
        {
            Class<?> class_temp = Class.forName(mapping.getClassName());
            Object instance = class_temp.getDeclaredConstructor().newInstance();
            Enumeration<String> parameterNames = request.getParameterNames();

            while(parameterNames.hasMoreElements())
            {
                String paramName = parameterNames.nextElement();
                MyCast.verified_casting_and_set(class_temp, paramName, instance, request.getParameter(paramName));
            }
<<<<<<< Updated upstream
            Method method = methodParamsType(class_temp, mapping.getMethod());
=======

            Method method = getMethod(class_temp, mapping.getMethod());
>>>>>>> Stashed changes
            String[] methodParamsName = methodParamsName(method);
            Object[] args = methodArgs(method.getParameterTypes(), methodParamsName, request);
            method.invoke(instance, args);
            return true;
        }
        return false;
    }
    
<<<<<<< Updated upstream
    public static void verified_casting_and_set(Class<?> classe, String paramName,Object instance, String value)throws Exception{
        Class<?> attr_class = Formulaire.hasField(classe, paramName);
        if(attr_class == null)return;
        if(isSimpleAttribute(attr_class) == false){
            Object targetObject = attr_class.newInstance();
            BeanUtils.populate(targetObject, Collections.singletonMap(paramName, value));
            BeanUtils.setProperty(instance, paramName, targetObject);
        }else{
            BeanUtils.setProperty(instance, paramName, value);
        }
    }
    
    public static boolean isSimpleAttribute(Class<?> classe_attr)throws Exception{
        if (classe_attr.equals(String.class)
                || classe_attr.equals(Integer.class) || classe_attr.equals(int.class)
                || classe_attr.equals(Long.class) || classe_attr.equals(long.class)
                || classe_attr.equals(Double.class) || classe_attr.equals(double.class)
                || classe_attr.equals(Float.class) || classe_attr.equals(float.class)
                || classe_attr.equals(Boolean.class) || classe_attr.equals(boolean.class)) {
            return true;
        }
        return false;
    }
    
    public static Class<?> hasField(Class<?> classe, String fieldName) {
        try {
            Field field = classe.getDeclaredField(fieldName);
            Class<?> ans = field.getType();
            return ans;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
    
    public static Method methodParamsType(Class<?> cls, String methodName){
        Method method = null;
        for (Method m : cls.getDeclaredMethods()) {
            if (m.getName().equals(methodName)) {
=======
    private static Method getMethod(Class<?> cls, String methodName)
    {
        for (Method m : cls.getDeclaredMethods())
        {
            if (m.getName().equals(methodName))
            {
>>>>>>> Stashed changes
                return m;
            }
        }
        return null;
    }
    
<<<<<<< Updated upstream
    public static String[] methodParamsName(Method method){
        if(method.isAnnotationPresent(ParameterName.class)){
            ParameterName myAnnotation = method.getAnnotation(ParameterName.class);
            String all_param = myAnnotation.paramsName();
            return all_param.split("-");
        }
        return new String[0];
=======
    private static String[] methodParamsName(Method method)
    {
        try {
            return ArgumentNamesExtractor.getArgumentNames(method);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
>>>>>>> Stashed changes
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
