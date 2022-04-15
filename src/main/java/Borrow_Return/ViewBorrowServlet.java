package Borrow_Return;

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

public class ViewBorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String jb = Util.jsonRequestHandler(request);
        List<Borrow> allBorrow = BorrowDao.getAllBorrow();
        List<Borrow> allReturn = BorrowDao.getAllReturned();
        String borrowJson = new Gson().toJson(allBorrow);
        String returnJson = new Gson().toJson(allReturn);
        String bothJson = '['+borrowJson+','+returnJson+']';
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        out.print(bothJson);
        response.setStatus(HttpURLConnection.HTTP_OK);
        out.flush();


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("POST not available"));
        response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }
}
