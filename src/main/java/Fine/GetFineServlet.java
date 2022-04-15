package Fine;

import Utils.Util;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.List;

public class GetFineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<Fine> allFine = FineDao.getALlFine();
        String fineJson = new Gson().toJson(allFine);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        out.print(fineJson);
        response.setStatus(HttpURLConnection.HTTP_OK);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("POST not available"));
        response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
    }
}
