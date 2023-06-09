package helpers_J;

import annotation_J.Scope;
import annotation_J.Url;
import etu1933.framework.Mapping;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Init {
    
    public static void setUrl_and_Singleton(HashMap<String, Mapping> mappingUrls,HashMap<String, Object> singletons, String package_anterieur,  String package_name, String path_classes) throws Exception
    {
        String pathToClasses = path_classes;
        if(package_name!=null) pathToClasses += "/"+package_name;

        File directory = new File(pathToClasses);
        String package2 ;

        // Parcourir tous les fichiers de classe dans le répertoire courant
        for (File file : directory.listFiles())
        {
            if(file.isDirectory())
            {
                if(package_anterieur == null)package2 = package_name;
                else package2 = package_anterieur+"."+package_name;
                setUrl_and_Singleton(mappingUrls,singletons, package2,file.getName(), pathToClasses);
            }
            else if (file.getName().endsWith(".class"))
            {
                try {
                    make_full(mappingUrls, singletons, file, package_anterieur, package_name);
                }catch (Exception ex){
                    throw new Exception(ex.getMessage());
                }
            }
        }
    }
    private static void make_full(HashMap<String, Mapping> mappingUrls,HashMap<String, Object> singletons,File file,String package_anterieur, String package_name) throws Exception
    {
        // Charger la classe
        String className = file.getName().replace(".class", "");
        Class<?> loadedClass = null;

        try{
            loadedClass = LoadedClass_and_package(package_anterieur, package_name, className);

            // Inspecter les annotations de chaque méthode
            Url_completion(loadedClass, mappingUrls);

            // Inspecter les annotations des classe singleton
            Singleton_completion(loadedClass,singletons);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }
    private static Class<?> LoadedClass_and_package(String package_anterieur, String package_name, String className) throws ClassNotFoundException {
        Class<?> loadedClass;
        String class_package = "";
        if(package_anterieur != null && package_name != null)
        {
            class_package = package_anterieur+"."+package_name+"."+className;
            loadedClass = Class.forName(class_package);
        }
        else if (package_name != null)
        {
            class_package = package_name+"."+className;
            loadedClass = Class.forName(class_package);
        }
        else
        {
            class_package = className;
            loadedClass = Class.forName(class_package);
        }
        return loadedClass;
    }
    private static void Singleton_completion(Class<?> loadedClass, HashMap<String, Object> singletons)
    {
        if(loadedClass.isAnnotationPresent(Scope.class))
        {
            Scope annotation_s = loadedClass.getAnnotation(Scope.class);
            if(annotation_s.SingleTon() == true)
            {
                singletons.put(loadedClass.getName(), null);
            }
        }
    }
    private static void Url_completion(Class<?> loadedClass, HashMap<String, Mapping> mappingUrls)
    {
        Method[] methods = loadedClass.getDeclaredMethods();
        for (Method method : methods)
        {
            if(method.isAnnotationPresent(Url.class))
            {
                Url annotation = method.getAnnotation(Url.class);
                String url = annotation.class_method();
                Mapping mapping = new Mapping(loadedClass.getName(), method.getName());
                mappingUrls.put(url,mapping);
            }
        }
    }
}
