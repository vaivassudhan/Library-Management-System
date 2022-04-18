package StripePayment;

import Book.Book;
import Utils.Util;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StripePaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Stripe.apiKey = "sk_test_51KpnBESE7P9CAQ0GHzpMQu36e6ER1WpkRQinq6VDYie9VdcoDfBvI46P93CmwxDDh0QkAmWeRUjRt8ECcCayuAh800017LkWBH";
        String jb = Util.jsonRequestHandler(request);
        Gson gson = new Gson();
        StripePayment payment = gson.fromJson(String.valueOf(jb), StripePayment.class);


        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(
                        payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                        .builder().setName(payment.getName()).build())
                                                .build())
                                .build())
                .build();

//        Stripe.apiKey = "sk_test_51KpnBESE7P9CAQ0GHzpMQu36e6ER1WpkRQinq6VDYie9VdcoDfBvI46P93CmwxDDh0QkAmWeRUjRt8ECcCayuAh800017LkWBH";
//
//        List<Object> lineItems = new ArrayList<>();
//        Map<String, Object> lineItem1 = new HashMap<>();
//        lineItem1.put("price", payment.getAmount());
//        lineItem1.put("quantity",payment.getQuantity());
//        lineItems.add(lineItem1);
//        Map<String, Object> params = new HashMap<>();
//        params.put(
//                "success_url",
//                payment.getSuccessUrl()
//        );
//        params.put(
//                "cancel_url",
//                payment.getCancelUrl()
//        );
//        params.put("line_items", lineItems);
//        params.put("mode", "payment");


        // create a stripe session
        Session session = null;
        try {
            session = Session.create(params);
            payment.setId(session.getId());
        } catch (StripeException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        System.out.println(session.getId());
        int status = StripePaymentDao.addStripePaymentDetails(payment);
        if(status != 0 ){
            Map<String, String> responseData = new HashMap<>();
            // We get the sessionId and we putted inside the response data you can get more info from the session object
            responseData.put("id", session.getId());
            // We can return only the sessionId as a String
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/json");
            response.setStatus(HttpURLConnection.HTTP_OK);
            out.print(gson.toJson(responseData));
            System.out.println(jb);
            out.flush();
        }
        else{
            out.print(Util.createErrorJson("Error in adding payment details"));
            response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
            out.flush();
        }

    }
}
