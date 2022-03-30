package Book;

import Borrow_Return.Borrow;
import Borrow_Return.BorrowDao;
import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateStockServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();

        jsonobject.addProperty("message-type","error");
        jsonobject.addProperty("message","Get method not available");
        out.write(String.valueOf(jsonobject));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      Handling json request
        String jb = Util.jsonRequestHandler(request);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

//      Get Values from json
        int Book_Id = jsonObject.get("Book_Id").getAsInt();
        int Nos_Available = jsonObject.get("Nos_Available").getAsInt();

        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();

        if(Nos_Available < 0 ){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Stock not valid");
            out.write(String.valueOf(jsonobject));
            return;
        }

        int status = BookDao.updateBookStock( Book_Id , Nos_Available );
        if(status != 0){
            jsonobject.addProperty("message-type","success");
            jsonobject.addProperty("message","Stock Updated Successfully!");
        }
        else{
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Some Error Occurred");
        }
        out.write(String.valueOf(jsonobject));
    }
}
