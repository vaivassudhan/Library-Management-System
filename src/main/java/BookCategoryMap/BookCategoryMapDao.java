package BookCategoryMap;

import Book.Book;
import Category.Category;
import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCategoryMapDao {
    public static List<Category> getCategoriesOfBook(int bookId){
        List<Category> categories = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT c.Category_Name FROM BookCategoryMap m, Category c WHERE m.Category_Id = c.Category_Id AND Book_Id = ?");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Category category = new Category();
                category.setCategory_Name(result.getString(1));
                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
