package etu1933.framework;

import java.util.HashMap;

public class Singleton
{
    public static Object getOrInitSingleton(HashMap<String, Object> Singletons, String classPackage) throws Exception {
        if (!Singletons.containsKey(classPackage))return null;

        Object instance = Singletons.get(classPackage);

        if (instance == null)
        {
            try
            {
                Class<?> clazz = Class.forName(classPackage);
                instance = clazz.getDeclaredConstructor().newInstance();
                Singletons.put(classPackage, instance);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.getMessage());
            }
        }
        return instance;
    }
}
