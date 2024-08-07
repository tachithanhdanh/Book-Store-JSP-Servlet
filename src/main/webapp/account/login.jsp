<%--
  Created by IntelliJ IDEA.
  User: LAPTOP ACER
  Date: 04/07/2024
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- responsive -->
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/assets/css/login-styles.css" rel="stylesheet" type="text/css">
    <title>Login</title>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">
<main class="form-signin w-100 m-auto">
    <form action="${pageContext.request.contextPath}/account" class="text-center" method="post">
        <input type="hidden" name="action" value="login">
        <img class="mb-4" src="${pageContext.request.contextPath}/img/login/logo.png" alt="" width="50%">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
        <p style="color: red;">${error}</p>
        <% session.removeAttribute("error"); %>
<%--        <c:if test="${param.redirect != null}">--%>
            <input type="hidden" name="redirect" value="${param.redirect}">
<%--        </c:if>--%>
        <div class="form-floating">
            <input type="text" class="form-control" id="username" placeholder="Username" name="username">
            <label for="username">Username</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="password" placeholder="Password" name="password">
            <label for="password">Password</label>
        </div>

        <div class="form-check text-start my-3">
            <input class="form-check-input" type="checkbox" value="true" id="stayLoggedIn" name="stayLoggedIn">
            <label class="form-check-label" for="stayLoggedIn">
                Stay logged in
            </label>
        </div>
        <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
        <p class="mt-3"><a href="${pageContext.request.contextPath}/account/request-password.jsp">Forgot your password?</a></p>
        <hr/>
        <p class="mt-3">Don't have an account?<a href="${pageContext.request.contextPath}/account/register.jsp">Register now</a></p>
        <p class="mt-5 mb-3 text-body-secondary">&copy; 2017–2024</p>
    </form>
</main>
</body>
</html>
