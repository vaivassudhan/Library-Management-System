package Student;

import Book.Book;
import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Objects;

public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();
        jsonobject.addProperty("message-type","error");
        jsonobject.addProperty("message","Post Method not available ");
        out.write(String.valueOf(jsonobject));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
//      Handling json request & Json to Java POJO
        String jb = Util.jsonRequestHandler(request);
        Gson gson = new Gson();
        Student student = gson.fromJson(String.valueOf(jb), Student.class);

//      Check cases
        if(Objects.equals(student.getStudent_Name(), "") || Objects.equals(student.getMobile(), "")){
            out.write(Util.createErrorJson("Error occurred"));
            return;
        }
        if(student.getMobile().length() < 10){
            out.write(Util.createErrorJson("Invalid mobile number"));
            return;
        }
        int validYear = Calendar.getInstance().get(Calendar.YEAR)+10 ;
        if(student.getBatch() < 1800 || student.getBatch() > validYear ){
            out.write(Util.createErrorJson("Invalid Batch"));
            return;
        }
        int status = StudentDao.registerStudent(student);
        if(status > 0 ){
            out.write(Util.successMessageJson("Student added successfully"));
        }
        else{
            out.write(Util.createErrorJson("Internal error occurred"));
        }

    }
}
