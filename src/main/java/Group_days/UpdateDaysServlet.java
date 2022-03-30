package Group_days;

import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateDaysServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDays groupDays = new GroupDays();
//      Handling json request
        String jb = Util.jsonRequestHandler(request);
        System.out.println("HELLO " + jb);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

        int group_id = jsonObject.get("Group_Id").getAsInt();
        int days = jsonObject.get("Days").getAsInt();


        int status = GroupDaysDAO.updateGroup(group_id,days);
        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();
        if(status != 0 ){
            jsonobject.addProperty("message-type","success");
            jsonobject.addProperty("message","Group Updated Successfully");
            out.write(String.valueOf(jsonobject));
            out.flush();
        }
        else{
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Error occurred");
            out.write(String.valueOf(jsonobject));
            out.flush();
        }
    }
}
