package models;

import annotation_J.Url;
import etu1933.framework.view.ModelView;
import java.util.Date;

public class Person {
    String nom;
    Date dateNaissance;
    String email;
    String adresse;

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Person(String nom) {
        this.nom = nom;
    }
    
    public Person() {
    }
    
    
    @Url(class_method = "Person-manger")
    public void manger(){}

    @Url(class_method = "Person-OK")
    public void OK(){}
    
    @Url(class_method = "Person-save")
    public void save(double classNumber, Date dateNaissance){
        System.out.println("Class number : "+classNumber+"\nDate sending :"+dateNaissance);
        System.out.println("Nom :" + this.nom +"\nDate de naissance :"+ this.dateNaissance +"\n"+
                "E-mail :" +this.email+ "\nAdresse :" + this.adresse);
    }
}