package Book;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ViewBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> allBook = BookDao.getALlBooks();
        String bookJson = new Gson().toJson(allBook);
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(bookJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();
        jsonobject.addProperty("message-type","error");
        jsonobject.addProperty("message","Post method not available");
        out.write(String.valueOf(jsonobject));
    }
}
