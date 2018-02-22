<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <title>WEBAPP</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css"/>
<body>
    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
        <div class="form-login formM">
            <h1>Login to Your Account</h1><br>
            <form action="/login" method="post">
                <div class="form-group">
                    <input type="text" name="username" class="form-control input-sm chat-input" placeholder="Username (e-mail)">
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control input-sm chat-input" placeholder="Password">
                </div>
                <div class="form-group">
                    <input type="submit" name="login" class="btn btn-primary btn-md" value="Login">
                </div>
            </form>
        </div>
    </sec:authorize>
</body>
</html>
