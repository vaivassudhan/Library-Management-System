use `Library`;
CREATE TABLE Category(
	Category_Id int AUTO_INCREMENT,
    category_Name varchar(20),
    PRIMARY KEY (Category_Id)
);
CREATE TABLE Author(
	Author_Id int AUTO_INCREMENT,
	Author_Name varchar(20),
    PRIMARY KEY (Author_Id)
);
CREATE TABLE Book(
	Book_Id int AUTO_INCREMENT,
    Book_Title varchar(100),
    Author_Id int,
    Category_Id int,
    Nos_Available int,
    Published_Year int,
    PRIMARY KEY (Book_Id),
    FOREIGN KEY (Category_Id) REFERENCES Category(Category_Id),
    FOREIGN KEY (Author_Id) REFERENCES Author(Author_Id)
);