package servlets;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("do method invoked");

        try {
            BasicDataSource bds = (BasicDataSource) req.getServletContext().getAttribute("bds");
            Connection connection = bds.getConnection();//get connection from the connection pool
            ResultSet resultSet = connection.prepareStatement("SELECT * FROM customer").executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            connection.close();//return the connection to the connection pool from the consumer pool
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("bds");
        //Connection connection = bds.getConnection();

        resp.setContentType("application/json");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        double salary = jsonObject.getInt("salary");

        String sql="INSERT INTO customer VALUES(?,?,?,?)";

        try {
            Connection connection = bds.getConnection();//get connection from the connection pool
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,address);
            preparedStatement.setObject(4,salary);
            int i = preparedStatement.executeUpdate();
            if (i>0) {
                System.out.println("customer saved");
            }else {
                System.out.println("customer not saved");
            }
            connection.close();//return the connection to the connection pool from the consumer pool
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
