package Group_days;

import Fine.Fine;
import Fine.FineDao;
import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class AddGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(request.getSession().getAttribute("Librarian_Id") == null){
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
//            dispatcher.forward(request,response);
//        }
        GroupDays groupDays = new GroupDays();
//      Handling json request
        String jb = Util.jsonRequestHandler(request);
        System.out.println("HELLO " + jb);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

//      Get Values from json
        int days = jsonObject.get("Days").getAsInt();
        float fine_per_day = jsonObject.get("Fine_Per_Day").getAsInt();

        groupDays.setDays(days);

        int group_id = GroupDaysDAO.addGroup(groupDays);
        Fine fine = new Fine();
        fine.setGroup_Id(group_id);
        fine.setFine_Per_Day(fine_per_day);
        int status = FineDao.addFine(fine);

        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();
        if(status != 0 ){
            jsonobject.addProperty("message-type","success");
            jsonobject.addProperty("message","Group Added Successfully");
            out.write(String.valueOf(jsonobject));
            out.flush();
        }
        else{
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Some Error Occurred");
            out.write(String.valueOf(jsonobject));
            out.flush();
        }
    }
}
