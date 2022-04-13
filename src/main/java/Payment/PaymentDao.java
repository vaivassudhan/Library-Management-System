package Payment;

import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDao {

//  Function to store the payment details in the database for verification
    public static int addPaymentDetails(Payment paymentDetails){
        Connection con = null;
        int status = 0;
        try{
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Payment(Student_Id, Borrow_Id, order_id,amount) VALUES (?,?,?,?)");
            ps.setInt(1,paymentDetails.getStudent_Id());
            ps.setInt(2,paymentDetails.getBorrow_Id());
            ps.setString(3,paymentDetails.getOrder_id());
            ps.setDouble(4,paymentDetails.getAmount());
            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }


//  Function to get the order id for verification of transaction
    public static String getOrderId(int borrow_id){
        Connection con = null;
        String order_id = null;
        try{
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT order_id FROM Payment WHERE Borrow_Id = ?");
            ps.setInt(1,borrow_id);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                order_id = result.getString(1);
            }
            System.out.println(order_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order_id;
    }


// Function to update the payment status in DB
    public static int updatePaymentStatus(String paymentStatus, int borrow_id){
        Connection con = null;
        int status = 0;
        try{
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Payment SET status = ? WHERE Borrow_Id = ?");
            ps.setString(1,paymentStatus);
            ps.setInt(2,borrow_id);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

// Function to update the payment details in DB
    public static int updatePaymentDetails(String paymentStatus,String razorpay_payment_id, int borrow_id) {
        Connection con = null;
        int status = 0;
        try {
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Payment SET status = ?, razorpay_payment_id = ? WHERE Borrow_Id = ?");
            ps.setString(1, paymentStatus);
            ps.setString(2, razorpay_payment_id);
            ps.setInt(3, borrow_id);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }


// Function to get the payment id from DB
    public static String getPaymentId(int borrow_id){
        Connection con = null;
        String payment_id = null;
        try{
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT razorpay_payment_id FROM Payment WHERE Borrow_Id = ?");
            ps.setInt(1,borrow_id);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                payment_id = result.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment_id;
    }
}
