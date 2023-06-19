package etu1933.framework;

public class Mapping
{
    String className;
    String method;
    String[] authentification;

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

    public String[] getAuthentification() {
        return authentification;
    }

    public void setAuthentification(String[] authentification) {
        this.authentification = authentification;
    }

    public Mapping(String className, String method, String[] authentification)
    {
        this.setClassName(className);
        this.setMethod(method);
        this.setAuthentification(authentification);
    }

    public boolean CanAccess(String require_auth)
    {
        if(require_auth == null || this.getAuthentification() == null || this.getAuthentification().length == 0)return true;

        for (String auth: this.getAuthentification())
        {
            if(auth.compareTo(require_auth) == 0)return true;
        }

        return false;
    }
    
    
}
