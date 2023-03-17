package helpers_J;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

import annotation_J.*;
import etu1933.framework.Mapping;

public class Init {
    
       public void setUrl(HashMap<String, Mapping> toComplete,String package_anterieur,  String package_name, String path_classes) throws ClassNotFoundException {
        String pathToClasses = path_classes;
        if(package_name!=null) pathToClasses += "/"+package_name;

        File directory = new File(pathToClasses);
        String package2 ;

        // Parcourir tous les fichiers de classe dans le répertoire courant
        for (File file : directory.listFiles()) {
            if(file.isDirectory()){
                if(package_anterieur == null)package2 = package_name;
                else package2 = package_anterieur+"."+package_name;
                this.setUrl(toComplete, package2,file.getName(), pathToClasses);
            }
            else if (file.getName().endsWith(".class"))
            {
                this.make_full(toComplete,file,package_anterieur,package_name);
            }
        }
    }
    public void make_full(HashMap<String, Mapping> toComplete,File file,String package_anterieur, String package_name) throws ClassNotFoundException {
        // Charger la classe
        String className = file.getName().replace(".class", "");
        Class<?> loadedClass = null;
        if(package_anterieur != null && package_name != null)
        {
            loadedClass = Class.forName(package_anterieur+"."+package_name+"."+className);
        }
        else if (package_name != null)
        {
            loadedClass = Class.forName(package_name+"."+className);
        }
        else loadedClass = Class.forName(className);

        // Inspecter les annotations de chaque méthode
        Method[] methods = loadedClass.getDeclaredMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(Url.class)){
                Url annotation = method.getAnnotation(Url.class);
                String url = annotation.class_method();
                Mapping mapping = new Mapping(className, method.getName());
                toComplete.put(url,mapping);
            }
        }
    }
}
