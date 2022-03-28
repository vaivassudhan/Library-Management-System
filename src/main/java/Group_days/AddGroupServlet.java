package Group_days;

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
        int status = GroupDaysDAO.addGroup(groupDays);
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
