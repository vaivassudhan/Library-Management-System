package Book;

import Utils.Util;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.Objects;

public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter out = res.getWriter();
//      Handling json request and converting to Java POJO
        String jb = Util.jsonRequestHandler(req);
        Gson gson = new Gson();
        Book book = gson.fromJson(String.valueOf(jb), Book.class);


//      Checking edge cases
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        if(book.getPublished_year()<=1800 || book.getPublished_year()>curYear){
            out.write(Util.createErrorJson("Year not valid"));
            res.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
            return;
        }
        if(book.getNos_Available() <= 0 ){
            out.write(Util.createErrorJson("Stock cannot be less than 0"));
            res.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
            return;
        }
        if(Objects.equals(book.getBook_Title(), "") || book.getBook_Title().length() < 3){
            out.write(Util.createErrorJson("Invalid Book Name"));
            res.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
            return;
        }
        if(Objects.equals(book.getAuthor_Name(), "") || book.getAuthor_Name().length() < 3){
            out.write(Util.createErrorJson("Invalid Author Name"));
            res.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
            return;
        }

        int status = BookDao.addBook(book);

        if(status > 0){
            res.setStatus(HttpURLConnection.HTTP_OK);
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            out.write(Util.successMessageJson("Book added successfully!"));
            out.flush();
        }
        else{
            res.sendError(HttpURLConnection.HTTP_INTERNAL_ERROR);
        }

    }
    protected  void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        res.sendError(HttpURLConnection.HTTP_INTERNAL_ERROR);
        out.write(Util.createErrorJson("Get method not available"));

    }
}
