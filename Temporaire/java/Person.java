package models.interne;

import annotation_J.Auth;
import annotation_J.Scope;
import annotation_J.Session;
import annotation_J.Url;
import etu1933.framework.view.ModelView;

import java.util.Date;
import java.util.HashMap;

@Scope(SingleTon = false)
public class Person
{
    HashMap<String, Object> sessions;

    String nom;

    public Person(){}

    public Person(String nom){this.nom = nom;}
    
    @Url(class_method = "Person-ReceiveData")
    @Auth(name = {"admin"})
    public ModelView ReceiveData(java.sql.Date[] dates, String null_able)
    {
        ModelView modelView = new ModelView();

        modelView.additem("SendData", dates);
        modelView.setView("admin.jsp");
        return modelView;
    }

    @Url(class_method = "Person-Client")
    @Auth(name = {"client"})
    @Session(sessionNames = {"authentification"})
    public ModelView Client()
    {
        ModelView modelView = new ModelView();

        modelView.addSession("createdSession", this.sessions.get("authentification"));
        modelView.setView("client.jsp");
        return modelView;
    }

    @Url(class_method = "Person-Both")
    @Auth(name = {"client", "admin"})
    public ModelView Both()
    {
        ModelView modelView = new ModelView();

        modelView.setView("both.jsp");
        return modelView;
    }
}
