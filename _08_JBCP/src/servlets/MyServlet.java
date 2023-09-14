package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/servlets")
public class MyServlet extends HttpServlet {
    //Life cycle of servlet
    public MyServlet() {
        System.out.println("create MyServlet object");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("initialize as a Servlet");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy the servlet");
    }
}
