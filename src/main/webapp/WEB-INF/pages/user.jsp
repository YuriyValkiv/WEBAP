<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>WEBAPP: Users</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/css/styles.css"/>
</head>
<body>
    <div class="container">
        <div>
            <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                <h2>Welcome: <c:out value="${username}"/></h2>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                <a href="/logout"><input type="button" name="login" class="btn-default" value="Logout"></a>
            </sec:authorize>
        </div>
        <h2>Users</h2>
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Password</th>
                <th>First Name</th>
                <th>E-mail</th>
                <th></th>
            </tr>
            </thead>
            <tr>
            <c:forEach var="users" items="${users}">
                <tr id="row${users.id}" class="info">
                    <th>${users.id}</th>
                    <th>${users.name}</th>
                    <th>******</th>
                    <th>${users.firstName}</th>
                    <th>${users.email}</th>
                    <th><button type="button" class="btn-primary" id="btn-change" onclick="change(${users.id})">Change</button></th>
                    <th><button type="button" class="btn-danger" onclick="removeUser(${users.id})">Delete</button></th>
                </tr>
                <tr id="hiddenRow${users.id}" class="info" style="display: none;">
                    <th>${users.id}</th>
                    <th><input type="text" class="form-control" id="name${users.id}" value="${users.name}"></th>
                    <th><input type="password" class="form-control" id="password${users.id}" value="${users.password}"></th>
                    <th><input type="text" class="form-control" id="firstName${users.id}" value="${users.firstName}"></th>
                    <th><input type="text" class="form-control" id="email${users.id}" value="${users.email}"></th>
                    <th><button type="button" class="btn-danger form-control" onclick="editUser(${users.id})">Accept</button></th>
                    <th><input type="hidden" id="time" class="btn-danger form-control"/></th>
                </tr>
            </c:forEach>
            <tr id="hiddenRowTwo" class="info" style="display: none;">
                <form action="/user/add" method="get">
                <th>#</th>
                <th>
                    <input type="text" class="form-control" id="name" name="name" required>
                    <span class="reg_rules" id="validateLoginRules">Only symbols: {A-z, 0-9, _}. From 2 to 25 symbols</span>
                </th>
                <th>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <span class="reg_rules" id="validatePasswordRules">At least one:[A-z,0-9,special symbol].Min 8 symbols</span>
                </th>
                <th>
                    <input type="text" class="form-control" id="firstName" name="firstName" required pattern="^[A-z0-9_]{2,25}">
                    <span class="reg_rules" id="validateFirstNameRules">Only symbols: {A-z}</span>
                </th>
                <th>
                    <input type="text" class="form-control" id="email" name="email" required pattern="^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$">
                    <span class="reg_rules" id="validateEmailRules">Only correct email with symbol {@}</span>
                </th>
                <th><input type="submit" class="btn-danger form-control" id="btn-accept" value="Accept"></th>
                <th><input type="hidden" id="timeTwo" class="btn-danger form-control"/></th>
                </form>
            </tr>

            <tr>
                <button type="button" class="btn-primary" id="btn-add" onclick="add()">Add to DB</button>
            </tr>
            </tbody>
        </table>
    </div>

    <!-- jQuery -->
    <script src="/resources/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/js/bootstrap.min.js"></script>

    <script src="/resources/js/script.js"></script>
</body>
</html>
