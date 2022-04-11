package Book;

import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class UpdateStockServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("GET not available"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
//      Read Token From Response Header

//      Handling json request
        String jb = Util.jsonRequestHandler(request);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

//      Get Values from json
        int Book_Id = jsonObject.get("Book_Id").getAsInt();
        int Nos_Available = jsonObject.get("Nos_Available").getAsInt();

//      Check Edge cases
        if(Nos_Available < 0 ){
            response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
            out.write(Util.createErrorJson("Stock not valid"));
            out.flush();
            return;
        }

        int status = BookDao.updateBookStock( Book_Id , Nos_Available );
        if(status != 0){
            response.setStatus(HttpURLConnection.HTTP_OK);
            out.write(Util.successMessageJson("Stock updated successfully!"));
        }
        else{
            response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
            out.write(Util.createErrorJson("Some Error Occurred!"));
        }
        out.flush();
    }
}
