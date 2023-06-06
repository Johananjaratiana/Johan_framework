package helpers_J;

import annotation_J.Url;
import etu1933.framework.Mapping;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

<<<<<<< Updated upstream
import annotation_J.*;
import etu1933.framework.Mapping;

=======
>>>>>>> Stashed changes
public class Init {
    
    public void setUrl(HashMap<String, Mapping> toComplete,String package_anterieur,  String package_name, String path_classes) throws ClassNotFoundException
    {
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
    public void make_full(HashMap<String, Mapping> toComplete,File file,String package_anterieur, String package_name) throws ClassNotFoundException
    {
        // Charger la classe
        String className = file.getName().replace(".class", "");
        String class_package = "";
        Class<?> loadedClass = null;
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

        // Inspecter les annotations de chaque méthode
        Method[] methods = loadedClass.getDeclaredMethods();
        for (Method method : methods) 
        {
            if(method.isAnnotationPresent(Url.class))
            {
                Url annotation = method.getAnnotation(Url.class);
                String url = annotation.class_method();
                Mapping mapping = new Mapping(class_package, method.getName());
                toComplete.put(url,mapping);
            }
        }
    }
}
