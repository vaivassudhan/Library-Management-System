package DBConnection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    public static java.sql.Connection getConnection(){
        java.sql.Connection con = null;
        try{
            InputStream input = new FileInputStream("/Users/vaivas/Documents/Library-Management-System/src/main/java/DBConnection/db.properties");
//            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
            Properties p=new Properties();
            p.load(input);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library",
                    p.getProperty("user"), p.getProperty("password"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
