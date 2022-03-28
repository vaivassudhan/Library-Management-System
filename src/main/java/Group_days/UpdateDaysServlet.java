package Group_days;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateDaysServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int group_id = Integer.parseInt(request.getParameter("Group_Id"));
            int days = Integer.parseInt(request.getParameter("Days"));

            int status = GroupDaysDAO.updateGroup(group_id,days);
            if(status != 0 ){
                request.setAttribute("message-type","success");
                request.setAttribute("message","Group Updated Successfully");
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
