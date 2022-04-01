package Book;

import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SearchByAuthorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

//      Handling json request
        String jb = Util.jsonRequestHandler(request);
        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

        String Author_Name = jsonObject.get("Author_Name").getAsString();
        List<Book> allBook = BookDao.searchByAuthor(Author_Name);
        String bookJson = new Gson().toJson(allBook);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        out.print(bookJson);
        out.flush();
    }
}
