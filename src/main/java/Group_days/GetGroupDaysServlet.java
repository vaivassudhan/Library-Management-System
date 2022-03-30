package Group_days;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetGroupDaysServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(request.getSession().getAttribute("Librarian_Id") == null){
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
//            dispatcher.forward(request,response);
//        }
        List<GroupDays> allGroups = GroupDaysDAO.getAllGroups();
        String groupJson = new Gson().toJson(allGroups);
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        out.print(groupJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
