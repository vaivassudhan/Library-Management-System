package Book;

import DBConnection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
//  Function to add book into DB
    public static int addBook(Book book){
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
//          Insert into books table query, prepare statement with values from passed book object
            PreparedStatement ps = con.prepareStatement("INSERT INTO Book(Book_Title, Author_Name,Category_Id, Nos_Available, Published_Year) VALUES (?,?,?,?,?)");
            ps.setString(1,book.getBook_Title());
            ps.setString(2,book.getAuthor_Name());
            ps.setInt(3,book.getCategory_Id());
            ps.setInt(4,book.getNos_Available());
            ps.setInt(5,book.getPublished_year());
//          Executing the query
            status = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

//  Function to get All Books
    public static List<Book> getALlBooks(){
        List<Book> allBooks = new ArrayList<>();
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT b.Book_Id,b.Book_Title,b.Author_Name,b.Category_Id,b.Nos_Available, b.Published_Year,c.Category_Name FROM Book b JOIN Category c ON b.Category_Id = c.Category_Id");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Book book = new Book();
                book.setBook_Id(result.getInt(1));
                book.setBook_Title(result.getString(2));
                book.setAuthor_Name(result.getString(3));
                book.setCategory_Id(result.getInt(4));
                book.setNos_Available(result.getInt(5));
                book.setPublished_year(result.getInt(6));
                book.category_name = result.getString(7);
                allBooks.add(book);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooks;
    }

//  Function to update book stock by id
    public static int updateBookStock(int book_id, int stock){
        int status = 0 ;
        Connection con;
        try{
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Book SET Nos_Available = ? Where Book_Id = ? ; ");
            ps.setInt(1,stock);
            ps.setInt(2,book_id);

            status = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

//  Function to remove book
    public static int removeBook(int book_id){
        int status = 0;
        Connection con;
        try{
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Book Where Book_Id = ?");
            ps.setInt(1,book_id);
            status = ps.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    //  Function to update book stock by id
    public static int updateReturnedBook(int book_id){
        int status = 0 ;
        Connection con;
        try{
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Book SET Nos_Available = Nos_Available + 1 WHERE Book_Id = ?");
            ps.setInt(1,book_id);

            status = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
    public static List<Book> searchByCategory(int Category_Id){
        List<Book> allBooks = new ArrayList<Book>();
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT b.Book_Id,b.Book_Title,b.Author_Name,b.Category_Id,b.Nos_Available, b.Published_Year,c.Category_Name FROM Book b JOIN Category c ON b.Category_Id = c.Category_Id WHERE b.Category_Id = ?");
            ps.setInt(1,Category_Id);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Book book = new Book();
                book.setBook_Id(result.getInt(1));
                book.setBook_Title(result.getString(2));
                book.setAuthor_Name(result.getString(3));
                book.setCategory_Id(result.getInt(4));
                book.setNos_Available(result.getInt(5));
                book.setPublished_year(result.getInt(6));
                book.category_name = result.getString(7);
                allBooks.add(book);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooks;
    }

}
