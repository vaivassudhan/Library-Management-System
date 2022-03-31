package Librarian;

import Utils.JWebToken;
import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class TokenTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      Handling json request
        System.out.println("HELLO TOKEN TEST");

        String token = request.getHeader("Authorization").split(" ")[1];
        System.out.println(" dkjdkj " + token);

        System.out.println(token);

        JWebToken incomingToken = null;
        try {
            incomingToken = new JWebToken(token);
            System.out.println("INCOMING TOKEn  " + incomingToken.getId() + "  " + incomingToken.getRole());
            String Librarian_Id = incomingToken.getId();
//            int Role = incomingToken.getRole();
//            System.out.println("IS INVALID");
//            System.out.println(Librarian_Id);
//            System.out.println(Role);
//            if (!incomingToken.isValid()) {
//                String audience = String.valueOf(incomingToken.getAudience());
//                String subject = incomingToken.getSubject();
//                System.out.println("IS INVALID");
//                System.out.println(audience);
//                System.out.println(subject);
//            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
