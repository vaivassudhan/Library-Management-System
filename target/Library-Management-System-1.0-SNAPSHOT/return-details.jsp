<%@ page import="Borrow_Return.Borrow" %><%--
  Created by IntelliJ IDEA.
  User: vaivas
  Date: 22/03/22
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>

    <title>LMS - Zoho</title>
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
<nav class="navbar navbar-dark bg-primary navbar-expand-lg ">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">LMS - Zoho</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="book-form">Add Book</a>
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
                    <a class="nav-link" href="view-borrow">View Issued Books</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
    <div class="container pt-5">
        <h2 class="text-center">Return Details </h2>
        <div class="row justify-content-center ">
            <div class="col-md-6 ">
<% Borrow borrow = (Borrow) request.getAttribute("borrow");
    session.setAttribute("borrow",borrow);
    %>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><b>Book Title : </b> <%= borrow.getBook_Name() %> Id (<%= borrow.getBook_Id() %>) </li>
                    <li class="list-group-item"><b>Student Name : </b> <%= borrow.getStudent_Name() %> Id ( <%= borrow.getStudent_Id() %> ) </li>
                    <li class="list-group-item"><b>Issued By :  </b> <%= borrow.getIssued_By() %></li>
                    <li class="list-group-item"><b>Borrow Date :  </b> <%= borrow.getBorrow_Date() %></li>
                    <li class="list-group-item"><b>Return Date :  </b> <%= borrow.getReturn_Date()%></li>
                    <li class="list-group-item"><b>Fine to be paid :</b> <%= borrow.getFine_Paid()%> </li>
                </ul>
                <a class="btn btn-primary" href="<%=request.getContextPath()%>/confirm-return">Confirm Return</a>
            </div>
        </div>
    </div>





</body>
</html>
