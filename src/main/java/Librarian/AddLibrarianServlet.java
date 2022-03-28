package Librarian;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddLibrarianServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Librarian librarian = new Librarian();
        librarian.setLibrarian_Id(request.getParameter("Librarian_Id"));
        librarian.setName(request.getParameter("Name"));
        librarian.setRole(Integer.parseInt(request.getParameter("Role")));
        librarian.setGender(request.getParameter("Gender"));

        int status = LibrarianDao.addLibrarian(librarian);
        if(status != 0 ){
            request.setAttribute("message-type","success");
            request.setAttribute("message","Librarian Added Successfully");
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }
        else{
            request.setAttribute("message-type","error");
            request.setAttribute("message","some error occurred!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }
    }
}
