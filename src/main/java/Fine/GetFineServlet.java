package Fine;

import Utils.Util;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GetFineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

//      Read Token From Response Header
        String token = request.getHeader("Authorization").split(" ")[1];
//      Auth Check
        if(!Util.verifyAuth(token)){
            out.write(Util.createErrorJson("UnAuthorized"));
            response.setStatus(401);
        }
        List<Fine> allFine = FineDao.getALlFine();
        String fineJson = new Gson().toJson(allFine);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        out.print(fineJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("POST not available"));
    }
}
