package Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDao {
//  Function to establish connection to DB (Returns connection object)
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
//  Function to register student
    public static int registerStudent(Student student){
        int status = 0 ;
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Student(Student_Name, Group_Id,Gender,Mobile) VALUES (?,?,?,?);");
            ps.setString(1,student.getStudent_Name());
            ps.setInt(2,student.getGroup_id());
            ps.setString(3,student.getGender());
            ps.setString(4,student.getMobile());
            status = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
