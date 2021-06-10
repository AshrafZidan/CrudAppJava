
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            <a class="navbar-brand"> User Management App </a>
        </div>

        <ul class="navbar-nav">
            <li>
                <form  method="post" action="/user">
                <a href="<%=request.getContextPath()%>/user"
                   class="nav-link">Users</a>
                    <input type="hidden" value="listUsers" name="actionType">
                </form>
            </li>

        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form  method="post" action="/user">
            <c:if test="${user != null}">
                <input type="hidden" name="actionType" value="update">
            </c:if>
            <c:if test="${user == null}">
                    <input type="hidden" name="actionType" value="insert">

                </c:if>

            <caption>
                <h2>
                    <%
                        System.out.println(request.getParameter("user"));
                    %>
                    <c:if test="${user != null}">
                        <input value="${user}">
                        Edit User
                    </c:if>

                    <c:if test="${user == null}">
                        Add New User
                    </c:if>
                </h2>
            </caption>

            <c:if test="${user != null}">
                <input type="hidden" name="userId" value="<c:out value='${user.id}' />" />
            </c:if>

                    <fieldset class="form-group">
                        <label>User Name</label>
                        <input type="text"
                                                        value="<c:out value='${user.name}' />" class="form-control"
                                                        name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Email</label> <input type="text"
                                                         value="<c:out value='${user.email}' />" class="form-control"
                                                         name="email">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Country</label> <input type="text"
                                                           value="<c:out value='${user.country}' />" class="form-control"
                                                           name="country">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>