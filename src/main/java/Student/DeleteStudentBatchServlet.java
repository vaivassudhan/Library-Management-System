package Student;

import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class DeleteStudentBatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpURLConnection.HTTP_BAD_METHOD);
        response.getWriter().write(Util.createErrorJson("Method not allowed"));
        response.getWriter().flush();
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
            response.setStatus(HttpURLConnection.HTTP_OK);
        }
        else{
            out.write(Util.createErrorJson("Error occurred. Please check the batch number"));
            response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
        }
        out.flush();
    }
}
