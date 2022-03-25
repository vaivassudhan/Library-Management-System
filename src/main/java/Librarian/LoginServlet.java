package Librarian;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Librarian_Id = request.getParameter("Librarian_Id");
        String Password = request.getParameter("Password");
        boolean status = LibrarianDao.validate(Librarian_Id,Password);
        response.setContentType("text/html");
        if(status){
            Librarian librarian = LibrarianDao.getDataById(Librarian_Id);
            HttpSession session = request.getSession();
            session.setAttribute("Librarian_Id",Librarian_Id);
            session.setAttribute("Name",librarian.getName());
            session.setAttribute("Role",librarian.getRole());
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request,response);
        }
        else{
            request.setAttribute("message-type","error");
            request.setAttribute("message","Invalid Username or Password. Please check your credentials.");

            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.include(request,response);



        }
    }
}
