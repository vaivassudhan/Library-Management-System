package Payment;

import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDao {

    public static int addPaymentDetails(Payment paymentDetails){
        Connection con = null;
        int status = 0;
        try{
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Payment");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
