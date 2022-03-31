package DBConnection;

import java.sql.DriverManager;

public class DBConnection {
    public static java.sql.Connection getConnection(){
        java.sql.Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library",
                    "root","vaivas2001");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
