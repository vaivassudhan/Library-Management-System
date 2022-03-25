<%@ page import="Category.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: vaivas
  Date: 18/03/22
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LMS - ZOHO</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<style>
td{
  text-align : center;
}
th{
    text-align : center;
}
</style>
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
                    <a class="nav-link active" href="view-category">View Category</a>
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

  <div class="row justify-content-center mt-4">
    <div class="col-md-6">
        <h4 class="text-center mb-4">Category List</h4>
          <table class="table table-striped">
          <thead>
              <tr>
                <th >#</th>
                <th>Category Name</th>
              </tr>
            </thead>
            <tbody>
                <%
                List<Category> allCategoryList = (ArrayList<Category>)request.getAttribute("allCategoryList");
                for(Category category:allCategoryList){
                %>
                <tr>
                <td><%= category.getCategory_Id() %></td>
                  <td><%= category.getCategory_Name() %></td>
                </tr>
                <% } %>
            </tbody>
          </table>
    </div>
      <div class="col-md-6">
          <h4 class="text-center mb-4">Search Books</h4>
          <form action="<%=request.getContextPath()%>/search-by-category" method="post">
              <div class="form-group my-2" style="font-size: 1.3rem">
                  <label for="Category_Id" class="form-label">Search Book By Category : </label>
                  <select class="form-select form-select-lg mb-3" name="Category_Id" id="Category_Id">
                      <% for(Category category: allCategoryList){%>
                      <option value="<%= category.getCategory_Id() %>"> <%= category.getCategory_Name() %> </option>

                      <% } %>
                  </select>
                  <button type="submit" class="btn btn-primary float-right">Search</button>
              </div>
          </form>
      </div>
  </div>
<%--    <div class="row justify-content-center mt-4">--%>
<%--        --%>
<%--    </div>--%>
</div>

</body>
</html>
