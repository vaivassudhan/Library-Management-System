package Fine;

import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class UpdateFineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("GET not available"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
//      Handling json request
        String jb = (String) request.getAttribute("requestJson");

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

//      Get Values from json
        int Group_Id = jsonObject.get("Group_Id").getAsInt();
        int Fine_per_day = jsonObject.get("Fine_Per_Day").getAsInt();

        int status = FineDao.updateFine(Group_Id,Fine_per_day);
        if(status != 0 ){
            out.write(Util.successMessageJson("Fine Updated Successfully!"));
            response.setStatus(HttpURLConnection.HTTP_OK);

        }
        else{
            out.write(Util.createErrorJson("Some error occurred"));
            response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
        }
        out.flush();
    }
}
