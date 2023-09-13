import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/json")
public class JSONServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        //How to generate a single JSON Object using JSON Processing
//        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
//        objectBuilder.add("id","C001");
//        objectBuilder.add("name","Tharindu");
//        objectBuilder.add("address","Mathara");
//        objectBuilder.add("salary",200000);
//        JsonObject build = objectBuilder.build();
//
//        PrintWriter writer = resp.getWriter();
//        writer.print(build);

        //How to generate a JSON Object Array using JSON Processing
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();//create json array

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();//create json object
        objectBuilder.add("id","C001");
        objectBuilder.add("name","Tharindu");
        objectBuilder.add("address","Mathara");
        objectBuilder.add("salary",200000);

        arrayBuilder.add(objectBuilder.build());//creat json obj and add to it json array

        JsonObjectBuilder objectBuilder2 = Json.createObjectBuilder();//create json object
        objectBuilder2.add("id","C002");
        objectBuilder2.add("name","Tharindu");
        objectBuilder2.add("address","Mathara");
        objectBuilder2.add("salary",200000);

        arrayBuilder.add(objectBuilder2.build());//creat json obj and add to it json array

        JsonArray build = arrayBuilder.build();//build json array

        PrintWriter writer = resp.getWriter();
        writer.print(build);
//        writer.print(arrayBuilder.build());

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Put Method wada.......");

//        ServletInputStream inputStream = req.getInputStream();
//        int read;
//        while ((read= inputStream.read()) != -1){
//            System.out.print((char)read);
//        }

        //How to work with JSON Processing
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String city = jsonObject.getString("city");
        System.out.println(id+" "+name+" "+city);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //handle json array came from client side
//        JsonReader reader = Json.createReader(req.getReader());
//        JsonArray jsonArray = reader.readArray();
//        int size = jsonArray.size();
//        System.out.println(size);
//
//        for (int i=0;i<size;i++){
//            JsonObject jsonObject = jsonArray.getJsonObject(i);
//            String id = jsonObject.getString("id");
//            String name = jsonObject.getString("name");
//            String address = jsonObject.getString("address");
//            int salary = jsonObject.getInt("salary");
//
//            System.out.println("Object "+i+" : "+id+" , "+name+" , "+address+" , "+salary);
//        }
        
        //using for-each loop
        JsonReader reader = Json.createReader(req.getReader());
        JsonArray jsonArray = reader.readArray();
        for (JsonValue jsonValue: jsonArray) {
            String id = jsonValue.asJsonObject().getString("id");
            String name = jsonValue.asJsonObject().getString("name");
            String address = jsonValue.asJsonObject().getString("address");
            int salary = jsonValue.asJsonObject().getInt("salary");

            System.out.println(id+" , "+name+" , "+address+" , "+salary);
        }
        
    }
}
