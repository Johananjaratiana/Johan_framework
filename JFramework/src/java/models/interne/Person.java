package models.interne;

import annotation_J.Scope;
import annotation_J.Url;
import etu1933.framework.view.ModelView;
import java.util.Date;

@Scope(SingleTon = false)
public class Person
{
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
    public void tableau(java.sql.Date[] nom, String null_able)
    {
        for (int i = 0 ;  i < nom.length ; i++)
        {
            System.out.println("Nom " + i + " : " + nom[i]);
        }
        System.out.println("null_able : " + null_able);
    }

    @Url(class_method = "Person-count")
    public void count()
    {
        this.count += 1;
        System.out.println(this.count + "------------------- Normale counter -----------------");
    }
    
}
