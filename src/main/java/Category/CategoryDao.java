package Category;

import DBConnection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
//  Function to add category
    public static int addCategory(Category category){
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
//          Insert into category table query, prepare statement with values from passed category object
            PreparedStatement ps = con.prepareStatement("INSERT INTO Category( Category_Name) VALUES (?)");
            ps.setString(1,category.getCategory_Name());

//          Executing the query
            status = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

//    Get all Category
    public static List<Category> getAllCategory(){
        List<Category> allCategory = new ArrayList<Category>();
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Category");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Category category = new Category();
                category.setCategory_Id(result.getInt(1));
                category.setCategory_Name(result.getString(2));
                allCategory.add(category);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCategory;
    }
    public static String getCategoryNameById(int category_id){
        String Category_Name = "";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Category WHERE Category_Id = ?");
            ps.setInt(1,category_id);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                Category_Name = result.getString(2);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Category_Name;
    }
}
