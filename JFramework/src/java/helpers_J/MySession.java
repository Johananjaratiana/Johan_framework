package helpers_J;

import annotation_J.Session;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MySession
{
    public static void NewSession(HashMap<String, Object> sessions , HttpServletRequest request)
    {
        if(sessions == null || sessions.size() == 0)return;

        sessions.forEach((key, value)->{
            request.getSession().setAttribute(key, value);
        });
    }
    public static void SetRequiredSession(String requiredSessionName, Object instance, Method method,HttpServletRequest request)throws Exception
    {
        try
        {
            String[] sessionNames = GetRequiredSessionName(method);

            if(sessionNames == null || sessionNames.length == 0)return;

            HashMap<String, Object> sessions = new HashMap<>();

            for (int i = 0; i < sessionNames.length; i++)
            {
                sessions.put(sessionNames[i], request.getSession().getAttribute(sessionNames[i]));
            }
            MySession.setAttributeValue(instance, requiredSessionName, sessions);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception("Session name not found , "+ex.getMessage());
        }
    }
    private static String[] GetRequiredSessionName(Method method)
    {
        if(method.isAnnotationPresent(Session.class))
        {
            Session session = method.getAnnotation(Session.class);
            return session.sessionNames();
        }
        return null;
    }
    private static void setAttributeValue(Object instance, String attributeName, HashMap<String, Object> value) throws Exception
    {
        try
        {
            Field field = instance.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);
            field.set(instance, value);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }
}
