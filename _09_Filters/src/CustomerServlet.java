import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hari..........");

        resp.setContentType("application/json");

        resp.addHeader("Access-Control-Allow-Origin","*");

        String sql="SELECT * FROM customer";

        try {
            Connection connection = dataSource.getConnection();
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (resultSet.next()){
                String id=resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                Double salary = resultSet.getDouble(4);

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id",id);
                objectBuilder.add("name",name);
                objectBuilder.add("address",address);
                objectBuilder.add("salary",salary);
                arrayBuilder.add(objectBuilder.build());
            }

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("data",arrayBuilder.build());
            objectBuilder.add("message","all customer list");
            objectBuilder.add("status",200);

            PrintWriter writer = resp.getWriter();
            writer.print(objectBuilder.build());

            connection.close();

        } catch (SQLException e) {
            JsonObjectBuilder exceotion = Json.createObjectBuilder();
            exceotion.add("status",400);
            exceotion.add("message","Error");
            exceotion.add("data",e.getLocalizedMessage());
            PrintWriter writer = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            writer.print(exceotion.build());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post method hari.........");

        resp.setContentType("application/json");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        double salary = (double) jsonObject.getInt("salary");

        System.out.println("id = "+id+" name = "+name+" address = "+address+" salary = "+salary);

        String sql="INSERT INTO customer VALUES(?,?,?,?)";

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,address);
            preparedStatement.setObject(4,salary);
            int i = preparedStatement.executeUpdate();
            PrintWriter writer = resp.getWriter();

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("data","");

            if(i>0){
                objectBuilder.add("message","customer saved");
                objectBuilder.add("status",201);
                resp.setStatus(HttpServletResponse.SC_CREATED);
                writer.print(objectBuilder.build());
            }else {
                objectBuilder.add("message","customer not saved");
                objectBuilder.add("status",500);
                writer.print(objectBuilder.build());
            }

            connection.close();
        } catch (SQLException e) {
            JsonObjectBuilder exceotion = Json.createObjectBuilder();
            exceotion.add("status",400);
            exceotion.add("message","Error");
            exceotion.add("data",e.getLocalizedMessage());
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = resp.getWriter();
            writer.print(exceotion.build());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("delete method call........");

        resp.setContentType("application/json");

        String id=req.getParameter("id");
        System.out.println("delete id eka = "+id);

        String sql="DELETE FROM customer WHERE id=?";

        System.out.println("SQL : "+sql);

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,id);
            int i = preparedStatement.executeUpdate();
            PrintWriter writer = resp.getWriter();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("data","");

            if (i>0){
                objectBuilder.add("message","customer deleted");
                objectBuilder.add("status",200);
                writer.print(objectBuilder.build());
            }else {
                objectBuilder.add("message","customer not deleted.Wrong ID Insert");
                objectBuilder.add("status",500);
                writer.print(objectBuilder.build());
            }

            connection.close();
        } catch (SQLException e) {
            JsonObjectBuilder exceotion = Json.createObjectBuilder();
            exceotion.add("status",500);
            exceotion.add("message","Error");
            exceotion.add("data",e.getLocalizedMessage());
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = resp.getWriter();
            writer.print(exceotion.build());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Put method call.....");

        resp.setContentType("application/json");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        double salary =(double) jsonObject.getInt("salary");

        String sql="UPDATE customer SET  name=?,address=?,salary=? WHERE id=?";
        System.out.println("Update data : "+id+" : "+name+" : "+address+" : "+salary);

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,address);
            preparedStatement.setObject(3,salary);
            preparedStatement.setObject(4,id);
            int i = preparedStatement.executeUpdate();
            PrintWriter writer = resp.getWriter();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("data","");
            if (i>0){
                objectBuilder.add("message","customer updated");
                objectBuilder.add("status",200);
                writer.print(objectBuilder.build());
            }else {
                objectBuilder.add("message","customer not updated");
                objectBuilder.add("status",500);
                writer.print(objectBuilder.build());
            }

            connection.close();
        } catch (SQLException e) {
            JsonObjectBuilder exceotion = Json.createObjectBuilder();
            exceotion.add("status",500);
            exceotion.add("message","Error");
            exceotion.add("data",e.getLocalizedMessage());
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = resp.getWriter();
            writer.print(exceotion.build());
            throw new RuntimeException(e);
        }
    }

    //UPDATE servlet.customer t SET t.salary = 800000 WHERE t.id LIKE 'C008' ESCAPE '#'
}