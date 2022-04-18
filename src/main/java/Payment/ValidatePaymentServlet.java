package Payment;

import Utils.Keys;
import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.razorpay.RazorpayException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.Objects;
// Servlet to check if the payment is successful or not. This also verifies the amount paid by the user.
public class ValidatePaymentServlet extends HttpServlet {
    public static String hmac_sha256(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String secret = PaymentUtils.getRazorSecret();
        String jb = Util.jsonRequestHandler(request);
        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();
        String razorpay_payment_id = jsonObject.get("razorpay_payment_id").getAsString();
        int borrow_id = jsonObject.get("Borrow_Id").getAsInt();
        int amount = jsonObject.get("amount").getAsInt();

        String order_id = PaymentDao.getOrderId(borrow_id);
        String razorpay_signature = jsonObject.get("razorpay_signature").getAsString();

        String generated_signature = null;
        try {
            generated_signature = hmac_sha256(secret,order_id + "|" + razorpay_payment_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(generated_signature);
        System.out.println(razorpay_signature);
        PrintWriter out = response.getWriter();
        if (Objects.equals(generated_signature, razorpay_signature)) {
            System.out.println("PAYMENT SUCCESSFUL");
            int status = PaymentDao.updatePaymentDetails(Keys.PAYMENT_CAPTURED,razorpay_payment_id,borrow_id);
            if(status != 0) {
                try {
                    int captured = PaymentUtils.capturePayment(amount, borrow_id);
                    if(captured != 0) {
                        response.setStatus(HttpURLConnection.HTTP_OK);
                        out.print(Util.successMessageJson("Payment Successful"));
                        out.flush();
                    }
                } catch (RazorpayException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            System.out.println("PAYMENT FAILED");
            PaymentDao.updatePaymentStatus(Keys.PAYMENT_FAILED,borrow_id);
            out.print(Util.createErrorJson("Payment Failed"));
            out.flush();
        }


    }

}
