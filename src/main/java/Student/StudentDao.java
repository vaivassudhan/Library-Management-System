package Student;

import Category.Category;
import DBConnection.DBConnection;
import Group_days.GroupDaysDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

//  Function to register student
    public static int registerStudent(Student student){
        int status = 0 ;
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Student(Student_Name, Group_Id,Gender,Mobile, Email, Address, Batch) VALUES (?,?,?,?,?,?,?);");
            ps.setString(1,student.getStudent_Name());
            ps.setInt(2,student.getGroup_id());
            ps.setString(3,student.getGender());
            ps.setString(4,student.getMobile());
            ps.setString(5,student.getEmail());
            ps.setString(6,student.getAddress());
            ps.setInt(7,student.getBatch());
            status = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }


//    Function to get all student
    public static List<Student> getAllStudent(){
        List<Student> allStudent = new ArrayList<Student>();
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Student_Id, Student_Name, Gender, Group_Id, Mobile, Email, Address, Batch FROM Student");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Student student = new Student();

                student.setStudent_Id(result.getInt(1));
                student.setStudent_Name(result.getString(2));
                student.setGender(result.getString(3));
                student.setGroup_id(result.getInt(4));
                student.setMobile(result.getString(5));
                student.setEmail(result.getString(6));
                student.setAddress(result.getString(7));
                student.setBatch(result.getInt(8));

                allStudent.add(student);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudent;
    }

//  Get Student by ID
    public static Student getStudentById(int student_id){
        Student student = new Student();
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Student_Id, Student_Name, Mobile, Group_Id FROM Student WHERE Student_Id = ?");
            ps.setInt(1,student_id);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                student.setStudent_Id(result.getInt(1));
                student.setStudent_Name(result.getString(2));
                student.setMobile(result.getString(3));
                student.setGroup_id(result.getInt(4));
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

//  get no of days for student id
    public static int getDaysBySid(int student_id){
        int no_days = 0;
        int group_id = 0;
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Group_Id FROM Student WHERE Student_Id =?");
            ps.setInt(1,student_id);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                group_id = result.getInt(1);
            }
            no_days = GroupDaysDAO.getDaysById(group_id);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return no_days;
    }
//   Function to delete student by batch
    public static int deleteStudentByBatch(int batch){
        int status = 0 ;
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Student WHERE Batch = ?");
            ps.setInt(1,batch);

            status = ps.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
