package Librarian;

import Utils.Util;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class AddLibrarianServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String jb = Util.jsonRequestHandler(request);

        Librarian librarian = new Gson().fromJson(jb,Librarian.class);

//      checking edge cases
        if(Objects.equals(librarian.getLibrarian_Id(), "")
                || Objects.equals(librarian.getName(), "")
                || Objects.equals(librarian.getMobile(),"")){
            out.write(Util.createErrorJson("Invalid Data"));
            return;
        }
        if(librarian.getMobile().length() < 10){
            out.write(Util.createErrorJson("Invalid Mobile"));
            return;
        }

        int status = LibrarianDao.addLibrarian(librarian);
        if(status != 0 ){
            out.write(Util.successMessageJson("Librarian Added"));
        }
        else{
            out.write(Util.createErrorJson("Internal error occurred"));
        }
    }
}
