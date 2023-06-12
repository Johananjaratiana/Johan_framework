package models.interne;

import annotation_J.Url;
import etu1933.framework.view.ModelView;
import models.interne.Person;

public class MyModel 
{
   
    @Url(class_method = "MyModel-manger")
    public void manger(){}

    @Url(class_method = "MyModel-OK")
    public void OK(){}
    
    @Url(class_method = "MyModel-find_all")
    public ModelView find_all(){
        ModelView mv = new ModelView();
        Object[] famille = new Object[]{
            new Person("ANDRIANAIVOSOA Johan"), 
            new Person("ANDRIANAIVOSOA Logan"), 
            new Person("ANDRIANAIVOSOA Manoa"), 
            new Person("ANDRIANAIVOSOA Denis"), 
            new Person("ANDRIAMALALA Anjara")
        };  
        mv.additem("famille", famille);
        mv.setView("HelloWord.jsp");
        return mv;
    }
}
