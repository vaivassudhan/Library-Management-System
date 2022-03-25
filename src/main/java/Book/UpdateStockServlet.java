package Book;

import Borrow_Return.Borrow;
import Borrow_Return.BorrowDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateStockServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false).getAttribute("Librarian_Id") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request,response);
        }
        int status = BookDao.updateBookStock(
                Integer.parseInt(request.getParameter("Book_Id")),
                Integer.parseInt(request.getParameter("Nos_Available"))
        );
        if(status != 0){
            request.setAttribute("message-type","success");
            request.setAttribute("message","Stock Updated Successfully");
        }
        else{
            request.setAttribute("message-type","error");
            request.setAttribute("message","Error Occurred");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
        dispatcher.forward(request,response);
    }
}
