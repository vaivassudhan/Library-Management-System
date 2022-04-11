package Borrow_Return;

import Utils.Util;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class ConfirmReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("GET not available"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
//      Handling json request
        String jb = Util.jsonRequestHandler(request);
        Gson gson = new Gson();
//      Json to Java POJO
        Borrow borrow = gson.fromJson(String.valueOf(jb), Borrow.class);

        int status = BorrowDao.confirmReturn(borrow);
        if(status != 0){
            response.setStatus(HttpURLConnection.HTTP_OK);
            out.write(Util.successMessageJson("Book Returned successfully!"));
        }
        else{
            response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
            out.write(Util.createErrorJson("Error Occurred "));
        }
        out.flush();
    }
}
