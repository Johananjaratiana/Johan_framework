package models.interne;

import annotation_J.Url;
import etu1933.framework.view.ModelView;
import models.Personne;

public class Animaux {
   
    @Url(class_method = "Animaux-manger")
    public void manger(){}

    @Url(class_method = "Animaux-OK")
    public void OK(){}
    
    @Url(class_method = "Animaux-find_all")
    public ModelView find_all(){
        ModelView mv = new ModelView();
        Personne[] famille= new Personne[]{
            new Personne("ANDRIANAIVOSOA", "Johan"), 
            new Personne("ANDRIANAIVOSOA", "Logan"), 
            new Personne("ANDRIANAIVOSOA", "Manoa"), 
            new Personne("ANDRIANAIVOSOA", "Denis"), 
            new Personne("ANDRIAMALALA", "Anjara")
        };  
        mv.additem("famille", famille);
        mv.setView("HelloWord.jsp");
        return mv;
    }
    
}
