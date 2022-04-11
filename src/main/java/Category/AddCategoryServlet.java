package Category;

import Utils.Util;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("GET not available"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String jb = Util.jsonRequestHandler(request);
        Gson gson = new Gson();
        Category category = gson.fromJson(String.valueOf(jb), Category.class);

        if(category.getCategory_Name().equals("") || category.getCategory_Name().length() < 3){
            out.write(Util.createErrorJson("Category name is not valid"));
            return;
        }

        int status = CategoryDao.addCategory(category);
        if(status > 0){
            response.setStatus(200);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.write(Util.successMessageJson("Category added successfully!"));

        }
        else{
            out.write(Util.createErrorJson("Some error occurred"));
            response.sendError(500);

        }
    }
}
