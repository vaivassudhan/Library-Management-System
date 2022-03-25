<%--
  Created by IntelliJ IDEA.
  User: vaivas
  Date: 24/03/22
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LMS - Zoho</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-dark bg-primary navbar-expand-lg ">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">LMS - Zoho</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
<%--        Nav bar if not logged in or invalid user--%>
        <% if(request.getSession(false) == null) { %>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="login">Login </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="view-books">Book List</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="view-category">View Category</a>
                    </li>
                </ul>
            </div>
        <% } %>
<%--        Navbar for Librarians --%>
        <% if(request.getSession(false) != null) { %>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active"  href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="book-form">Add Book</a>
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
        <% } %>
    </div>
</nav>
<div class="container pt-5">
    <% if(request.getAttribute("message-type") == "success"){ %>
    <div class="alert alert-success" role="alert">
        <%= request.getAttribute("message") %>
    </div>
    <%}%>
    <% if(request.getAttribute("message-type") == "error"){ %>
    <div class="alert alert-danger" role="alert">
        <%= request.getAttribute("message") %>
    </div>
    <%}%>
</div>

</body>
</html>
