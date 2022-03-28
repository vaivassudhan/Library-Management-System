package Student;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ViewStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false).getAttribute("Librarian_Id") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("login");
            dispatcher.forward(request,response);
        }
        List<Student> allStudent = StudentDao.getAllStudent();
        request.setAttribute("allStudent",allStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
