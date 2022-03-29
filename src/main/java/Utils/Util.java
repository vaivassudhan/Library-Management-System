package Utils;

import Book.Book;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

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
}
