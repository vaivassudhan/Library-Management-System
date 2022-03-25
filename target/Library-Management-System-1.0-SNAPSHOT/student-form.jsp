<%--
  Created by IntelliJ IDEA.
  User: vaivas
  Date: 20/03/22
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <div class="row">
        <h2 class="text-centre">Add Student Form </h2>
        <div class="col-md-12">
            <form action="<%=request.getContextPath()%>/add-student" method="post">
                <div class="mb-3">
                    <label for="Student_Name" class="form-label">Student Name</label>
                    <input type="text" class="form-control" id="Student_Name" name="Student_Name">
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="Gender" id="Gender" value="Male">
                    <label class="form-check-label" for="Gender">
                        Male
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="Gender" id="Gender2" value="Female">
                    <label class="form-check-label" for="Gender2">
                        Female
                    </label>
                </div>
                <div class="mb-3">
                    <label for="Group_Id" class="form-label">Group Id</label>
                    <input type="text" class="form-control" id="Group_Id" name="Group_Id">
                </div>
                <div class="mb-3">
                    <label for="Mobile" class="form-label">Mobile</label>
                    <input type="text" class="form-control" id="Mobile" name="Mobile">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

