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
//      Handling json request
        String jb = Util.jsonRequestHandler(request);

        Gson gson = new Gson();
        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();
//      Json to Java POJO
        Student student = gson.fromJson(String.valueOf(jb), Student.class);

//      Check cases
        if(Objects.equals(student.getStudent_Name(), "") || Objects.equals(student.getMobile(), "")){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Error Occurred! ");
            out.write(String.valueOf(jsonobject));
            return;
        }
        if(student.getMobile().length() < 10){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Invalid Mobile Number! ");
            out.write(String.valueOf(jsonobject));
            return;
        }
        int validYear = Calendar.getInstance().get(Calendar.YEAR)+10 ;
        if(student.getBatch() < 1800 || student.getBatch() > validYear ){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Invalid Batch ");
            out.write(String.valueOf(jsonobject));
            return;
        }



        int status = StudentDao.registerStudent(student);
        if(status > 0 ){
            jsonobject.addProperty("message-type","success");
            jsonobject.addProperty("message","Student Added Successfully");
            out.write(String.valueOf(jsonobject));

        }
        else{
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Error Occurred! ");
            out.write(String.valueOf(jsonobject));
        }

    }
}
