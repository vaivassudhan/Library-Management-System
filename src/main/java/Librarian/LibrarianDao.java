package Librarian;

import DBConnection.DBConnection;

import java.sql.*;

public class LibrarianDao {

//   Function to validate login
    public static boolean validate(String Lid,String Password){
        Connection con = DBConnection.getConnection();
        boolean status = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT Librarian_Id FROM Librarian WHERE Librarian_Id=? and Password=?");
            ps.setString(1,Lid);
            ps.setString(2,Password);
            ResultSet result = ps.executeQuery();
            status = result.next();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

//   Function to get librarian details after login
    public static Librarian getDataById(String Lid){
        Connection con = DBConnection.getConnection();
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
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librarian;
    }

//  Function to add Librarian
    public static int addLibrarian(Librarian librarian){
        Connection con = DBConnection.getConnection();
        int status = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Librarian(Librarian_Id, Password, Name, Gender, Role, Mobile ) VALUES(?,?,?,?,?,?)");
            ps.setString(1,librarian.getLibrarian_Id());
            ps.setString(2,librarian.getLibrarian_Id());
            ps.setString(3,librarian.getName());
            ps.setString(4,librarian.getGender());
            ps.setInt(5,librarian.getRole());
            ps.setString(6,librarian.getMobile());
            status = ps.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
