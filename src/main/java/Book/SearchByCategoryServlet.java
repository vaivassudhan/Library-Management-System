package Book;

import Category.CategoryDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class SearchByCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int Category_Id = Integer.parseInt(request.getParameter("Category_Id"));
        List<Book> allBook= BookDao.searchByCategory(Category_Id);
        String category_name = CategoryDao.getCategoryNameById(Integer.parseInt(request.getParameter("Category_Id")));
        request.setAttribute("allBook",allBook);
        request.setAttribute("Category_Name",category_name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category-books.jsp");
        dispatcher.forward(request,response);
    }
}
