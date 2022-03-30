package Borrow_Return;

import Category.Category;
import Utils.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class AddBorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jb = Util.jsonRequestHandler(request);
        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();
        System.out.println("CEHCK ADD Borrow Servlet" + jsonObject);


        int Book_Id = jsonObject.get("Book_Id").getAsInt();
        int Student_Id = jsonObject.get("Student_Id").getAsInt();
        String Issued_By = jsonObject.get("Issued_By").getAsString();

        System.out.println("CEHCK ADD Borrow Servlet");
        JsonObject jsonobject = new JsonObject();
        PrintWriter out = response.getWriter();

        if(Objects.equals(Issued_By, "")){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Invalid librarian");
            out.write(String.valueOf(jsonobject));
            return;
        }

        Borrow borrow = new Borrow();
        borrow.setBook_Id(Book_Id);
        borrow.setStudent_Id(Student_Id);
        borrow.setIssued_By(Issued_By);

        System.out.println("check Borrow servlet");
        int borrow_id = BorrowDao.borrowBook(borrow);


        if(borrow_id == 0){
            jsonobject.addProperty("message-type","error");
            jsonobject.addProperty("message","Error Occurred.");
            out.write(String.valueOf(jsonobject));
            return;
        }
        if(borrow_id != 0 ){
            jsonobject.addProperty("message-type","success");
            jsonobject.addProperty("message","Book Issued Successfully. Borrow ID is " + borrow_id);
            out.write(String.valueOf(jsonobject));
            return;
        }

    }
}
