package Borrow_Return;

import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

public class ReturnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("GET not available"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String jb = Util.jsonRequestHandler(request);


        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();



//      Get Values from json
        int Borrow_Id = jsonObject.get("Borrow_Id").getAsInt();
        Borrow borrow = BorrowDao.returnBook(Borrow_Id);
        if(borrow.getBorrow_Id() == 0){
            out.write(Util.createErrorJson("Invalid borrow ID"));
            return;
        }
        String borrowJson = new Gson().toJson(borrow);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        out.print(borrowJson);
        out.flush();

    }
}
