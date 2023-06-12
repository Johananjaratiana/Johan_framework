package etu1933.framework;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Singleton
{
    public static Object getOrInitSingleton(HashMap<String, Object> Singletons, String classPackage)throws Exception
    {
        try
        {
            if (!Singletons.containsKey(classPackage)) return null;

            Object instance = Singletons.get(classPackage);

            if (instance == null)
            {
                Class<?> clazz = Class.forName(classPackage);
                instance = clazz.getDeclaredConstructor().newInstance();
                Singletons.put(classPackage, instance);
            }
            initializeDefaults(instance);
            return instance;
        }
        catch (Exception ex)
        {
            throw new Exception(ex.getMessage());
        }
    }
    public static void initializeDefaults(Object instance)throws Exception
    {
        try {
            Class<?> clazz = instance.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields)
            {
                field.setAccessible(true);

                Object defaultValue = getDefaultValue(field.getType());
                field.set(instance, defaultValue);
            }
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    private static Object getDefaultValue(Class<?> fieldType)
    {
        if (fieldType == byte.class) {
            return (byte) 0;
        } else if (fieldType == short.class) {
            return (short) 0;
        } else if (fieldType == int.class) {
            return 0;
        } else if (fieldType == long.class) {
            return 0L;
        } else if (fieldType == float.class) {
            return 0.0f;
        } else if (fieldType == double.class) {
            return 0.0;
        } else if (fieldType == char.class) {
            return '\u0000';
        } else if (fieldType == boolean.class) {
            return false;
        } else {
            return null;
        }
    }

}
