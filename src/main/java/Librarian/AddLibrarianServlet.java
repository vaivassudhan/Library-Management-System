package Librarian;

import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class AddLibrarianServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jb = Util.jsonRequestHandler(request);

        Librarian librarian = new Gson().fromJson(jb,Librarian.class);
        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();

        if(Objects.equals(librarian.getLibrarian_Id(), "")
                || Objects.equals(librarian.getName(), "")
                || Objects.equals(librarian.getMobile(),"")){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Invalid Data");
            out.write(String.valueOf(jsonobject));
            return;
        }
        if(librarian.getMobile().length() < 10){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Invalid Mobile");
            out.write(String.valueOf(jsonobject));
            return;
        }

//
//        Librarian librarian = new Librarian();
//        librarian.setLibrarian_Id(request.getParameter("Librarian_Id"));
//        librarian.setName(request.getParameter("Name"));
//        librarian.setRole(Integer.parseInt(request.getParameter("Role")));
//        librarian.setGender(request.getParameter("Gender"));

        int status = LibrarianDao.addLibrarian(librarian);
        if(status != 0 ){
            jsonobject.addProperty("message-type","success");
            jsonobject.addProperty("message","Added Librarian");
            out.write(String.valueOf(jsonobject));
        }
        else{
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Some Error Occured");
            out.write(String.valueOf(jsonobject));
        }
    }
}
