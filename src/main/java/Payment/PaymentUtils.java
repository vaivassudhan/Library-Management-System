package Payment;

import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Properties;



public class PaymentUtils {

//  Util function to get razor key
    public static String getRazorKey(){
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            properties.load(classLoader.getResourceAsStream("/secrets.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String razorpayKey = properties.getProperty("razorpayKey");
        return razorpayKey;
    }
//   Util function to get razor secret key
    public static String getRazorSecret(){
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            properties.load(classLoader.getResourceAsStream("/secrets.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String razorpaySecret = properties.getProperty("razorpaySecret");
        return razorpaySecret;
    }


//  Capturing payment and verifying the amount
    public static int capturePayment(int amount,int borrow_id) throws RazorpayException, IOException {
        int status = 0;
        System.out.println("AMOUNT " + amount);
        amount = amount * 100;
        RazorpayClient razorpay = new RazorpayClient(getRazorKey(), getRazorSecret());
        try {
            JSONObject captureRequest = new JSONObject();
            captureRequest.put("amount", amount);
            captureRequest.put("currency", PaymentKeys.INR_CURRENCY);

            String paymentId = PaymentDao.getPaymentId(borrow_id);

            Payment payment = razorpay.Payments.capture(paymentId, captureRequest);
            if(payment.get("status").equals("captured")) {
                status = 1;
            }
        } catch (RazorpayException e) {
            // Handle Exception
            e.printStackTrace();
        }
        return status;
    }
}
