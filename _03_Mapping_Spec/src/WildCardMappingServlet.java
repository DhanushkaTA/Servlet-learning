import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/items/*")
public class WildCardMappingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        String method = req.getMethod();
        String pathInfo = req.getPathInfo();
        String contextPath = req.getContextPath();
        /** URL :- http://localhost:8080/_03_Mapping_Spec_Web_exploded/items/I001/saman */
        System.out.println(servletPath); // /items
        System.out.println(method); // GET
        System.out.println(pathInfo); // /I001/saman
        System.out.println(contextPath); // /_03_Mapping_Spec_Web_exploded
        System.out.println("Wildcard mapping spec");
    }
}
