package Book;

import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//        if(req.getSession(false).getAttribute("Librarian_Id") == null){
//            RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
//            dispatcher.forward(req,res);
//        }

//        Handling json request
        String jb = Util.jsonRequestHandler(req);

        Gson gson = new Gson();
        Book book = gson.fromJson(String.valueOf(jb), Book.class);

        int status = BookDao.addBook(book);
//        List<Book> allBook = BookDao.getALlBooks();
//        req.setAttribute("allBook",allBook);
        if(status > 0){
            PrintWriter out = res.getWriter();
            res.setStatus(200);
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("book-list.jsp");
//            dispatcher.forward(req,res);
        }
        else{
            res.sendError(500);

        }

    }
    protected  void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

    }
}
