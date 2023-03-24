package models;

import annotation_J.Url;

public class Personne {
    @Url(class_method = "modele.Personne-getName")
    public String getName(){
        return "Johan";
    }

    @Url(class_method = "modele.Personne-getUserName")
    public String getUserName(){
        return "Johan";
    }
}
