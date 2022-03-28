<%@ page import="Fine.Fine" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: vaivas
  Date: 28/03/22
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
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


<div class="container pt-5">
    <div class="row justify-content-center mt-4">
        <div class="col-md-6">
            <h4 class="text-center mb-4">Fine Amount List</h4>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Group ID</th>
                    <th>Fine Per Day </th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Fine> allFine = (ArrayList<Fine>)request.getAttribute("allFine");
                    for(Fine fine:allFine){

                %>
                <tr>
                    <td><%= fine.getGroup_Id() %></td>
                    <td><%= fine.getFine_Per_Day() %></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <div class="col-md-4 mx-4">
            <h4 class="display4 text-center mb-4">Update Fine</h4>
            <hr>
            <form method="post" action="update-fine">
                <div class="form-group">
                    <label for="UGroup_Id">Group ID : </label>
                    <input type="text" class="form-control" id="UGroup_Id" name="Group_Id" placeholder="Enter Group ID">
                </div>
                <div class="form-group">
                    <label for="UDays">Fine Per day : </label>
                    <input type="text" class="form-control" id="UDays" name="Days" placeholder="Update Days">
                </div>
                <button type="submit" class="btn btn-primary">Update</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
