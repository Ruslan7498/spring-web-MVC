package ru.netology.initializer;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {
    public static final String RU_NETOLOGY = "ru.netology";
    public static final String APP = "app";
    public static final String SLASH = "/";

    @Override
    public void onStartup(ServletContext servletContext) {
        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.scan(RU_NETOLOGY);
        applicationContext.refresh();
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        final ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(APP, dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping(SLASH);
    }
}
