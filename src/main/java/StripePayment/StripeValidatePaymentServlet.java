package StripePayment;

import Utils.Util;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class StripeValidatePaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String jb = Util.jsonRequestHandler(request);
        System.out.println(jb);

        JSONObject jsonObject = new JSONObject(jb).getJSONObject("data").getJSONObject("object");
        String session_id = jsonObject.getString("id");
        String payment_status = jsonObject.getString("payment_status");
        int status = StripePaymentDao.updateStripeStatus(session_id,payment_status);


        PrintWriter out = response.getWriter();
        if(status != 0){
            response.setStatus(HttpURLConnection.HTTP_OK);
            out.print(Util.successMessageJson("Payment Successful"));
            out.flush();
        }
        else{
            response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
            out.print(Util.createErrorJson("Payment Failed"));
            out.flush();
        }


    }
}
