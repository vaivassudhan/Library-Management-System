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
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("GET not available"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

//      Read Token From Response Header
        String token = request.getHeader("Authorization").split(" ")[1];
//      Auth Check
        if(!Util.verifyAuth(token)){
            out.write(Util.createErrorJson("UnAuthorized"));
            response.setStatus(401);
        }
//      Handling json request
        String jb = Util.jsonRequestHandler(request);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

//      Get Values from json
        int Group_Id = jsonObject.get("Group_Id").getAsInt();
        int Fine_per_day = jsonObject.get("Fine_Per_Day").getAsInt();

        int status = FineDao.updateFine(Group_Id,Fine_per_day);
        if(status != 0 ){
            out.write(Util.successMessageJson("Fine Updated Successfully!"));

        }
        else{
            out.write(Util.createErrorJson("Some error occurred"));
        }
    }
}
