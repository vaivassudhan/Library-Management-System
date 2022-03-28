<%--
  Created by IntelliJ IDEA.
  User: vaivas
  Date: 20/03/22
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Borrow_Return.Borrow" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LMS</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%--Check if user is logged in --%>
<%
    if(session.getAttribute("Librarian_Id") == null){
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
        dispatcher.forward(request,response);
    }
%>
<nav class="navbar navbar-dark bg-dark navbar-expand-lg ">
<div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">LMS - Zoho</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0" >
            <%-- Books Dropdown --%>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="book-dropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Manage Books
                </a>
                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="book-dropdown">
                    <li><a class="dropdown-item" href="book-form">Add Book</a></li>
                    <li><a class="dropdown-item" href="view-books">Book List</a></li>
                </ul>
            </li>
            <%-- Categories drop down  --%>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="category-dropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Manage Categories
                </a>
                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="category-dropdown">
                    <li><a class="dropdown-item" href="category-form.jsp">Add Category</a></li>
                    <li><a class="dropdown-item" href="view-category">View Category</a></li>
                </ul>
            </li>
            <%-- Issue return  drop down  --%>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="issue-dropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Manage Issue / Return
                </a>
                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="issue-dropdown">
                    <li><a class="dropdown-item" href="book-issue.jsp">Issue Book</a></li>
                    <li><a class="dropdown-item" href="return-book.jsp">Return Book</a></li>
                    <li><a class="dropdown-item" href="view-borrow">View Issued Books</a></li>
                </ul>
            </li>
            <%-- Student  drop down  --%>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="student-dropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Manage Student
                </a>
                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="student-dropdown">
                    <li><a class="dropdown-item" href="student-form.jsp">Add Student</a></li>
                    <li><a class="dropdown-item" href="view-student">View Students</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout">Logout</a>
            </li>
        </ul>
    </div>
</div>
</nav>
<div class="container pt-5">
    <h2 class="text-center">Issued Book List</h2>
    <div class="row justify-content-center mt-4">
        <div class="col-md-10">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Borrow Id</th>
                    <th>Book Title</th>
                    <th>Student Name</th>
                    <th>Issued By </th>
                    <th>Issued Date </th>
<%--                    <th>Return Date </th>--%>
<%--                    <th>Fine Paid</th>--%>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Borrow> allBorrow = (ArrayList<Borrow>)request.getAttribute("allBorrow");
                    for(Borrow borrow:allBorrow){

                %>
                <tr>
                    <td><%= borrow.getBorrow_Id() %></td>
                    <td><%= borrow.getBook_Name() %></td>
                    <td><%= borrow.getStudent_Name()%></td>
                    <td><%= borrow.getIssued_By()%></td>
                    <td><%= borrow.getBorrow_Date() %></td>
<%--                    <td><%= borrow.getReturn_Date() %></td>--%>
<%--                    <td><%= borrow.getFine_Paid() %></td>--%>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <h2 class="text-center pt-5">Returned Book List</h2>
    <div class="row justify-content-center mt-4">
        <div class="col-md-10">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Borrow Id</th>
                    <th>Book Title</th>
                    <th>Student Name</th>
                    <th>Issued By </th>
                    <th>Issued Date </th>
                    <th>Return Date </th>
                    <th>Fine Paid</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Borrow> allReturn = (ArrayList<Borrow>)request.getAttribute("allReturn");
                    for(Borrow borrow:allReturn){

                %>
                <tr>
                    <td><%= borrow.getBorrow_Id() %></td>
                    <td><%= borrow.getBook_Name() %></td>
                    <td><%= borrow.getStudent_Name()%></td>
                    <td><%= borrow.getIssued_By()%></td>
                    <td><%= borrow.getBorrow_Date() %></td>
                    <td><%= borrow.getReturn_Date() %></td>
                    <td><%= borrow.getFine_Paid() %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>