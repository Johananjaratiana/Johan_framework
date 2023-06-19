package helpers_J;

import annotation_J.Auth;
import etu1933.framework.Mapping;

import java.lang.reflect.Method;
import java.util.HashMap;

public class Authentification
{
    public static String[] GetAuthentification(Method method)
    {
        if(method.isAnnotationPresent(Auth.class))
        {
            Auth authentification = method.getAnnotation(Auth.class);
            return authentification.name();
        }
        return null;
    }
    public static String[] GetAuthentification(Mapping mapping)throws Exception
    {
        try
        {
            Class<?> class_temp = Class.forName(mapping.getClassName());
            Method method = MethodLoader.getMethod(class_temp, mapping.getMethod());
            if(method.isAnnotationPresent(Auth.class))
            {
                Auth authentification = method.getAnnotation(Auth.class);
                return authentification.name();
            }
            return null;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }
}
