import db.DBConnection;
import dto.CustomerDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("hari..........");
//
//        String sql="SELECT cus_ID,cus_Name FROM customer";
//        ArrayList<CustomerDto>customerList=new ArrayList<>();
//        try {
//            ResultSet resultSet = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
//
//            while (resultSet.next()){
//                customerList.add(new CustomerDto(resultSet.getString(1),resultSet.getString(2) ));
//            }
//
//            ArrayList<String>list=new ArrayList<>();
//            for (CustomerDto c : customerList) {
//                System.out.println(c.toString());
//                String s= "{\"id\": \""+c.getCus_id()+"\"," +
//                        "\"name\": \""+c.getCus_name()+"\"}";
//                list.add(s);
//                System.out.println(s);
//            }
//
//            String s="[";
//            for (int i=0;i<list.size();i++) {
//                s=s+list.get(i);
//                if(i!=list.size()-1){
//                    s=s+",";
//                }
//            }
//            String s1=s+"]";
//            System.out.println(s1);
//
//            PrintWriter writer = resp.getWriter();
//            writer.write(s1);
//            resp.setContentType("application/json");
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//
//    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post method");
        String id=req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String salary = req.getParameter("salary");

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
}