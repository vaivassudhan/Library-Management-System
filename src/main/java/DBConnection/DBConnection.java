package DBConnection;


import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    public static java.sql.Connection getConnection(){
        java.sql.Connection con = null;
        Properties properties = new Properties();
        try{
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            properties.load(classLoader.getResourceAsStream("/db.properties"));
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library",
                    properties.getProperty("user"),properties.getProperty("password"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
