package Librarian;

import Utils.JWebToken;
import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        request.getHeaders("");

        String jb = Util.jsonRequestHandler(request);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();
        String Librarian_Id = jsonObject.get("Librarian_Id").getAsString();
        String Password = jsonObject.get("Password").getAsString();


//        String Librarian_Id = request.getParameter("Librarian_Id");
//        String Password = request.getParameter("Password");
        boolean status = LibrarianDao.validate(Librarian_Id,Password);

        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();
        Librarian librarian = LibrarianDao.getDataById(Librarian_Id);
        if(status){

            final int EXPIRY_DAYS = 90;

            JSONObject jwtPayload = new JSONObject();
            try {
                jwtPayload.put("status", 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String token = "";
            try {
                System.out.println("LOGIN SERVLET "+ librarian.getRole());
                jwtPayload.put("Librarian_Id",librarian.getLibrarian_Id() );
                jwtPayload.put("Role", String.valueOf(librarian.getRole()));
                LocalDateTime ldt = LocalDateTime.now().plusDays(EXPIRY_DAYS);
                jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC)); //this needs to be configured
                token = new JWebToken(jwtPayload).toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }


            response.setStatus(200);
            jsonobject.addProperty("token",token);
            jsonobject.addProperty("Librarian_Id",librarian.getLibrarian_Id());
            jsonobject.addProperty("Name",librarian.getName());
            jsonobject.addProperty("Role",librarian.getRole());
            out.write(String.valueOf(jsonobject));
            out.flush();
        }
        else{
            jsonobject.addProperty("error-message","Invalid librarian id or password");
            out.write(String.valueOf(jsonobject));
            out.flush();
        }
//        response.setContentType("text/html");
//        if(status){
//            Librarian librarian = LibrarianDao.getDataById(Librarian_Id);
//            HttpSession session = request.getSession();
//            session.setAttribute("Librarian_Id",Librarian_Id);
//            session.setAttribute("Name",librarian.getName());
//            session.setAttribute("Role",librarian.getRole());
//            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//            dispatcher.forward(request,response);
//        }
//        else{
//            request.setAttribute("message-type","error");
//            request.setAttribute("message","Invalid Username or Password. Please check your credentials.");
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
//            dispatcher.include(request,response);
//
//
//
//        }
    }
}
