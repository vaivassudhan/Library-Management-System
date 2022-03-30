package Fine;

import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateFineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      Handling json request
        String jb = Util.jsonRequestHandler(request);

        System.out.println("FINE SERVLET "+ jb);
        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

//      Get Values from json
        int Group_Id = jsonObject.get("Group_Id").getAsInt();
        int Fine_per_day = jsonObject.get("Fine_Per_Day").getAsInt();

        System.out.println("UPDATE FINE " + Group_Id);
        System.out.println("UPDATE FINE " + Fine_per_day);

        int status = FineDao.updateFine(Group_Id,Fine_per_day);
        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();


        if(status != 0 ){
            jsonobject.addProperty("message-type","success");
            jsonobject.addProperty("message","Fine Updated Successfully!");
            out.write(String.valueOf(jsonobject));

        }
        else{
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Some Error occurred");
            out.write(String.valueOf(jsonobject));
        }
    }
}
