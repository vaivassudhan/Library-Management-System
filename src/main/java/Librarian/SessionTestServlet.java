package Librarian;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        System.out.println("---------------------");
        if(session.isNew()){
            System.out.println("New Session");
        }
        else{
            System.out.println("Existing Session ");
        }
        System.out.println(session.getId());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
