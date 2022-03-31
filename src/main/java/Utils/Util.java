package Utils;

import Book.Book;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    public static String jsonRequestHandler(HttpServletRequest req){
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(req.getReader());
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        }
        catch (Exception e) {
            // crash and burn
            e.printStackTrace();
        }
        return String.valueOf(jb);
    }
    public static boolean verifyAuth(String token){
        JWebToken incomingToken = null;
        try {
            incomingToken = new JWebToken(token);
            int Role = Integer.parseInt(incomingToken.getRole());
            if (Role == 1 || Role == 2) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isAdmin(String token){
        JWebToken incomingToken = null;
        try {
            incomingToken = new JWebToken(token);
            int Role = Integer.parseInt(incomingToken.getRole());
            if (Role == 1) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

//  Function to create JSON Error message
    public static String createErrorJson(String message){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message-type","error");
        jsonObject.addProperty("message",message);

        return String.valueOf(jsonObject);
    }
//  Function to create JSON Success Message
    public static String successMessageJson(String message){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message-type","success");
        jsonObject.addProperty("message",message);

        return String.valueOf(jsonObject);
    }
}
