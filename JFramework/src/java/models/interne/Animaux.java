package models.interne;

import annotation_J.Url;
import etu1933.framework.view.ModelView;

public class Animaux {
   
    @Url(class_method = "Animaux-manger")
    public void manger(){}

    @Url(class_method = "Animaux-OK")
    public void OK(){}
    
    @Url(class_method = "Animaux-find_all")
    public ModelView find_all(){
        ModelView mv = new ModelView();
        String[] personne = new String[]{"Johan", "Logan"};
        
        mv.additem("Personne", personne); // Data
        mv.setView("page1.jsp"); // Page
        return mv;
    }
    
}
