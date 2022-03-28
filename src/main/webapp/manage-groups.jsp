<%@ page import="Group_days.GroupDays" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: vaivas
  Date: 28/03/22
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LMS - Zoho</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<style>
    td{
        text-align : center;
    }
    th{
        text-align : center;
    }
</style>
<%--Check if user is logged in --%>
<%
    if(session.getAttribute("Librarian_Id") == null){
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
        dispatcher.forward(request,response);
    }
    if((Integer) session.getAttribute("Role") == 2){
        request.setAttribute("message-type","error");
        request.setAttribute("message","Not Authorized to access");
        RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
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
        <div class="col-md-6">
            <h4 class="text-center mb-4">Group List</h4>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Group ID</th>
                    <th>Days </th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<GroupDays> allGroups = (ArrayList<GroupDays>)request.getAttribute("allGroups");
                    for(GroupDays group:allGroups){

                %>
                <tr>
                    <td><%= group.getGroup_Id() %></td>
                    <td><%= group.getDays() %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <div class="col-md-4 mx-4">
            <h4 class="display4 text-center mb-4">Add New Group</h4>
            <hr>
            <form method="post" action="add-group">
                <div class="form-group">
                    <label for="Days">No of days : </label>
                    <input type="Number" class="form-control" id="Days" name="Days" placeholder="No of Days">
                </div>
                <div class="form-group">
                    <label for="Days">Fine Per Day : </label>
                    <input type="Number" class="form-control" id="Fine_Per_Day" name="Fine_Per_Day" placeholder="Fine per day">
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
            <hr>
            <h4 class="display4 text-center mb-4">Update Days</h4>
            <hr>
            <form method="post" action="update-days">
                <div class="form-group">
                    <label for="UGroup_Id">Group ID : </label>
                    <input type="text" class="form-control" id="UGroup_Id" name="Group_Id" placeholder="Enter Group ID">
                </div>
                <div class="form-group">
                    <label for="UDays">No of days : </label>
                    <input type="text" class="form-control" id="UDays" name="Days" placeholder="Update Days">
                </div>
                <button type="submit" class="btn btn-primary">Update</button>
            </form>
        </div>
    </div>
<%--    <div class="row justify-content-center mt-4">--%>
<%--        <div class="col-md-4 mx-4">--%>
<%--            --%>
<%--        </div>--%>
<%--    </div>--%>
</div>

</body>
</html>


