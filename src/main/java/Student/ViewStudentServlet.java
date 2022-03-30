package Student;

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
//        if(request.getSession(false).getAttribute("Librarian_Id") == null){
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login");
//            dispatcher.forward(request,response);
//        }
        List<Student> allStudent = StudentDao.getAllStudent();
        String studentJson = new Gson().toJson(allStudent);
        PrintWriter out = response.getWriter();
        out.write(studentJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message-type","error");
        jsonObject.addProperty("message","Post method not available!");
        response.setStatus(400);
        PrintWriter out = response.getWriter();
        out.write(String.valueOf(jsonObject));
    }
}
