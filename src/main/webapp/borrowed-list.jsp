<%@ page import="Borrow_Return.Borrow" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: vaivas
  Date: 20/03/22
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LMS</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%--Check if user is logged in --%>
<%--<%--%>
<%--    if(session.getAttribute("Librarian_Id") == null){--%>
<%--        RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");--%>
<%--        dispatcher.forward(request,response);--%>
<%--    }--%>
<%--%>--%>
<nav class="navbar navbar-dark bg-primary navbar-expand-lg ">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">LMS - Zoho</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link " aria-current="page" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="book-form">Add Book</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="view-books">Book List</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="category-form.jsp">Add Category</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="view-category">View Category</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="book-issue.jsp">Issue Book</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="return-book.jsp">Return Book</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="view-borrow">View Issued Books</a>
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