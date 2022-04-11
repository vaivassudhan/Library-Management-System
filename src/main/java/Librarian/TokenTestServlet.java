package Librarian;

import Utils.JWebToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class TokenTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      Handling json request
        System.out.println("TOKEN TEST");

        String token = request.getHeader("Authorization").split(" ")[1];
        System.out.println(" TOKEN " + token);

        System.out.println(token);

        JWebToken incomingToken = null;
        try {
            incomingToken = new JWebToken(token);
            System.out.println("INCOMING TOKEN  " + incomingToken.getId() + "  " + incomingToken.getRole());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
