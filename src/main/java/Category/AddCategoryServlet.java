package Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false) == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false) == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request,response);
        }
        String category_name = request.getParameter("category_name");
        Category category = new Category();
        category.setCategory_Name(category_name);
        int status = CategoryDao.addCategory(category);
        PrintWriter out = response.getWriter();
        if(status != 0){
            request.setAttribute("message-type","success");
            request.setAttribute("message","Category Added ");
        }
        else{
            request.setAttribute("message-type","error");
            request.setAttribute("message","Error Occurred, Please try later. ");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
        dispatcher.forward(request,response);
    }
}
