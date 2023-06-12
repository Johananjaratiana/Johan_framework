package helpers_J;

import annotation_J.Auth;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Authentification
{
    public static void InitSession_and_authentification(HashMap<String, String> Auth_Session, String auth_session, String isConnected)
    {
        if(Auth_Session == null)Auth_Session = new HashMap<>();
        Auth_Session.put(isConnected, null);
        Auth_Session.put(auth_session, null);
    }
    public static String GetAuthentification(Method method)
    {
        if(method.isAnnotationPresent(Auth.class))
        {
            Auth authentification = method.getAnnotation(Auth.class);
            return authentification.name();
        }
        return null;
    }
}
