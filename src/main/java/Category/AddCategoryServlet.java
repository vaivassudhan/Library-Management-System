package Category;

import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String jb = Util.jsonRequestHandler(request);
        Gson gson = new Gson();
        Category category = gson.fromJson(String.valueOf(jb), Category.class);

        System.out.println(category.getCategory_Name());

        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();

        if(category.getCategory_Name().equals("") || category.getCategory_Name().length() < 3){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Category Name not valid");
            out.write(String.valueOf(jsonobject));
            return;
        }

        int status = CategoryDao.addCategory(category);
        if(status > 0){
//            PrintWriter out = response.getWriter();
            response.setStatus(200);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            jsonobject.addProperty("message-type","success");
            jsonobject.addProperty("message","Category added successfully!");
            out.write(String.valueOf(jsonobject));

        }
        else{
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Some error occured");
            out.write(String.valueOf(jsonobject));
            response.sendError(500);

        }
    }
}
