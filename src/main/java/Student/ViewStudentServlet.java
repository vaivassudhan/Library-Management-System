package Student;

import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        List<Student> allStudent = StudentDao.getAllStudent();
        String studentJson = new Gson().toJson(allStudent);
        out.write(studentJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("POST not available"));
    }
}
