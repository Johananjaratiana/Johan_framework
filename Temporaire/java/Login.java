package models;

import annotation_J.Auth;
import annotation_J.Scope;
import annotation_J.Session;
import annotation_J.Url;
import etu1933.framework.view.ModelView;
import models.interne.Person;
import myConnection.MyConnection;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

@Scope(SingleTon = true)
public class Login
{
    HashMap<String, Object> sessions;

    @Url(class_method = "Login-Documentation")
    public ModelView Documentation()
    {
        ModelView modelView = new ModelView();
        modelView.setView("documentation.jsp");
        return modelView;
    }

    @Url(class_method = "Login-index")
    public ModelView index()
    {
        ModelView modelView = new ModelView();
        modelView.setView("index.jsp");
        return modelView;
    }

    @Url(class_method = "Login-log")
    public ModelView log(String name, String email, String password)
    {
        ModelView modelView = new ModelView();

        try
        {
            if(this.ConnectAsAdmin(email, password, modelView))return modelView;
            if(this.ConnectedAsClient(email, password, modelView))return modelView;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            modelView.additem("error", ex.getMessage());
        }
        
        modelView.setView("index.jsp");
        return modelView;
    }

    @Session(sessionNames = {"authentification"})
    @Url(class_method = "Login-change")
    public ModelView change()
    {
        String auth = (String) sessions.get("authentification");

        if(auth.compareTo("admin") == 0)auth = "client";
        else auth = "admin";

        ModelView modelView = new ModelView();
        modelView.addSession("authentification", auth); // Changer de valeur
        modelView.setView("accueil.jsp");
        return modelView;
    }
    @Session(sessionNames = {"temporary"})
    @Url(class_method = "Login-removeOrAddSession")
    public ModelView removeOrAddSession()
    {
        ModelView modelView = new ModelView();
        Object temporary = sessions.get("temporary");
        if(temporary == null)
        {
            modelView.addSession("temporary", "Coucou toi");
        }
        else 
        {
            List<String> rs = new ArrayList<>();
            rs.add("temporary");
            modelView.setRemoveSession(rs);
        }
        modelView.setView("accueil.jsp");
        return modelView;
    }

    @Url(class_method = "Login-logout")
    public ModelView logout()
    {
        ModelView modelView = new ModelView();
        modelView.setInvalidateSession(true);
        modelView.setView("index.jsp");
        return modelView;
    }

    private boolean ConnectAsAdmin(String email, String password, ModelView modelView)throws Exception
    {
        if(email.compareTo("johan@gmail.com") == 0 && password.compareTo("johan") == 0)
        {
            try
            {
                Person person = MyConnection.GetPersonByEmail_and_Mdp(email, password);
                if(person == null)return false;
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                throw new Exception(ex.getMessage());
            }
            modelView.addSession("isConnected", new Object());
            modelView.addSession("authentification", "admin");
            modelView.addSession("temporary", "Coucou toi");
            modelView.setView("accueil.jsp");
            return true;
        }
        return false;
    }
    
    private boolean ConnectedAsClient(String email, String password, ModelView modelView)throws Exception
    {
        if(email.compareTo("logan@gmail.com") == 0 && password.compareTo("logan") == 0)
        {
            try
            {
                Person person = MyConnection.GetPersonByEmail_and_Mdp(email, password);
                if(person == null)return false;
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                throw new Exception(ex.getMessage());
            }
            modelView.addSession("isConnected", new Object());
            modelView.addSession("authentification", "client");
            modelView.addSession("temporary", "Coucou toi");
            modelView.setView("accueil.jsp");
            return true;
        }
        return false;
    }
}