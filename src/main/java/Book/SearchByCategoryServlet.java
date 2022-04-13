package Book;

import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SearchByCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();


//      Handling json request
        String jb = (String) request.getAttribute("requestJson");
        System.out.println(jb);
        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

        int Category_Id = jsonObject.get("Category_Id").getAsInt();
        List<Book> allBook= BookDao.searchByCategory(Category_Id);
        String bookJson = new Gson().toJson(allBook);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        out.write(bookJson);
        out.flush();

    }
}
