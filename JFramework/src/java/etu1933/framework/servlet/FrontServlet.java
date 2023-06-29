package etu1933.framework.servlet;

import com.google.gson.Gson;
import etu1933.framework.Mapping;
import etu1933.framework.file.FileUpload;
import etu1933.framework.view.ModelView;
import helpers_J.Authentification;
import helpers_J.MethodLoader;
import helpers_J.Init;
import helpers_J.MySession;

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
    boolean isJson = false;
    HashMap<String,Mapping> MappingUrls;
    HashMap<String,Object> Singletons;
    HashMap<String,String> InitParam;
    ArrayList<FileUpload> fileUploads;

    private void CheckAuthentification(Mapping mapping, HttpServletRequest request)throws Exception
    {
        if(mapping == null)return;

        String user_auth = (String) request.getSession().getAttribute(this.InitParam.get("auth_session"));

        if(mapping.CanAccess(user_auth) == false)
        {
            throw new Exception("Method`s authetification ! error !");
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
            String user_session_name = this.InitParam.get("session_name");
            ModelView modelview = MethodLoader.load_function(user_session_name, mapping, this.Singletons,request, response);

            if(modelview == null)return false;

            if(modelview.isJson() == true)
            {
                LoadJSON(modelview.getData(), response);
                this.isJson = true;
                return false;
            }

            MySession.NewSession(modelview.getSession(), request);      // Ajout de session
            modelview.sendData(request);                                // Envoie du data
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

    private void LoadJSON(Object dataObject, HttpServletResponse response)throws Exception
    {
        Gson gson = new Gson();
        String json = gson.toJson(dataObject);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try
        {
            response.getWriter().write(json);
        }
        catch (IOException e)
        {
            throw new Exception(e.getMessage() +" --- error on returning JSON Object");
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
    private void CheckIsConnected(Mapping mapping, HttpServletRequest request)throws Exception
    {
        try
        {
            String[] require_authentification = Authentification.GetAuthentification(mapping);
            if (require_authentification == null) return;
            if(request.getSession().getAttribute(this.InitParam.get("isConnected")) == null)
            {
                throw new Exception("Your are not connected");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws IOException, ClassNotFoundException
    {
        if(!CanProcess(request))return;

        try
        {
            FileUpload.AddFileUpload(this.fileUploads, request, response);

            Mapping mapping = this.getMapping(request);

            MyDispatcher(mapping, request, response);

            CheckAuthentification(mapping, request);

            CheckIsConnected(mapping, request);

            if(!TryModelView(mapping, request, response))
            {
                if (!this.isJson)
                {
                    UseDefaultController(request, response);
                }
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
        InitParam.put("session_name",  getServletConfig().getInitParameter("session_name"));
        InitParam.put("isConnected",  getServletConfig().getInitParameter("isConnected"));
        InitParam.put("default_controller",  getServletConfig().getInitParameter("default_controller"));
        InitParam.put("excludeFolders", getServletConfig().getInitParameter("excludeFolders"));
    }
    public boolean CanProcess(HttpServletRequest request)
    {
        String excludeFolders = this.InitParam.get("excludeFolders");
        String requestPath = request.getRequestURI();
        if (excludeFolders != null && requestPath.contains(excludeFolders))return false;
        return true;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String path = getServletContext().getRealPath("/WEB-INF/classes");
        this.setInitParam();
        String package_name = InitParam.get("package_name");
        try
        {
            this.MappingUrls = new HashMap<>();
            this.Singletons = new HashMap<>();

            Init.setUrl_and_Singleton(this.MappingUrls,this.Singletons,null, package_name, path);
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
            ex.printStackTrace();
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
            ex.printStackTrace();
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
