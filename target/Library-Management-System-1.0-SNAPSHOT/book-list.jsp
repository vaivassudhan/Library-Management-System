<%@ page import="Book.Book" %>
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
                <%--            Manage Admin--%>
                <% if((Integer)session.getAttribute("Role") == 1){ %>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="admin-dropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Admin Manage
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="student-dropdown">
                        <li><a class="dropdown-item" href="librarian-form.jsp">Add Librarian</a></li>
                        <li><a class="dropdown-item" href="get-groups">Manage Groups</a></li>
                        <li><a class="dropdown-item" href="get-fine">Manage Fine</a></li>
                    </ul>
                </li>
                <% } %>
                <li class="nav-item">
                    <a class="nav-link" href="logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
    <div class="container pt-5">
        <div class="row justify-content-center mt-4">
            <div class="col-md-8">
                <h4 class="text-center mb-4">Book List</h4>
              <table class="table table-striped">
              <thead>
                  <tr>
                    <th>#</th>
                    <th>Book Title</th>
                    <th>Author Name</th>
                    <th>Category</th>
                    <th>Stock</th>
                    <th>Year</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                    List<Book> allBook = (ArrayList<Book>)request.getAttribute("allBook");
                    for(Book book:allBook){

                    %>
                    <tr>
                    <td><%= book.getBook_Id() %></td>
                      <td><%= book.getBook_Title() %></td>
                        <td><%= book.getAuthor_Name() %></td>
                        <td><%= book.category_name %></td>
                        <td><%= book.getNos_Available() %></td>
                        <td><%= book.getPublished_year() %></td>
                    </tr>
                    <% } %>
                </tbody>
              </table>
            </div>

            <div class="col-md-3 mx-4">
                <h4 class="display4 text-center mb-4">Update Stock</h4>
                <hr>
                <form method="post" action="update-stock">
                    <div class="form-group">
                        <label for="Book_Id">Book ID : </label>
                        <input type="text" class="form-control" id="Book_Id" name="Book_Id" placeholder="Enter Book ID">
                    </div>
                    <div class="form-group">
                        <label for="Nos_Available">Nos Available : </label>
                        <input type="text" class="form-control" id="Nos_Available" name="Nos_Available" placeholder="Update stock">
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                </form>
            </div>
        </div>

    </div>
</body>
</html>