package Fine;

import java.sql.*;

public class FineDao {
    //    Function to establish DB Connection
    public static Connection getConnection(){
        Connection con = null;
        try{

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library",
                    "root", "vaivas2001");
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }

    public static Fine getFineById(int group_id) {
        Fine fine = new Fine();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Fine WHERE Group_Id = ?");
            ps.setInt(1, group_id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {

                fine.setFine_Per_Day(result.getFloat(2));
                fine.setDamage_Fine(result.getFloat(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fine;
    }
}
