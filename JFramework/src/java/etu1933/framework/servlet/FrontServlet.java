package etu1933.framework.servlet;

import etu1933.framework.Mapping;
import etu1933.framework.file.FileUpload;
import etu1933.framework.view.ModelView;
import helpers_J.Authentification;
import helpers_J.MethodLoader;
import helpers_J.Init;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/MyServlet")
@MultipartConfig(
        maxFileSize = 5242880, // Taille maximale des fichiers en octets
        maxRequestSize = 10485760, // Taille maximale de la requête en octets
        fileSizeThreshold = 0 // Taille à partir de laquelle les fichiers seront stockés sur le disque
)
public class FrontServlet extends HttpServlet
{
    HashMap<String,Mapping> MappingUrls;
    HashMap<String,Object> Singletons;
    HashMap<String,String> InitParam;
    HashMap<String,String> Auth_Session;
    HashMap<String,Object> Sessions;
    ArrayList<FileUpload> fileUploads;

    public  HashMap<String,Object> getSessions()
    {
        return this.Sessions;
    }
    private void CheckAuthentification(Mapping mapping)throws Exception
    {
        if(mapping == null)return;
//        String isConnected = getServletContext().getInitParameter("isConnected");
//        String status = Auth_Session.get(isConnected);
//        if(status == null)
//        {
//            throw new Exception("Your are not connected !");
//        }

        String require_auth = mapping.getAuthentification();

        if(require_auth == null || require_auth.compareTo("") == 0)return;

        String user_auth = Auth_Session.get("auth_session");

        if(require_auth.compareTo(user_auth) == 0)
        {
            throw new Exception("Authentification error");
        }
    }

    private Mapping getMapping(HttpServletRequest request) throws Exception
    {
        try
        {
            String servlet_path = request.getServletPath();
            String valeur = "";
            if(servlet_path != null)
            {
                String[] elements = servlet_path.split("/"); // Ca debute avec 1
                valeur = elements[1];
                Mapping mapping = this.MappingUrls.get(valeur);
                return mapping;
            }
            return null;
        }
        catch(Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
    public boolean TryModelView(Mapping mapping,HttpServletRequest request, HttpServletResponse response)throws Exception
    {
        try
        {
            ModelView modelview = MethodLoader.load_function(mapping, this.Singletons,request, response);

            if(modelview == null)return false;

            modelview.sendData(request, response);// Envoie du data
            String page = modelview.getView();
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.include(request, response);

            return true;
        }
        catch(Exception ex)
        {
            throw new Exception(ex.getMessage());
        }
    }
    private void MyDispatcher(Mapping mapping, HttpServletRequest request, HttpServletResponse response)throws Exception
    {
        if(mapping == null)
        {
            String controller = null;
            try
            {
                String servlet_path = request.getServletPath();
                String[] elements = servlet_path.split("/"); // Ca debute avec 1
                controller = elements[1];
                RequestDispatcher dsp = request.getRequestDispatcher(controller);
                dsp.forward(request, response);
            }
            catch(Exception e)
            {
                throw new Exception(controller);
            }
        }
    }
    private void UseDefaultController(HttpServletRequest request, HttpServletResponse response)throws Exception
    {
        String DefaultController = InitParam.get("default_controller");
        RequestDispatcher dispatcher = request.getRequestDispatcher(DefaultController);
        try
        {
            dispatcher.include(request, response);
        }
        catch (Exception ex)
        {
            throw new Exception(ex.getMessage());
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws IOException, ClassNotFoundException {
        try
        {
            FileUpload.AddFileUpload(this.fileUploads, request, response);

            Mapping mapping = this.getMapping(request);

            MyDispatcher(mapping, request, response);

            CheckAuthentification(mapping);

            if(!TryModelView(mapping, request, response))
            {
                UseDefaultController(request, response);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            this.error(ex, request,response);
        }
    }
    private void setInitParam()
    {
        if(InitParam == null)InitParam = new HashMap<>();
        InitParam.put("package_name",  getServletConfig().getInitParameter("package_name"));
        InitParam.put("auth_session",  getServletConfig().getInitParameter("auth_session"));
        InitParam.put("isConnected",  getServletConfig().getInitParameter("isConnected"));
        InitParam.put("default_controller",  getServletConfig().getInitParameter("default_controller"));
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String path = getServletContext().getRealPath("/WEB-INF/classes");
        this.setInitParam();
        String package_name = InitParam.get("package_name");
        String auth_session = InitParam.get("auth_session");
        String isConnected = InitParam.get("isConnected");
        ServletContext servletContext = getServletContext();
        try
        {
            this.MappingUrls = new HashMap<>();
            this.Singletons = new HashMap<>();
            this.Auth_Session = new HashMap<>();
            this.Sessions = new HashMap<>();

            Init.setUrl_and_Singleton(this.MappingUrls,this.Singletons,null, package_name, path);
            Authentification.InitSession_and_authentification(this.Auth_Session, isConnected, auth_session);
            servletContext.setAttribute(auth_session, this.Auth_Session);
            servletContext.setAttribute("session", this.Sessions);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException
    {
        try
        {
            processRequest(request, response);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException
    {
        try
        {
            processRequest(request, response);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void error(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Error : " + ex.getMessage() + "</h1>");
            out.println("<p><u>Request URI:</u></p>");
            out.println("<h3>" + request.getRequestURI()+ "</h3>");
            out.println("<p><u>Method:</u></p>");
            out.println("<h3>" + request.getMethod()+ "</h3>");
            out.println("<p><u>Context path:</u></p>");
            out.println("<h3>" + request.getContextPath() + "</h3>");
            out.println("<p><u>Servlet path:</u></p>");
            out.println("<h3>" + request.getServletPath()+ "</h3>");
            out.println("<p><u>Map:</u></p>");
            Map<String, String[]> paramMap = request.getParameterMap();
            for (String paramName : paramMap.keySet())
            {
                String[] paramValues = paramMap.get(paramName);
                for (String paramValue : paramValues)
                {
                    out.println("<h3>" + paramName + " = " + paramValue+ "</h3>");
                }
            }
            out.println("<p><u>MappingUrls:</u></p>");
            out.println( "<h1>"+this.MappingUrls+"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
