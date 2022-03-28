package Group_days;

import Fine.Fine;
import Fine.FineDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("Librarian_Id") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request,response);
        }
        GroupDays groupDays = new GroupDays();
        groupDays.setDays(Integer.parseInt(request.getParameter("Days")));
        int group_id = GroupDaysDAO.addGroup(groupDays);
        Fine fine = new Fine();
        fine.setGroup_Id(group_id);
        fine.setFine_Per_Day(Integer.parseInt(request.getParameter("Fine_Per_Day")));
        int status = FineDao.addFine(fine);
        if(status != 0 ){
            request.setAttribute("message-type","success");
            request.setAttribute("message","Group Added Successfully");
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }
        else{
            request.setAttribute("message-type","error");
            request.setAttribute("message","Error Occurred");
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }
    }
}
