import db.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/customer2")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hari..........");

        resp.setContentType("application/json");

        String sql="SELECT * FROM customer";

        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();

            String data="";
            while (resultSet.next()){
                String id=resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                Double salary = resultSet.getDouble(4);

                data=data+"{\"id\":\""+id+"\",\"name\":\""+name+"\",\"address\":\""+address+"\",\"salary\":"+salary+"},";
                System.out.println(data);
            }

            String customerList="["+data.substring(0,data.length()-1)+"]";
            System.out.println(customerList);

            PrintWriter writer = resp.getWriter();
            writer.write(customerList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post method hari.........");

        String id=req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        Double salary = Double.parseDouble(req.getParameter("salary"));

        System.out.println("id = "+id+" name = "+name+" address = "+address+" salary = "+salary);

        String sql="INSERT INTO customer VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,address);
            preparedStatement.setObject(4,salary);
            int i = preparedStatement.executeUpdate();
            PrintWriter writer = resp.getWriter();
            if(i>0){
                writer.write("true");
            }else {
                writer.write("false");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("delete method call........");

        String id=req.getParameter("id");
        System.out.println("delete id eka = "+id);

        String sql="DELETE FROM customer WHERE id=?";

        System.out.println("SQL : "+sql);

        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setObject(1,id);
            int i = preparedStatement.executeUpdate();
            PrintWriter writer = resp.getWriter();
            if (i>0){
                writer.write("true");
            }else {
                writer.write("false");
            }
        } catch (SQLException e) {
            resp.sendError(500,e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            resp.sendError(500,e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Put method call.....");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        double salary = Double.parseDouble(req.getParameter("salary"));

        String sql="UPDATE customer SET  name=?,address=?,salary=? WHERE id=?";
        System.out.println("Update data : "+id+" : "+name+" : "+address+" : "+salary);

        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,address);
            preparedStatement.setObject(3,salary);
            preparedStatement.setObject(4,id);
            int i = preparedStatement.executeUpdate();
            PrintWriter writer = resp.getWriter();
            if (i>0){
                writer.write("true");
            }else {
                writer.write("false");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //UPDATE servlet.customer t SET t.salary = 800000 WHERE t.id LIKE 'C008' ESCAPE '#'
}