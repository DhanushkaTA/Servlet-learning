package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = "/item") //filter only this servlet request
//@WebFilter(urlPatterns = {"/item","/customer"}) //Filter only these servlet request
//@WebFilter(urlPatterns = "/*")//filter all request
public class MyFilter implements Filter {

    public MyFilter() {
        System.out.println("MyFilter Object created");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter Initialized / Now MyFilter class is a Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Before the request sent
        System.out.println("Do Filter : DoFilter method called");

        //without this line request will not proceed to the servlet
        filterChain.doFilter(servletRequest,servletResponse);//proceed request to servlet

        //ServletResponse cast to HttpServletResponse for get addHeader method
        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;
        httpServletResponse.addHeader("My-Company","TDA");
        httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.addHeader("Access-Control-Allow-Methods","DELETE,PUT");
        httpServletResponse.addHeader("Access-Control-Allow-Headers","Content-Type");

        //After the servlet response
        System.out.println("Do Filter : after forward req to servlet");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter Object destroyed");
    }
}
