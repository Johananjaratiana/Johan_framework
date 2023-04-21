package etu1933.framework.servlet;

import etu1933.framework.Mapping;
import etu1933.framework.view.ModelView;
import helpers_J.Formulaire;
import helpers_J.Init;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontServlet extends HttpServlet {
    
    HashMap<String,Mapping> MappingUrls;
    
    public Mapping getMapping(HttpServletRequest request){
        try{
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
        }catch(Exception e){
            return null;
        }
    }
    
    public ModelView getModelView(Mapping mapping) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException
    {
        try
        {
            Class<?> classe = Class.forName(mapping.getClassName());
            Object instance = classe.newInstance();
            Method methode = classe.getMethod(mapping.getMethod());
            ModelView modelview  = (ModelView)(methode.invoke(instance));
            return modelview;
        }
        catch(Exception ex) {return null;}
    }
    
    public boolean testModelView(Mapping mapping,HttpServletRequest request, HttpServletResponse response){
        try{
            ModelView modelview = this.getModelView(mapping);// trouver le lien mapping
            if(modelview == null)return false;
            modelview.sendData(request, response);// Envoie du data
            String page = modelview.getView();
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.include(request, response);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public boolean testUseObjectFonction(Mapping mapping,HttpServletRequest request, HttpServletResponse response)throws Exception{
        try{
            return Formulaire.formulaire_object(mapping, request, response);
        }catch(Exception ex){
            return false;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException {
        try{
            Mapping mapping = this.getMapping(request);
            if(!this.testUseObjectFonction(mapping, request, response)){
                if(!this.testModelView(mapping, request, response)){
                    this.error(request,response);
                }
            }
        }catch(Exception ex){
             this.error(request,response);
        }
    }
    
    @Override
    public void init() throws ServletException {
        super.init();
        String path = getServletContext().getRealPath("/WEB-INF/classes");
        String package_name = getServletConfig().getInitParameter("package_name");
        Init init = new Init();
        try {
            this.MappingUrls = new HashMap<>();
            init.setUrl(this.MappingUrls,null, package_name, path);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void error(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");            
            out.println("</head>");
            out.println("<body>");    
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
