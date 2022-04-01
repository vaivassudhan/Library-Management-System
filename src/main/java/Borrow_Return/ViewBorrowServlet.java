package Borrow_Return;

import Utils.Util;
import com.google.gson.Gson;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
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
        out.flush();


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("POST not available"));
    }
}
