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
import java.net.HttpURLConnection;
import java.util.List;

public class GetBookByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HELLO FROM VIEW SERVLET");
        String jb = Util.jsonRequestHandler(request);
        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();


        int pageNo = jsonObject.get("pageNo").getAsInt();
        int pageSize = jsonObject.get("pageSize").getAsInt();
        System.out.println(pageNo+" : "+pageSize);
        List<Book> booksByPage =  BookDao.getBooksByPage(pageNo, pageSize);

        String bookJson = new Gson().toJson(booksByPage);
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setStatus(HttpURLConnection.HTTP_OK);
        out.print(bookJson);
        out.flush();
    }
}
