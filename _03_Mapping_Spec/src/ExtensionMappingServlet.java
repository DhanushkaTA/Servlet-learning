import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.jpg")
public class ExtensionMappingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Extension Mapping");
        /** URL :- http://localhost:8080/_03_Mapping_Spec_Web_exploded/items.jpg */
        /** URL :- http://localhost:8080/_03_Mapping_Spec_Web_exploded/abcd.jpg */
    }
}
