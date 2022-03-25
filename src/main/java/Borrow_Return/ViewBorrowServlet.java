package Borrow_Return;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ViewBorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(request.getSession(false).getAttribute("Librarian_Id") == null){
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
//            dispatcher.forward(request,response);
//        }
        System.out.println(request.getSession(false).getAttribute("Librarian_Id"));
        List<Borrow> allBorrow = BorrowDao.getAllBorrow();
        List<Borrow> allReturn = BorrowDao.getAllReturned();

        request.setAttribute("allBorrow",allBorrow);
        request.setAttribute("allReturn",allReturn);
        RequestDispatcher dispatcher = request.getRequestDispatcher("borrowed-list.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
