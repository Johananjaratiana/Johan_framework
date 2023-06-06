package helpers_J;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class MyCast
{
    public static void verified_casting_and_set(Class<?> classe, String paramName, Object instance, String value)throws Exception
    {
        Class<?> attr_class = hasField(classe, paramName);
        if(attr_class == null)return;

        if(isSimpleAttribute(attr_class) == false)
        {
            Object targetObject = attr_class.getDeclaredConstructor().newInstance();
            BeanUtils.populate(targetObject, Collections.singletonMap(paramName, value));
            BeanUtils.setProperty(instance, paramName, targetObject);
        }
        else
        {
            BeanUtils.setProperty(instance, paramName, value);
        }
    }
    static <T> T MyCasting(Class<T> targetType, String value) throws Exception
    {
        Class<?> casting_type_object = getClassType(targetType);
        if(casting_type_object == String.class)
        {
            return targetType.cast(value);
        }
        else if (isSimpleAttribute(casting_type_object) == true)
        {
            Method valueOfMethod = casting_type_object.getMethod("valueOf", String.class);
            return targetType.cast(valueOfMethod.invoke(null, value));
        }
        else
        {
            return targetType.cast(parseDate(targetType, value, "yyyy-MM-dd"));
        }
    }
    private  static <T> T parseDate(Class<T> targetType, String dateString, String format) throws Exception
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        if(targetType.equals(LocalDate.class))return targetType.cast(LocalDate.parse(dateString, formatter));
        else if(targetType.equals(LocalTime.class))return targetType.cast(LocalTime.parse(dateString, formatter));
        else if(targetType.equals(LocalDateTime.class))return targetType.cast(LocalDateTime.parse(dateString, formatter));

        SimpleDateFormat formatter2 = new SimpleDateFormat(format);
        java.util.Date utilDate = formatter2.parse(dateString);

        if(targetType.equals(java.sql.Date.class))return targetType.cast(new java.sql.Date(utilDate.getTime()));
        else if (targetType.equals(java.util.Date.class))return targetType.cast(utilDate);

        throw new IllegalArgumentException("UNsupported target type : " + targetType);
    }
    private static Class<?> getClassType(Class<?> targetType)
    {
        if(targetType.equals(int.class))return Integer.class;
        if(targetType.equals(double.class))return Double.class;
        if(targetType.equals(float.class))return Float.class;
        if(targetType.equals(boolean.class))return Boolean.class;
        if(targetType.equals(long.class))return Long.class;
        return targetType;
    }
    public static boolean isSimpleAttribute(Class<?> classe_attr)
    {
        Class<?> componentType;

        if (classe_attr.isArray()) componentType = classe_attr.getComponentType();
        else componentType = classe_attr;

        if (componentType.equals(String.class)
                || componentType.equals(Integer.class) || componentType.equals(int.class)
                || componentType.equals(Long.class) || componentType.equals(long.class)
                || componentType.equals(Double.class) || componentType.equals(double.class)
                || componentType.equals(Float.class) || componentType.equals(float.class)
                || componentType.equals(Boolean.class) || componentType.equals(boolean.class))
        {
            return true;
        }
        return false;
    }
    private static Class<?> hasField(Class<?> classe, String fieldName)
    {
        try
        {
            Field field = classe.getDeclaredField(fieldName);
            Class<?> ans = field.getType();
            return ans;
        }
        catch (NoSuchFieldException e)
        {
            return null;
        }
    }
}
