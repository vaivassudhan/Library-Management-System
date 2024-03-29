package Student;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student();
        PrintWriter out = response.getWriter();
        out.println(request.getParameter("Gender"));
        student.setStudent_Name(request.getParameter("Student_Name"));
        student.setGender(request.getParameter("Gender"));
        student.setMobile(request.getParameter("Mobile"));
        student.setGroup_id(Integer.parseInt(request.getParameter("Group_Id")));

        int status = StudentDao.registerStudent(student);
        if(status > 0 ){
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            request.setAttribute("message-type","success");
            request.setAttribute("message","Student "+ request.getParameter("Student_Name") +" Added Successfully!");
            dispatcher.forward(request,response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            request.setAttribute("message-type","error");
            request.setAttribute("message","Some Error Occurred");
            dispatcher.forward(request,response);
        }

    }
}
