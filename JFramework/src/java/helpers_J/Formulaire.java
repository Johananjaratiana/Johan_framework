/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helpers_J;

import etu1933.framework.Mapping;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author johan
 */
public class Formulaire {
//        Si il y a des valeurs des inputs
//        Instanciation de la classe
//        Si le name de chaque input est une attribut de la classe appeler la fonction set
//        Appelle de la fonction par cette objet instancie
    
    public static boolean formulaire_object(Mapping mapping, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(request.getParameterNames().hasMoreElements()) {
            Class<?> class_temp = Class.forName(mapping.getClassName());
            Object instance = class_temp.newInstance();
            Enumeration<String> parameterNames = request.getParameterNames();
            while(parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                Formulaire.verified_casting_and_set(class_temp, paramName, instance, request.getParameter(paramName));
            }
            Method method = class_temp.getDeclaredMethod(mapping.getMethod());
            method.invoke(instance);
            return true;
        }
        return false;
    }
    
    public static void verified_casting_and_set(Class<?> classe, String paramName,Object instance, String value)throws Exception{
        if(!Formulaire.hasField(classe, paramName))return;
        Field field = instance.getClass().getDeclaredField(paramName);
        field.setAccessible(true);
        Formulaire.casting_and_set(field, instance, paramName, value);
    }
    
    public static void casting_and_set(Field field, Object instance,String paramName,String value)throws Exception{
        if (field.getType().equals(String.class)) {
            field.set(instance, value);
        } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
            field.set(instance, Integer.parseInt(value));
        } else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
            field.set(instance, Long.parseLong(value));
        } else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
            field.set(instance, Double.parseDouble(value));
        } else if (field.getType().equals(Float.class) || field.getType().equals(float.class)) {
            field.set(instance, Float.parseFloat(value));
        } else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
            field.set(instance, Boolean.parseBoolean(value));
        } else if (field.getType().equals(Date.class)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(value);
            field.set(instance, date);
        } else if (field.getType().equals(LocalDate.class)){
            LocalDate localDate = LocalDate.parse(value);
            field.set(instance, localDate);
        }
    }
    public static boolean hasField(Class<?> clazz, String fieldName) {
        try {
            clazz.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }
}
