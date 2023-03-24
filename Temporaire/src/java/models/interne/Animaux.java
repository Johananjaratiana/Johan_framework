package models.interne;

import annotation_J.Url;
public class Animaux {
   
    @Url(class_method = "Animaux-manger")
    public void manger(){}

    @Url(class_method = "Animaux-OK")
    public void OK(){}
}
