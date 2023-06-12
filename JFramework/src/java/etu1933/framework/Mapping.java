package etu1933.framework;

public class Mapping
{
    String className;
    String method;
    String authentification;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAuthentification() {return authentification;}

    public void setAuthentification(String authentification) {this.authentification = authentification;}

    public Mapping(String className, String method, String authentification)
    {
        this.setClassName(className);
        this.setMethod(method);
        this.setAuthentification(authentification);
    }
    
    
}
