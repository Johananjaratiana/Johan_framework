package models;

import annotation_J.Auth;
import annotation_J.Scope;
import annotation_J.Session;
import annotation_J.Url;
import etu1933.framework.view.ModelView;

@Scope(SingleTon = true)
public class Login
{
    @Url(class_method = "Login-log")
    public ModelView log(String name)
    {
        ModelView modelView = new ModelView();

        if(this.ConnectAsAdmin(name, modelView))return modelView;
        if(this.ConnectedAsClient(name, modelView))return modelView;
        
        modelView.setView("index.jsp");
        return modelView;
    }

    private boolean ConnectAsAdmin(String name, ModelView modelView)
    {
        if(name.compareTo("Johan") == 0)
        {
            modelView.addSession("isConnected", new Object());
            modelView.addSession("authentification", "admin");
            modelView.setView("accueil.jsp");
            return true;
        }
        return false;
    }
    
    private boolean ConnectedAsClient(String name, ModelView modelView)
    {
        if(name.compareTo("Logan") == 0)
        {
            modelView.addSession("isConnected", new Object());
            modelView.addSession("authentification", "client");
            modelView.setView("accueil.jsp");
            return true;
        }
        return false;
    }
}