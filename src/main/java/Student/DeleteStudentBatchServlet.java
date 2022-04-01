package Student;

import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteStudentBatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
//      Handling json request
        String jb = Util.jsonRequestHandler(request);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

        int batch = jsonObject.get("Batch").getAsInt();
        int status = StudentDao.deleteStudentByBatch(batch);
        if(status != 0 ){
            out.write(Util.successMessageJson("Deleted successfully"));
            response.setStatus(200);
        }
        else{
            out.write(Util.createErrorJson("Internal server error"));
        }
    }
}
