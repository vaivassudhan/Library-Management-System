package Borrow_Return;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class AddBorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("Librarian_Id") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request,response);
        }
        Borrow borrow = new Borrow();
        borrow.setBook_Id(Integer.parseInt(request.getParameter("Book_Id")));
        borrow.setStudent_Id(Integer.parseInt(request.getParameter("Student_Id")));
        borrow.setIssued_By((String) session.getAttribute("Librarian_Id"));

        int borrow_id = BorrowDao.borrowBook(borrow);
        System.out.println("servlet check " +borrow_id);
        if(borrow_id == 0){
            request.setAttribute("message-type","error");
            request.setAttribute("message","Error occurred. Please check stock or book id properly");
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }
        if(borrow_id != 0 ){
            request.setAttribute("message-type","success");
            request.setAttribute("message","Book Issued Successfully. Please Note the Borrow ID "+borrow_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }

    }
}
