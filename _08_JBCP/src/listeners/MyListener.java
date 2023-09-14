package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Context Initialized");
        //This method invoked suddenly just after the creation of servlet context
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Context Destroyed");
        //This method invoked suddenly before the servlet context destroyed
    }
}
