import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("do method invoked");

        //How to create connection pool
        BasicDataSource bts=new BasicDataSource();
        bts.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bts.setUrl("jdbc:mysql://localhost/servlet");
        bts.setUsername("root");
        bts.setPassword("1234");

        bts.setMaxTotal(5);//add how many connection we want
        bts.setInitialSize(5);//set how many connection we should initialize

        try {
            Connection connection = bts.getConnection();//get connection
            ResultSet resultSet = connection.prepareStatement("SELECT * FROM customer").executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
