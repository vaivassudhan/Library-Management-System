package Payment;

import Utils.Util;
import com.google.gson.JsonObject;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class CapturedWebhookHandler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String jb = Util.jsonRequestHandler(request);
        System.out.println(jb);
        JSONObject jsonObject = new JSONObject(jb).getJSONObject("payload").getJSONObject("payment").getJSONObject("entity");
        String order_id = jsonObject.getString("order_id");
        String razorpay_payment_id = jsonObject.getString("id");
        String payment_status = jsonObject.getString("status");
        int amount = jsonObject.getInt("amount")/100;

        int status = PaymentDao.capturedWebhookHandler(razorpay_payment_id,order_id, amount, payment_status);

        PrintWriter out = response.getWriter();
        if(status == 1) {
            int borrow_id = PaymentDao.getBorrowId(order_id);
            if(borrow_id != 0) {
                try {
                    int captured = PaymentUtils.capturePayment(borrow_id, amount);
                    if(captured != 0) {
                        response.setStatus(HttpURLConnection.HTTP_OK);
                        out.print(Util.successMessageJson("Payment Captured Successfully"));
                        out.flush();
                    }
                } catch (RazorpayException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
            out.print(Util.createErrorJson("Payment already captured"));
            out.flush();
        }

    }
}
