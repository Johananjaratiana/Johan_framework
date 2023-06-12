package etu1933.framework.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class StaticContentServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // Obtenez l'objet ServletContext
        ServletContext servletContext = getServletContext();
        // Obtenez l'attribut "session" de l'objet ServletContext
        @SuppressWarnings("unchecked")
        HashMap<String, Object> session = (HashMap<String, Object>) servletContext.getAttribute("authentification");
        System.out.println(session);
    }
}