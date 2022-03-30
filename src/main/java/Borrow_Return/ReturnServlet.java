package Borrow_Return;

import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jb = Util.jsonRequestHandler(request);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

//      Get Values from json
        int Borrow_Id = jsonObject.get("Borrow_Id").getAsInt();

        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();

        Borrow borrow = BorrowDao.returnBook(Borrow_Id);
        if(borrow.getBorrow_Id() == 0){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Invalid borrow Id ");
            out.write(String.valueOf(jsonobject));
            return;
        }

        String borrowJson = new Gson().toJson(borrow);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        out.print(borrowJson);
        out.flush();
//        if(borrow.getBorrow_Date()==null || borrow.getReturn_Date()!=null){
//            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
//            request.setAttribute("message-type","danger");
//            request.setAttribute("message","Book Returned already");
//            dispatcher.forward(request,response);
//        }

//        request.setAttribute("borrow",borrow);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("return-details.jsp");
//        dispatcher.forward(request,response);
    }
}
