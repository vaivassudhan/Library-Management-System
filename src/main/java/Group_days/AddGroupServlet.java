package Group_days;

import Fine.Fine;
import Fine.FineDao;
import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("GET not available"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        GroupDays groupDays = new GroupDays();
//      Handling json request
        String jb = Util.jsonRequestHandler(request);
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

        if(status != 0 ){
            out.write(Util.successMessageJson("Group Added Successfully"));
            out.flush();
        }
        else{
            out.write(Util.createErrorJson("Internal Error occurred"));
            out.flush();
        }
    }
}
