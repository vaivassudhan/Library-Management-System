package Book;

public class Book {
    private int Book_Id, Nos_Available,Category_Id,Published_year;
    private String Book_Title, Author_Name;
    public String category_name;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public int getBook_Id() {
        return Book_Id;
    }

    public void setBook_Id(int book_Id) {
        Book_Id = book_Id;
    }

    public int getNos_Available() {
        return Nos_Available;
    }

    public void setNos_Available(int nos_Available) {
        Nos_Available = nos_Available;
    }

    public int getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(int category_Id) {
        Category_Id = category_Id;
    }

    public int getPublished_year() {
        return Published_year;
    }

    public void setPublished_year(int published_year) {
        Published_year = published_year;
    }

    public String getBook_Title() {
        return Book_Title;
    }

    public void setBook_Title(String book_Title) {
        Book_Title = book_Title;
    }

    public String getAuthor_Name() {
        return Author_Name;
    }

    public void setAuthor_Name(String author_Name) {
        Author_Name = author_Name;
    }


}
