package Book;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ViewBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getSession(false));
        List<Book> allBook = BookDao.getALlBooks();
        String bookJson = new Gson().toJson(allBook);
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(bookJson);
        out.flush();
//        request.setAttribute("allBook",allBook);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
//        dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
