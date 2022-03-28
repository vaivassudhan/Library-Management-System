package Student;

import Category.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


//    Function to get all student
    public static List<Student> getAllStudent(){
        List<Student> allStudent = new ArrayList<Student>();
        try{
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Student_Id, Student_Name, Gender, Group_Id, Mobile FROM Student");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Student student = new Student();

                student.setStudent_Id(result.getInt(1));
                student.setStudent_Name(result.getString(2));
                student.setGender(result.getString(3));
                student.setGroup_id(result.getInt(4));
                student.setMobile(result.getString(5));

                allStudent.add(student);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudent;
    }
}
