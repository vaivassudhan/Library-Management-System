<%@ page import="Book.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>LMS - ZOHO</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
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
<%--<% if(session.getAttribute("Librarian_Id") != null) { %>--%>
    <div class="container pt-5">
        <div class="row">
            <div class="col-md-12">
                <h1 class="display-4 text-center mb-4">Welcome to Library Management System</h1>

            </div>
        </div>
    </div>
<%--            <div class="col-md-8">--%>
<%--                <table class="table table-striped">--%>
<%--                    <thead>--%>
<%--                    <tr>--%>
<%--                        <th>#</th>--%>
<%--                        <th>Book Title</th>--%>
<%--                        <th>Author Name</th>--%>
<%--                        <th>Category</th>--%>
<%--                        <th>Stock</th>--%>
<%--                        <th>Published Year</th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody>--%>
<%--                    <%--%>
<%--                        RequestDispatcher dispatcher = request.getRequestDispatcher("/view-books");--%>
<%--                        dispatcher.include(request,response);--%>
<%--                        List<Book> allBook = (ArrayList<Book>)request.getAttribute("allBook");--%>
<%--                        for(Book book:allBook){--%>

<%--                    %>--%>
<%--                    <tr>--%>
<%--                        <td><%= book.getBook_Id() %></td>--%>
<%--                        <td><%= book.getBook_Title() %></td>--%>
<%--                        <td><%= book.getAuthor_Name() %></td>--%>
<%--                        <td><%= book.category_name %></td>--%>
<%--                        <td><%= book.getNos_Available() %></td>--%>
<%--                        <td><%= book.getPublished_year() %></td>--%>
<%--                    </tr>--%>
<%--                    <% } %>--%>
<%--                    </tbody>--%>
<%--                </table>--%>
<%--            </div>--%>

<%--            --%>
<%--        </div>--%>
<%--    </div>--%>
<%--<% } else {--%>
<%--    RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");--%>
<%--    dispatcher.forward(request, response);--%>
<%--}--%>
<%--%>--%>

</body>
</html>