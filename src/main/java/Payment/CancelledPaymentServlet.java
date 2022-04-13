package Payment;

import Utils.Keys;
import Utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class CancelledPaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jb = Util.jsonRequestHandler(request);
        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();
        PrintWriter out = response.getWriter();
        int borrow_id = jsonObject.get("Borrow_Id").getAsInt();
        int status = PaymentDao.updatePaymentStatus(Keys.PAYMENT_CANCELLED,borrow_id);
        if(status != 0){
            response.setStatus(HttpURLConnection.HTTP_OK);
            out.write(Util.successMessageJson("Payment Cancelled Successfully"));
            out.flush();
        }
        else{
            response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
            out.write("Error occurred. Please check borrow id");
            out.flush();
        }
    }

}
