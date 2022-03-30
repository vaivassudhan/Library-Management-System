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
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//      Handling json request
        String jb = Util.jsonRequestHandler(req);

        Gson gson = new Gson();
//      Json to Java POJO
        Book book = gson.fromJson(String.valueOf(jb), Book.class);

        JsonObject jsonobject = new JsonObject();
        PrintWriter out = res.getWriter();

//      Checking edge cases
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        if(book.getPublished_year()<=1800 || book.getPublished_year()>curYear){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Year not valid");
            out.write(String.valueOf(jsonobject));
            return;
        }
        if(book.getNos_Available() <= 0 ){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Stock cannot be less than 0");
            out.write(String.valueOf(jsonobject));
            return;
        }
        if(Objects.equals(book.getBook_Title(), "")){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Invalid Book Name");
            out.write(String.valueOf(jsonobject));
            return;
        }
        if(Objects.equals(book.getAuthor_Name(), "")){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Invalid Author Name");
            out.write(String.valueOf(jsonobject));
            return;
        }


        int status = BookDao.addBook(book);

        if(status > 0){
            res.setStatus(200);
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            jsonobject.addProperty("message","Book Added Successfully!");
            jsonobject.addProperty("message-type","success");
            out.write(String.valueOf(jsonobject));
            out.flush();
        }
        else{
            res.sendError(500);
        }

    }
    protected  void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        JsonObject jsonobject = new JsonObject();
        PrintWriter out = res.getWriter();

        jsonobject.addProperty("message-type","error");
        jsonobject.addProperty("message","Get method not available");
        out.write(String.valueOf(jsonobject));

    }
}
