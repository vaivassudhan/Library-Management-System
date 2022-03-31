package Borrow_Return;

import Book.Book;
import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ConfirmReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("GET not available"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

//      Read Token From Response Header
        String token = request.getHeader("Authorization").split(" ")[1];
//      Auth Check
        if(!Util.verifyAuth(token)){
            out.write(Util.createErrorJson("UnAuthorized"));
            response.setStatus(401);
        }
//      Handling json request
        String jb = Util.jsonRequestHandler(request);
        Gson gson = new Gson();
//      Json to Java POJO
        Borrow borrow = gson.fromJson(String.valueOf(jb), Borrow.class);

        int status = BorrowDao.confirmReturn(borrow);
        if(status != 0){
            out.write(Util.successMessageJson("Book Returned successfully!"));
        }
        else{
            out.write(Util.createErrorJson("Error Occurred "));
        }
    }
}
