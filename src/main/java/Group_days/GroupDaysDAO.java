package Group_days;

import DBConnection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return days;
    }

//  get all group days
    public static List<GroupDays> getAllGroups(){
        List<GroupDays> allGroups = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Group_Days");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                GroupDays groupDays = new GroupDays();
                groupDays.setGroup_Id(result.getInt(1));
                groupDays.setDays(result.getInt(2));
                allGroups.add(groupDays);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGroups;
    }

//   Update days by group id
    public static int updateGroup(int group_id, int days){
        System.out.println("Group ID "+group_id+" "+days);
        int status = 0;
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Group_Days SET Days = ? WHERE Group_Id = ?");
            ps.setInt(1,days);
            ps.setInt(2,group_id);
            status = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

//  Add Group Days
    public static int addGroup(GroupDays groupDays){
        int group_id = 0;
        try{
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO Group_Days(Days) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,groupDays.getDays());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            group_id = rs.getInt(1);


            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group_id;
    }
}
