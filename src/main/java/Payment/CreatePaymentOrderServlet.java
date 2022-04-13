package Payment;

import Borrow_Return.Borrow;
import Utils.Keys;
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
            Borrow borrow = gson.fromJson(String.valueOf(jb), Borrow.class);

            PrintWriter out = response.getWriter();

            RazorpayClient razorpayClient = new RazorpayClient(PaymentUtils.getRazorKey(),PaymentUtils.getRazorSecret());
            JSONObject options = new JSONObject();
            options.put("amount", borrow.getFine_Paid()*100);
            options.put("currency", PaymentKeys.INR_CURRENCY);
            options.put("receipt", PaymentKeys.RECEIPT_PREFIX +borrow.getBorrow_Id());
            Order order = razorpayClient.Orders.create(options);

            Payment paymentDetails = new Payment();
            paymentDetails.setBorrow_Id(borrow.getBorrow_Id());
            paymentDetails.setStudent_Id(borrow.getStudent_Id());
            paymentDetails.setOrder_id(order.get("id").toString());
            paymentDetails.setAmount(borrow.getFine_Paid());
            paymentDetails.setStatus(Keys.PAYMENT_CREATED);
            int status = PaymentDao.addPaymentDetails(paymentDetails);
            if(status == 0){
                response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
                out.println("Payment Details not added");
                out.flush();
            }
            else {
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.setContentType("application/json");
                response.setStatus(HttpURLConnection.HTTP_OK);
                out.print(order);
                out.flush();
            }

        } catch (RazorpayException e) {
            e.printStackTrace();
        }
    }
}
