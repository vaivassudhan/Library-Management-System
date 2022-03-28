package Fine;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateFineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status = FineDao.updateFine(
                Integer.parseInt(request.getParameter("Group_Id")),
                Integer.parseInt(request.getParameter("Per_Day_Amount"))
        );
        if(status != 0 ){
            request.setAttribute("message-type","success");
            request.setAttribute("message","Fine Amount Updated Successfully");
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }
        else{
            request.setAttribute("message-type","error");
            request.setAttribute("message","Some Error Occurred");
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }
    }
}
