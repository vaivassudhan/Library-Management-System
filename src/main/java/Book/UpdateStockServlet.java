package Book;

import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

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
        String token = request.getHeader("Authorization").split(" ")[1];
//      Auth Check
        if(!Util.verifyAuth(token)){
            out.write(Util.createErrorJson("UnAuthorized"));
            response.setStatus(401);
        }

//      Handling json request
        String jb = Util.jsonRequestHandler(request);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

//      Get Values from json
        int Book_Id = jsonObject.get("Book_Id").getAsInt();
        int Nos_Available = jsonObject.get("Nos_Available").getAsInt();

//      Check Edge cases
        if(Nos_Available < 0 ){
            out.write(Util.createErrorJson("Stock not valid"));
            return;
        }

        int status = BookDao.updateBookStock( Book_Id , Nos_Available );
        if(status != 0){
            out.write(Util.successMessageJson("Stock updated successfully!"));
        }
        else{
            out.write(Util.createErrorJson("Some Error Occurred!"));
        }
    }
}
