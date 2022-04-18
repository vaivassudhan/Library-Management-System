package StripePayment;

import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StripePaymentDao {
//   function to add stripe payment details
    public static int addStripePaymentDetails(StripePayment payment){
        Connection con = null;
        int status = 0;
        try{
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO StripePayment (id,Borrow_Id,amount) VALUES (?,?,?)");
            ps.setString(1,payment.getId());
            ps.setInt(2,payment.getBorrow_Id());
            ps.setInt(3, (int) payment.getAmount());

            status = ps.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

//  function to update status after webhook
    public static int updateStripeStatus(String id, String payment_status) {
        Connection con = null;
        int status = 0;
        try {
            con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE StripePayment SET status = ? WHERE id = ?");
                ps.setString(1, payment_status);
                ps.setString(2,id);

                status = ps.executeUpdate();
                con.close();
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return status ;
    }


}
