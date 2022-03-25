package Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        if(req.getSession(false) == null){
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
            dispatcher.forward(req,res);
        }
        Book book = new Book();
        book.setBook_Title(req.getParameter("Book_Title"));
        book.setAuthor_Name(req.getParameter("Author_Name"));
        book.setCategory_Id(Integer.parseInt(req.getParameter("Category_Id")));
        book.setNos_Available(Integer.parseInt(req.getParameter("Nos_Available")));
        book.setPublished_year(Integer.parseInt(req.getParameter("Published_Year")));

        int status = BookDao.addBook(book);

        List<Book> allBook = BookDao.getALlBooks();
        req.setAttribute("allBook",allBook);

        if(status > 0){
            RequestDispatcher dispatcher = req.getRequestDispatcher("book-list.jsp");
            dispatcher.forward(req,res);
        }
        else{
            req.setAttribute("message-type","error");
            req.setAttribute("message","Some Error Occurred. ");
        }

    }
    protected  void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

    }
}
