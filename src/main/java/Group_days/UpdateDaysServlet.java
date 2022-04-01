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
        PrintWriter out = response.getWriter();
//      Handling json request
        String jb = Util.jsonRequestHandler(request);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

        int group_id = jsonObject.get("Group_Id").getAsInt();
        int days = jsonObject.get("Days").getAsInt();

        int status = GroupDaysDAO.updateGroup(group_id,days);
        if(status != 0 ){
            out.write(Util.successMessageJson("Group updated successfully"));
            out.flush();
        }
        else{
            out.write(Util.createErrorJson("Internal error occurred"));
            out.flush();
        }
    }
}
