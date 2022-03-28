<%@ page import="Student.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: vaivas
  Date: 25/03/22
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
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
                <h3 class="text-center">Student List</h3>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Student Name</th>
                        <th>Gender</th>
                        <th>Group </th>
                        <th>Mobile Number</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Student> allStudent = (ArrayList<Student>)request.getAttribute("allStudent");
                        for(Student student:allStudent){

                    %>
                    <tr>
                        <td><%= student.getStudent_Id() %></td>
                        <td><%= student.getStudent_Name() %></td>
                        <td><%= student.getGender() %></td>
                        <td><%= student.getGroup_id() %></td>
                        <td><%= student.getMobile() %></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <div class="col-md-3 mx-4">
                <h3 class="display4 text-center">Add Student</h3>
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