package Borrow_Return;

import Book.BookDao;
import DBConnection.DBConnection;
import Student.StudentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BorrowDao {
    //  Function to Borrow book
    public static int borrowBook(Borrow borrow){
        int borrow_id = 0;
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();

//          Check for book stock
            PreparedStatement statement = con.prepareStatement("SELECT Nos_Available FROM Book WHERE Book_Id = ?");
            statement.setInt(1,borrow.getBook_Id());
            ResultSet result = statement.executeQuery();
            int nos_available = 0 ;
            while(result.next()){
                nos_available = result.getInt(1);
            }
            if(nos_available >= 1){

                String sql = "INSERT INTO Borrow(Book_Id, Student_Id, Issued_By, Borrow_Date, DueDate) VALUES (?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setInt(1,borrow.getBook_Id());
                ps.setInt(2,borrow.getStudent_Id());
                ps.setString(3, borrow.getIssued_By());
//              Get current date
                long millis=System.currentTimeMillis();
                java.sql.Date cur_date=new java.sql.Date(millis);
                ps.setDate(4,cur_date);

//              Get no of days
                int no_of_days = StudentDao.getDaysBySid(borrow.getStudent_Id());
                Calendar c = Calendar.getInstance();
                c.setTime(cur_date); // Using today's date
                c.add(Calendar.DATE,no_of_days);
                long added_millis = c.getTimeInMillis();
                java.sql.Date due_date = new java.sql.Date(added_millis);
                ps.setDate(5,due_date);

//              Executing the query
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                borrow_id = rs.getInt(1);

                if(rs.getInt(1) != 0  ){
//                  Reduce Book Stock in Book Table
                    ps = con.prepareStatement("UPDATE Book SET Nos_Available = Nos_Available - 1 WHERE Book_Id = ? ");
                    ps.setInt(1,borrow.getBook_Id());
                    status = ps.executeUpdate();
                }
                con.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(status != 0){
            return borrow_id;
        }
        return 0;
    }

    //  Function to get All Borrowed List
    public static List<Borrow> getAllBorrow(){
        List<Borrow> allBorrow = new ArrayList<>();
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT b.Book_Title, s.Student_Name, br.Issued_By, br.Borrow_Date, br.Return_Date, br.Fine_Paid, br.Borrow_Id, br.DueDate FROM Book b, Student s, Borrow br WHERE (s.Student_Id = br.Student_Id AND b.Book_Id = br.Book_Id) AND br.Return_Date is NULL;");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Borrow borrow = new Borrow();
                borrow.setBook_Name(result.getString(1));
                borrow.setStudent_Name(result.getString(2));
                borrow.setIssued_By(result.getString(3));
                borrow.setBorrow_Date(result.getDate(4));
                borrow.setReturn_Date(result.getDate(5));
                borrow.setFine_Paid(result.getFloat(6));
                borrow.setBorrow_Id(result.getInt(7));
                borrow.setDueDate(result.getDate(8));
                allBorrow.add(borrow);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBorrow;
    }

    public static long daysDifference(Date from_date, Date return_date){
        long time_difference = return_date.getTime() - from_date.getTime();
        return (time_difference / (1000*60*60*24)) % 365;

    }

    public static Borrow returnBook(int borrow_id){
        Borrow borrow = new Borrow();
        borrow.setBorrow_Id(borrow_id);
        long days_difference = 0;
        try{
            Connection con = DBConnection.getConnection();
//          Get borrow details
            PreparedStatement ps = con.prepareStatement("SELECT br.Borrow_Id,br.Book_Id, br.Student_Id, br.Issued_By, br.Borrow_Date, b.Book_Title,br.Return_Date, s.Student_Name, br.DueDate FROM Borrow br, Student s, Book b WHERE br.Book_Id = b.Book_Id AND br.Student_Id = s.Student_Id AND br.Return_Date is null AND  br.Borrow_Id = ?;");
            ps.setInt(1,borrow_id);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                borrow.setBorrow_Id(result.getInt(1));
                borrow.setBook_Id(result.getInt(2));
                borrow.setStudent_Id(result.getInt(3));
                borrow.setIssued_By(result.getString(4));
                borrow.setBorrow_Date(result.getDate(5));

//              Additional fields
                borrow.setBook_Name(result.getString(6));
                borrow.setReturn_Date(result.getDate(7));
                borrow.setStudent_Name(result.getString(8));
                borrow.setDueDate(result.getDate(9));

            }
            long millis=System.currentTimeMillis();
            Date return_date = new Date(millis);

//          update return date in object
            days_difference = daysDifference(borrow.getDueDate(),return_date);

//          Get fine amount per day query
            ps = con.prepareStatement("SELECT * FROM Fine WHERE Group_Id = ( SELECT Group_Id FROM Student WHERE Student_Id = ?)");
            ps.setInt(1,borrow.getStudent_Id());
            result = ps.executeQuery();
            int fine_per_day = 0;

            if(result.next()){
//              Setting group id as additional field in borrow object
                borrow.setGroup_Id(result.getInt(1));
                fine_per_day = result.getInt(2);
            }
//          Get number of days query
//            ps = con.prepareStatement("SELECT * FROM Group_Days WHERE Group_Id = ?");
//            ps.setInt(1,borrow.getGroup_Id());
//            result = ps.executeQuery();
//            int no_of_days = 0;
//            if(result.next()){
//                no_of_days = result.getInt(2);
//            }

//          Calculate Fine
//            float fine_to_pay = 0 ;
//            if(days_difference > no_of_days){
//                fine_to_pay = (float) fine_per_day * (days_difference - no_of_days);
//            }
            float fine_to_pay = 0;
            if(days_difference > 0 ){
                fine_to_pay = (float) fine_per_day * days_difference ;
            }

            borrow.setFine_Paid(fine_to_pay);

//            Update DB and object with return date
            borrow.setReturn_Date(return_date);
        con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrow;
    }

    public static int confirmReturn(Borrow borrow){
        int status = 0 ;
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Borrow SET Return_Date = ?  WHERE Borrow_Id = ?");
            ps.setDate(1,borrow.getReturn_Date());
            ps.setInt(2,borrow.getBorrow_Id());
            status = ps.executeUpdate();
            if(status != 0){
                ps = con.prepareStatement("UPDATE Borrow SET Fine_Paid = ? WHERE Borrow_Id = ?");
                ps.setFloat(1,borrow.getFine_Paid());
                ps.setInt(2,borrow.getBorrow_Id());
                status = ps.executeUpdate();
                status = status & (BookDao.updateReturnedBook(borrow.getBook_Id()));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }



    //  Function to get All Returned List
    public static List<Borrow> getAllReturned(){
        List<Borrow> allReturn = new ArrayList<>();
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT b.Book_Title, s.Student_Name, br.Issued_By, br.Borrow_Date, br.Return_Date, br.Fine_Paid, br.Borrow_Id FROM Book b, Student s, Borrow br WHERE s.Student_Id = br.Student_Id AND b.Book_Id = br.Book_Id AND br.Return_Date is not null;");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Borrow borrow = new Borrow();
                borrow.setBook_Name(result.getString(1));
                borrow.setStudent_Name(result.getString(2));
                borrow.setIssued_By(result.getString(3));
                borrow.setBorrow_Date(result.getDate(4));
                borrow.setReturn_Date(result.getDate(5));
                borrow.setFine_Paid(result.getFloat(6));
                borrow.setBorrow_Id(result.getInt(7));
                allReturn.add(borrow);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allReturn;
    }

}
