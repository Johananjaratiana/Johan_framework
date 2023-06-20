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
    Date dateNaissance;
    String email;
    Float adresse;
    int count = 0;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getAdresse() {
        return adresse;
    }

    public void setAdresse(Float adresse) {
        this.adresse = adresse;
    }
    
    @Url(class_method = "Person-find_all")
    public ModelView find_all()
    {
        ModelView mv = new ModelView();
        String[] personne = new String[]{"Johan", "Logan"};

        mv.setJson(true);
        mv.additem("Personne", personne); // Data
        mv.setView("page1.jsp"); // Page
        return mv;
    }
    @Url(class_method = "Person-save")
    public void save(Integer your_class_number, Date dateNaissance){
        System.out.println("Class number : "+your_class_number+"\nDate sending :"+dateNaissance);
        System.out.println("Nom :" + this.nom +"\nDate de naissance : "+ this.dateNaissance +"\n"+
                "E-mail :" +this.email+ "\nAdresse :" + this.adresse);
    }
    @Url(class_method = "Person-tableau")
    @Session(sessionNames = {"user_session"})
    public void tableau(java.sql.Date[] dates, String null_able)
    {
        System.out.println(this.sessions.get("user_session"));
        System.out.println(dates);
        for (int i = 0 ;  i < dates.length ; i++)
        {
            System.out.println("Dates " + i + " : " + dates[i]);
        }
        System.out.println("null_able : " + null_able);
    }

    @Url(class_method = "Person-count")
    @Auth(name = {"admin"})
    public void count()
    {
        this.count += 1;
        System.out.println(this.count + "------------------- Normale counter -----------------");
    }

    @Url(class_method = "Person-log")
    public ModelView log(String name)
    {
        ModelView mv = new ModelView();

        if(name.compareTo("Johan") == 0)
        {
            String[] personne = new String[]{"Johan", "Logan"};

            mv.addSession("isConnected", new Object());
            mv.addSession("authentification", "admin");
            mv.addSession("user_session", "Coucou");
            mv.additem("Personne", personne);
            mv.setView("page1.jsp");
            return mv;
        }
        mv.setView("index.jsp");
        return mv;
    }
}
