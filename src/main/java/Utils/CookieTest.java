package Utils;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class CookieTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try {
            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_IqhGeEi5hICFZg","xftTeCetY2Z8YdVOuFuTjUvC");
            JSONObject options = new JSONObject();
            options.put("amount", 100);
            options.put("currency", "INR");
            options.put("receipt", "txn_123456");
            Order order = razorpayClient.Orders.create(options);
            System.out.println(order);
        } catch (RazorpayException e) {
            e.printStackTrace();
        }
    }
}
