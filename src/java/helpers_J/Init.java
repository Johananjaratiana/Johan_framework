package helpers_J;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

import annotation_J.*;
import etu1933.framework.Mapping;

public class Init {

    public static void setUrl(HashMap<String, Mapping> toComplete,  String package_name, String path_classes) throws ClassNotFoundException {
        String pathToClasses = path_classes;
        if(package_name!=null) pathToClasses += "/"+package_name;

        File directory = new File(pathToClasses);

        // Parcourir tous les fichiers de classe dans le répertoire courant
        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".class")) {

                // Charger la classe
                String className = file.getName().replace(".class", "");
                Class<?> loadedClass = null;
                if (package_name != null)loadedClass = Class.forName(package_name+"."+className);
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
    }
    public void show_all_annotation(String package_name,String path_classes) throws ClassNotFoundException {
        String pathToClasses = path_classes+package_name;
        File directory = new File(pathToClasses);

        // Parcourir tous les fichiers de classe dans le répertoire courant
        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".class")) {

                // Charger la classe
                String className = file.getName().replace(".class", "");
                Class<?> loadedClass = Class.forName(className);

                // Inspecter les annotations de chaque méthode
                Method[] methods = loadedClass.getDeclaredMethods();
                for (Method method : methods) {
                    Annotation[] annotations = method.getDeclaredAnnotations();
                    for (Annotation annotation : annotations) {
                        // Faire quelque chose avec chaque annotation trouvée
                        System.out.println("Annotation " + annotation.annotationType().getSimpleName()
                                + " trouvée sur la méthode " + method.getName() + " de la classe " + className);
                    }
                }
            }
        }
    }
}
