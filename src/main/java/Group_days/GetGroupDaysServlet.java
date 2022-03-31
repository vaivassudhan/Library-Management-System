package Group_days;

import Utils.Util;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetGroupDaysServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

//      Read Token From Response Header
        String token = request.getHeader("Authorization").split(" ")[1];
//      Auth Check
        if(!Util.isAdmin(token)){
            out.write(Util.createErrorJson("UnAuthorized"));
            response.setStatus(401);
        }
        List<GroupDays> allGroups = GroupDaysDAO.getAllGroups();
        String groupJson = new Gson().toJson(allGroups);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        out.print(groupJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(Util.createErrorJson("POST not available"));
        response.setStatus(401);
    }
}
