package etu1933.framework.servlet;

import helpers_J.GlobalServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        ServletContext servletContext = sce.getServletContext();
        GlobalServlet.setServletContext(servletContext);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
    }
}

