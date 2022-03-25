use `Library`;

-- create table Borrow(
-- 	Borrow_Id int not null,
--     Student_Id int,
--     Book_Id int,
--     Issued_By varchar(20),
--     Borrow_Date datetime,
--     Return_Date datetime,
--     Fine_Paid float,
--     primary key (Borrow_Id),
--     foreign key (Student_Id) references Student(Student_Id),
--     foreign key (Book_Id) references Book(Book_Id),
--     foreign key (Issued_By) references Librarian(Librarian_Id)
--     );
-- UPDATE `library`.`Borrow` SET `Borrow_Date` = '2022-03-15 00:00:00' WHERE (`Borrow_Id` = '2');
SELECT * FROM Borrow;
