package Fine;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fine> allFine = new ArrayList<Fine>();
        allFine = FineDao.getALlFine();
        request.setAttribute("allFine",allFine);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manage-fine.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
