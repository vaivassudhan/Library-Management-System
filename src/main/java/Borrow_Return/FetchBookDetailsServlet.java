package Borrow_Return;

import Book.Book;
import Book.BookDao;
import Student.Student;
import Student.StudentDao;
import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class FetchBookDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("GET not available"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String jb = Util.jsonRequestHandler(request);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

//      Get Values from json
        int Book_Id = jsonObject.get("Book_Id").getAsInt();
        int Student_Id = jsonObject.get("Student_Id").getAsInt();

        Book book = BookDao.getBookByID(Book_Id);
        Student student = StudentDao.getStudentById(Student_Id);


        String bookJson = new Gson().toJson(book);
        String studentJSon = new Gson().toJson(student);

//      Combining JSON into array
        String bothJson = "["+bookJson+","+studentJSon+"]";
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        out.print(bothJson);
        response.setStatus(HttpURLConnection.HTTP_OK);
        out.flush();

    }
}
