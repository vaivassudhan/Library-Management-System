package Librarian;

import Utils.JWebToken;
import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jb = Util.jsonRequestHandler(request);

        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();
        String Librarian_Id = jsonObject.get("Librarian_Id").getAsString();
        String Password = jsonObject.get("Password").getAsString();

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
                jwtPayload.put("Librarian_Id",librarian.getLibrarian_Id() );
                jwtPayload.put("Role", String.valueOf(librarian.getRole()));
                LocalDateTime ldt = LocalDateTime.now().plusDays(EXPIRY_DAYS);
                jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC));
                token = new JWebToken(jwtPayload).toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }
            response.setStatus(200);
            jsonobject.addProperty("token",Util.encryptToken(token));
            jsonobject.addProperty("Librarian_Id",librarian.getLibrarian_Id());
            jsonobject.addProperty("Name",librarian.getName());
            jsonobject.addProperty("Role",librarian.getRole());
            out.write(String.valueOf(jsonobject));
            out.flush();
        }
        else{
            out.write(Util.createErrorJson("Invalid Librarian ID or Password"));
            out.flush();
        }
    }
}
