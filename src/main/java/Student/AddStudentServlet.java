package Student;

import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.Objects;

public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("Invalid request"));
        response.setStatus(HttpURLConnection.HTTP_BAD_METHOD);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
//      Handling json request & Json to Java POJO
        String jb = (String) request.getAttribute("requestJson");
        Gson gson = new Gson();
        Student student = gson.fromJson(String.valueOf(jb), Student.class);

//      Check cases
        if(Objects.equals(student.getStudent_Name(), "") || Objects.equals(student.getMobile(), "")){
            out.write(Util.createErrorJson("Please check the student name"));
            response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
            out.flush();
            return;
        }
        if(student.getMobile().length() < 10){
            out.write(Util.createErrorJson("Invalid mobile number"));
            response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
            out.flush();
            return;
        }
        int validYear = Calendar.getInstance().get(Calendar.YEAR)+10 ;
        if(student.getBatch() < 1800 || student.getBatch() > validYear ){
            out.write(Util.createErrorJson("Invalid Batch"));
            response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
            out.flush();
            return;
        }
        int status = StudentDao.registerStudent(student);
        if(status > 0 ){
            out.write(Util.successMessageJson("Student added successfully"));
            response.setStatus(HttpURLConnection.HTTP_OK);
        }
        else{
            out.write(Util.createErrorJson("Internal error occurred"));
            response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
        }
        out.flush();

    }
}
