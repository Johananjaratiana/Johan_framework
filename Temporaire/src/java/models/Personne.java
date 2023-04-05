package models;

import annotation_J.Url;

public class Personne {
    String name;    
    String userName;
    
    
    @Url(class_method = "modele.Personne-getName")
    public String getName(){
        return this.name;
    }

    @Url(class_method = "modele.Personne-getUserName")
    public String getUserName(){
        return this.userName;
    }

    public Personne(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

  
    
    
}
