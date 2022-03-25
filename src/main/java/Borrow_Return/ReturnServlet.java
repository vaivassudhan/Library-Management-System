package Borrow_Return;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false).getAttribute("Librarian_Id") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request,response);
        }
        int Borrow_Id = Integer.parseInt(request.getParameter("Borrow_Id"));
        Borrow borrow = BorrowDao.returnBook(Borrow_Id);
//        if(borrow.getBorrow_Date()==null || borrow.getReturn_Date()!=null){
//            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
//            request.setAttribute("message-type","danger");
//            request.setAttribute("message","Book Returned already");
//            dispatcher.forward(request,response);
//        }

        request.setAttribute("borrow",borrow);
        RequestDispatcher dispatcher = request.getRequestDispatcher("return-details.jsp");
        dispatcher.forward(request,response);
    }
}
