package Category;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetCategoryListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> allCategoryList = CategoryDao.getAllCategory();
        String bookJson = new Gson().toJson(allCategoryList);
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(bookJson);
        out.flush();
//        request.setAttribute("allCategoryList",allCategoryList);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("category-list.jsp");
//        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
