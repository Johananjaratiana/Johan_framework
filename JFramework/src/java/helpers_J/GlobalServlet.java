package helpers_J;
import javax.servlet.ServletContext;

public class GlobalServlet
{
    private static ServletContext servletContext;

    public static void setServletContext(ServletContext servletContext)
    {
        GlobalServlet.servletContext = servletContext;
    }
    public static ServletContext getServletContext()
    {
        return servletContext;
    }
    // Autres méthodes/utilitaires qui utilisent l'objet ServletContext
}

