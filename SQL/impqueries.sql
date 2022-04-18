-- CREATE TABLE Group_Days(
-- 	Group_Id int not null auto_increment,
--     Days int,
--     primary key (Group_Id)
--     );
--     
-- CREATE TABLE Fine(
-- 	Group_Id int,
--     Fine_Per_day double,
--     Damage_Fine double,
--     foreign key (Group_Id) references Group_Days(Group_Id)
--     );

-- create table Student(
-- 	Student_Id int not null auto_increment,
--     Student_Name varchar(30),
--     Gender varchar(8),
--     Mobile varchar(13),
--     Email varchar(25),
--     Batch int,
--     Group_Id int,
--     primary key(Student_Id),
--     foreign key (Group_Id) references Group_Days(Group_Id)
--     );
--     
--     

-- ALTER TABLE Student 
-- MODIFY COLUMN Email varchar(40);


-- create table Borrow(
-- 	Borrow_Id int not null auto_increment,
--     Student_Id int,
--     Book_Id int,
--     Issued_By varchar(20),
--     Borrow_Date date,
--     Return_Date date,
-- 	DueDate date,
--     Fine_Paid float,
--     primary key (Borrow_Id),
--     foreign key (Student_Id) references Student(Student_Id),
--     foreign key (Book_Id) references Book(Book_Id),
--     foreign key (Issued_By) references Librarian(Librarian_Id)
--     );


-- create table Payment(
-- 	Student_Id int not null,
--     Borrow_Id int not null,
--     order_id varchar(30) not null,
--     razorpay_payment_id varchar(30), 
--     razorpay_order_id varchar(30),
--     amount int,
-- 		status varchar(10);
--     primary key (order_id),
--     foreign key (Student_Id) references Student(Student_Id),
--     foreign key (Borrow_Id) references Borrow(Borrow_Id)
--     );
--     
-- ALTER TABLE Payment
-- ADD COLUMN Status varchar(30);

--  TRUNCATE TABLE Payment; 
-- UPDATE `Library`.`Borrow` SET `Return_Date` = null WHERE (`Borrow_Id` = '2');

-- SELECT * FROM Payment;


-- DROP TABLE StripePayment;
-- CREATE TABLE StripePayment(
-- 	id varchar(100) unique not null,
--     status varchar(30),
--     Borrow_Id int not null,
--     amount int,
--     primary key (id),
--     foreign key (Borrow_Id) references Borrow(Borrow_Id)
--     );


SELECT * FROM StripePayment;


