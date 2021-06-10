<%@ page import="com.train.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
 <html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> User
                Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/user?actionType=user"
                   class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/user?actionType=addNew" class="btn btn-success">Add
                New User</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>

            <%
                List<User> users   = (ArrayList<User>)request.getAttribute("listUsers");

                for(int i = 0 ;i < users.size(); i++) {

                    User usr = users.get(i);

            %>

            <tr>
                <td><%=usr.getId()%></td>
                <td><%=usr.getName()%></td>
                <td><%=usr.getEmail()%></td>
                <td><%=usr.getCountry()%></td>

                <td>

                <a href="<%=request.getContextPath()%>/user?actionType=edit&userId=<%=usr.getId()%>">
                    Edit
                </a>
                    <a href="<%=request.getContextPath()%>/user?actionType=delete&userId=<%=usr.getId()%>">

                        Delete
                    </a>
                </td>



            </tr>

            <%
                }
            %>


            <%--<c:forEach var="user" items="${listUsers}">--%>

                <%--<tr>--%>
                    <%--<td><c:out value="${user.id}" /></td>--%>
                    <%--<td><c:out value="${user.name}" /></td>--%>
                    <%--<td><c:out value="${user.email}" /></td>--%>
                    <%--<td><c:out value="${user.country}" /></td>--%>
                    <%--<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>--%>

                        <%--<a href="delete?id=<c:out value='${user.id}'/>">Delete</a></td>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>


            </tbody>

        </table>
    </div>
</div>
</body>
</html>