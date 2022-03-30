package Borrow_Return;

import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewBorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(request.getSession(false).getAttribute("Librarian_Id") == null){
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
//            dispatcher.forward(request,response);
//        }

        String jb = Util.jsonRequestHandler(request);
        List<Borrow> allBorrow = BorrowDao.getAllBorrow();
        List<Borrow> allReturn = BorrowDao.getAllReturned();

        JsonObject jsonObject = new JsonObject();
        PrintWriter out = response.getWriter();
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

    }
}
