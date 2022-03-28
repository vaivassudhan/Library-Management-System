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
