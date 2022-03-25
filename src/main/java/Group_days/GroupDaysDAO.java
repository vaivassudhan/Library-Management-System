package Group_days;

import java.sql.*;

public class GroupDaysDAO {
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

    public static int getDaysById(int groupid){
        int days = 0;
        try{
            Connection con = getConnection();
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
