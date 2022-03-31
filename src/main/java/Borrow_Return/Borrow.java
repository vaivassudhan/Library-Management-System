package Borrow_Return;

import java.sql.Date;

public class Borrow {
    private int Borrow_Id, Book_Id, Student_Id;
    private Date Borrow_Date;
    private Date Return_Date;
    private String Issued_By;
    private Float Fine_Paid;
    private Date DueDate;
    //    Additional fields for easy access
    private String Book_Name , Student_Name;
    private int Group_Id;

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date dueDate) {
        DueDate = dueDate;
    }

    public int getGroup_Id() {
        return Group_Id;
    }

    public void setGroup_Id(int group_Id) {
        Group_Id = group_Id;
    }

    public String getIssued_By() {
        return Issued_By;
    }

    public void setIssued_By(String issued_By) {
        Issued_By = issued_By;
    }

    public int getBorrow_Id() {
        return Borrow_Id;
    }

    public void setBorrow_Id(int borrow_Id) {
        Borrow_Id = borrow_Id;
    }

    public int getBook_Id() {
        return Book_Id;
    }

    public void setBook_Id(int book_Id) {
        Book_Id = book_Id;
    }

    public int getStudent_Id() {
        return Student_Id;
    }

    public void setStudent_Id(int student_Id) {
        Student_Id = student_Id;
    }

    public Date getBorrow_Date() {
        return Borrow_Date;
    }

    public void setBorrow_Date(Date borrow_Date) {
        Borrow_Date = borrow_Date;
    }

    public Date getReturn_Date() {
        return Return_Date;
    }

    public void setReturn_Date(Date return_Date) {
        Return_Date = return_Date;
    }

    public Float getFine_Paid() {
        return Fine_Paid;
    }

    public void setFine_Paid(Float fine_Paid) {
        Fine_Paid = fine_Paid;
    }

    public String getBook_Name() { return Book_Name; }

    public void setBook_Name(String book_Name) {
        Book_Name = book_Name;
    }

    public String getStudent_Name() {
        return Student_Name;
    }

    public void setStudent_Name(String student_Name) {
        Student_Name = student_Name;
    }

}
