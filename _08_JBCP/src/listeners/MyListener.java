package listeners;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
    //This listener create to the track servlet context events
    //These methods only run the one time
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Context Initialized");
        //This method invoked suddenly just after the creation of servlet context

        //How to create connection pool
        BasicDataSource bts=new BasicDataSource();
        bts.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bts.setUrl("jdbc:mysql://localhost/servlet");
        bts.setUsername("root");
        bts.setPassword("1234");

        bts.setMaxTotal(5);//add how many connection we want
        bts.setInitialSize(5);//set how many connection we should initialize

        ServletContext servletContext = servletContextEvent.getServletContext();//get servlet context
        servletContext.setAttribute("bds",bts);//add bds to servlet context
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Context Destroyed");
        //This method invoked suddenly before the servlet context destroyed
    }
}
