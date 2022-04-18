package Book;

import Category.CategoryDao;
import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
//  Function to add book into DB
    public static int addBook(Book book){
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
//          Insert into books table query, prepare statement with values from passed book object
            PreparedStatement ps = con.prepareStatement("INSERT INTO Book(Book_Title, Author_Name,Category_Id, Nos_Available, Published_Year, ISBN) VALUES (?,?,?,?,?,?)");
            ps.setString(1,book.getBook_Title());
            ps.setString(2,book.getAuthor_Name());
            ps.setInt(3,book.getCategory_Id());
            ps.setInt(4,book.getNos_Available());
            ps.setInt(5,book.getPublished_year());
            ps.setString(6, book.getISBN());
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
            PreparedStatement ps = con.prepareStatement("SELECT b.Book_Id,b.Book_Title,b.Author_Name,b.Category_Id,b.Nos_Available, b.Published_Year,c.Category_Name, b.ISBN FROM Book b JOIN Category c ON b.Category_Id = c.Category_Id");
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
                book.setISBN(result.getString(8));
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
            PreparedStatement ps = con.prepareStatement("SELECT b.Book_Id,b.Book_Title,b.Author_Name,b.Category_Id,b.Nos_Available, b.Published_Year,c.Category_Name, b.ISBN FROM Book b JOIN Category c ON b.Category_Id = c.Category_Id WHERE b.Category_Id = ?");
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
                book.setISBN(result.getString((8)));
                allBooks.add(book);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooks;
    }

//  get book by id
    public static Book getBookByID(int Book_Id){
        Book book = new Book();
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Book_Id,Book_Title,Author_Name,Published_Year,Category_Id,Nos_Available,ISBN FROM Book WHERE Book_Id = ?");
            ps.setInt(1,Book_Id);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                book.setBook_Id(result.getInt(1));
                book.setBook_Title(result.getString(2));
                book.setAuthor_Name(result.getString(3));
                book.setPublished_year(result.getInt(4));
                book.setCategory_Id(result.getInt(5));
                book.setNos_Available(result.getInt(6));
                book.setISBN(result.getString(7));
            }
            con.close();
            book.setCategory_name(CategoryDao.getCategoryNameById(book.getCategory_Id()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

//   function to find books of author
    public static List<Book> searchByAuthor(String Author_Name){
        List<Book> allBooks = new ArrayList<Book>();
        try
        {
            String upper_author_name = Author_Name.toUpperCase();
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT b.Book_Id,b.Book_Title,b.Author_Name,b.Category_Id,b.Nos_Available, b.Published_Year,c.Category_Name,b.ISBN FROM Book b JOIN Category c ON b.Category_Id = c.Category_Id WHERE upper(b.Author_Name) LIKE ?");
            ps.setString(1,"%"+upper_author_name+"%");

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
                book.setISBN(result.getString(8));
                allBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooks;
    }
    public static List<Book> searchByTitle(String Book_Title){
        List<Book> allBooks = new ArrayList<Book>();
        try
        {
            String upper_book_title = Book_Title.toUpperCase();
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT b.Book_Id,b.Book_Title,b.Author_Name,b.Category_Id,b.Nos_Available, b.Published_Year,c.Category_Name,b.ISBN FROM Book b JOIN Category c ON b.Category_Id = c.Category_Id WHERE upper(b.Book_Title) LIKE ?");
            ps.setString(1,"%"+upper_book_title+"%");

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
                book.setISBN(result.getString(8));
                allBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooks;

    }


//   get all books by pagination
    public static List<Book> getBooksByPage(int pageNo, int pageSize){
        Connection con = null;
        List<Book> allBooks = new ArrayList<Book>();
        try{
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT b.Book_Id,b.Book_Title,b.Author_Name,b.Category_Id,b.Nos_Available, b.Published_Year,c.Category_Name,b.ISBN FROM Book b JOIN Category c ON b.Category_Id = c.Category_Id LIMIT ?,?");
            ps.setInt(1,(pageNo-1)*pageSize);
            ps.setInt(2,pageSize);
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
                book.setISBN(result.getString(8));
                allBooks.add(book);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooks;
    }
}
