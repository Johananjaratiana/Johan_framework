package etu1933.framework.servlet;

import etu1933.framework.Mapping;
import helpers_J.Init;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontServlet extends HttpServlet {
    
    HashMap<String,Mapping> MappingUrls;
    
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        processRequest(request, response);
    }
}
