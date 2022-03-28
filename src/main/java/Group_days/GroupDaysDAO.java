package Group_days;

import DBConnection.DBConnection;

import java.sql.*;

public class GroupDaysDAO {

    public static int getDaysById(int groupid){
        int days = 0;
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Days FROM Group_Days WHERE Group_Id = ?");
            ps.setInt(1,groupid);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                days = result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return days;
    }
}
