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
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("GET not available"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
//      Read Token From Response Header
        String token = request.getHeader("Authorization").split(" ")[1];
//      Auth Check
        if(!Util.verifyAuth(token)){
            out.write(Util.createErrorJson("UnAuthorized"));
            response.setStatus(401);
        }

//      Handle JSON Request
        String jb = Util.jsonRequestHandler(request);
        JsonObject jsonObject = new JsonParser().parse(jb).getAsJsonObject();

        int Book_Id = jsonObject.get("Book_Id").getAsInt();
        int Student_Id = jsonObject.get("Student_Id").getAsInt();
        String Issued_By = jsonObject.get("Issued_By").getAsString();

//      Check Edge Cases
        if(Objects.equals(Issued_By, "")){
            out.write(Util.createErrorJson("Invalid librarian"));
            return;
        }
        if(Book_Id == 0 || Student_Id == 0){
            out.write(Util.createErrorJson("Invalid librarian"));
            return;
        }

        Borrow borrow = new Borrow();
        borrow.setBook_Id(Book_Id);
        borrow.setStudent_Id(Student_Id);
        borrow.setIssued_By(Issued_By);

        int borrow_id = BorrowDao.borrowBook(borrow);
        if(borrow_id == 0){
            out.write(Util.createErrorJson("Error Occurred"));
        }
        else{
            out.write(Util.successMessageJson("Book Issued Successfully. Borrow ID is " + borrow_id));
        }

    }
}
