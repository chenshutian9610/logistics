package web;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebContainer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic registration=servletContext.addServlet("mvc",new DispatcherServlet());
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
        servletContext.setInitParameter("contextConfigLocation","classpath:applicationContext.xml");
        servletContext.addListener(new ContextLoaderListener());
    }
}
