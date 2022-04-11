package Fine;

import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FineDao {

    public static Fine getFineById(int group_id) {
        Fine fine = new Fine();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Fine WHERE Group_Id = ?");
            ps.setInt(1, group_id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {

                fine.setFine_Per_Day(result.getFloat(2));
                fine.setDamage_Fine(result.getFloat(3));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fine;
    }

//   Update fine amount
    public static int updateFine(int group_id,float fine_amount){
        int status = 0 ;
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE FINE SET Fine_Per_Day = ? WHERE Group_Id = ?");
            ps.setFloat(1,fine_amount);
            ps.setInt(2,group_id);
            status = ps.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

//  Add Fine with group id
    public static int addFine(Fine fine){
        int status = 0;
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Fine(Group_Id, Fine_Per_Day) VALUES(?,?)");

            ps.setInt(1,fine.getGroup_Id());
            ps.setFloat(2,fine.getFine_Per_Day());

            status = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

//    Get ALl fine
    public static List<Fine> getALlFine(){
        List<Fine> allFine = new ArrayList<Fine>();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Group_Id,Fine_Per_Day FROM Fine");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Fine fine = new Fine();
                fine.setGroup_Id(result.getInt(1));
                fine.setFine_Per_Day(result.getFloat(2));
                allFine.add(fine);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allFine;
    }
}
