<%@ page import="java.util.List" %>
<%@ page import="Category.Category" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: vaivas
  Date: 19/03/22
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<%  List<Category> allCategory = (ArrayList<Category>)request.getAttribute("allCategory"); %>
<nav class="navbar navbar-dark bg-primary navbar-expand-lg ">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">LMS - Zoho</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link "  href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="book-form">Add Book</a>
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
        <h2 class="text-centre">Add Book Form </h2>
        <div class="col-md-12">
            <form action="<%=request.getContextPath()%>/add-book" method="post">
                <div class="mb-3">
                    <label for="Book_Title" class="form-label">Book Title</label>
                    <input type="text" class="form-control" id="Book_Title" name="Book_Title">
                </div>
                <div class="mb-3">
                    <label for="Author_Name" class="form-label">Author Name</label>
                    <input type="text" class="form-control" id="Author_Name" name="Author_Name">
                </div>
                <div class="mb-3">
                    <label for="Nos_Available" class="form-label">Nos Available</label>
                    <input type="text" class="form-control" id="Nos_Available" name="Nos_Available">
                </div>
                <div class="mb-3">
                    <label for="Category_Id" class="form-label">Category</label>
                    <select name="Category_Id" id="Category_Id">
                        <% for(Category category: allCategory){%>
                        <option value="<%= category.getCategory_Id() %>"> <%= category.getCategory_Name() %> </option>

                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="Published_Year" class="form-label">Published Year</label>
                    <input type="text" class="form-control" id="Published_Year" name="Published_Year">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
