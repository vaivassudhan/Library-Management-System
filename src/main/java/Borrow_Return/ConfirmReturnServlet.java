package Borrow_Return;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ConfirmReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false).getAttribute("Librarian_Id") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request,response);
        }
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        Borrow borrow = (Borrow) session.getAttribute("borrow");

        out.println("Hello "+  borrow.getBorrow_Id());
        int status = BorrowDao.confirmReturn(borrow);
        if(status != 0){
            out = response.getWriter();
            out.println("Returned book successfully");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
