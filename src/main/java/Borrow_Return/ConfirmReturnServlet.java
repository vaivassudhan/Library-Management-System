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


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //      Handling json request
        String jb = Util.jsonRequestHandler(request);
        Gson gson = new Gson();
//      Json to Java POJO
        Borrow borrow = gson.fromJson(String.valueOf(jb), Borrow.class);

        System.out.println(borrow.getBorrow_Id());

        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();


        int status = BorrowDao.confirmReturn(borrow);
        if(status != 0){
            jsonobject.addProperty("message-type","success");
            jsonobject.addProperty("message","Book Returned Successfully! ");
            out.write(String.valueOf(jsonobject));
            return;
        }
        else{
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Error Occurred ");
            out.write(String.valueOf(jsonobject));
        }
    }
}
