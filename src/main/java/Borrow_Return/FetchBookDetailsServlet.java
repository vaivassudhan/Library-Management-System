package Borrow_Return;

import Book.Book;
import Book.BookDao;
import Student.Student;
import Student.StudentDao;
import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class FetchBookDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jb = Util.jsonRequestHandler(request);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

//      Get Values from json
        int Book_Id = jsonObject.get("Book_Id").getAsInt();
        int Student_Id = jsonObject.get("Student_Id").getAsInt();

        System.out.println("CHECK Fetch");
        System.out.println(Book_Id);

        Book book = BookDao.getBookByID(Book_Id);
        Student student = StudentDao.getStudentById(Student_Id);


        String bookJson = new Gson().toJson(book);
        String studentJSon = new Gson().toJson(student);

        String bothJson = "["+bookJson+","+studentJSon+"]";

        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        out.print(bothJson);
        out.flush();

    }
}
