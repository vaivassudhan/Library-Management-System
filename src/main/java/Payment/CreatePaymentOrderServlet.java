package Payment;

import Borrow_Return.Borrow;
import Utils.Util;
import com.google.gson.Gson;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class CreatePaymentOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String jb = Util.jsonRequestHandler(request);
            System.out.println(jb);
            Gson gson = new Gson();
            //      Json to Java POJO
            Borrow borrow = gson.fromJson(String.valueOf(jb), Borrow.class);

            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_IqhGeEi5hICFZg","xftTeCetY2Z8YdVOuFuTjUvC");
            JSONObject options = new JSONObject();
            options.put("amount", borrow.getFine_Paid()*100);
            options.put("currency", "INR");
            options.put("receipt", "txn_"+borrow.getBorrow_Id()+"1");
            Order order = razorpayClient.Orders.create(options);
            PrintWriter out = response.getWriter();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/json");
            response.setStatus(HttpURLConnection.HTTP_OK);
            out.print(order);
            out.flush();

        } catch (RazorpayException e) {
            e.printStackTrace();
        }
    }
}
