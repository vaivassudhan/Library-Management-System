package Librarian;

import java.sql.*;

public class LibrarianDao {
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
//   Function to validate login
    public static boolean validate(String Lid,String Password){
        Connection con = getConnection();
        boolean status = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT Librarian_Id FROM Librarian WHERE Librarian_Id=? and Password=?");
            ps.setString(1,Lid);
            ps.setString(2,Password);
            ResultSet result = ps.executeQuery();
            status = result.next();
//            if(status){
//                Librarian librarian = new Librarian();
//                librarian
//            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

//   Function to get librarian details after login
    public static Librarian getDataById(String Lid){
        Connection con = getConnection();
        Librarian librarian = new Librarian();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT Librarian_Id,Name,Role FROM Librarian WHERE Librarian_Id=?");
            ps.setString(1,Lid);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                librarian.setLibrarian_Id(result.getString(1));
                librarian.setName(result.getString(2));
                librarian.setRole(result.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librarian;
    }
}
