<%--
  Created by IntelliJ IDEA.
  User: LAPTOP ACER
  Date: 14/07/2024
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/assets/js/popper.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <title>Reset password</title>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="col-lg-4 border p-4 shadow-sm bg-white rounded mt-5">
        <h2 class="text-center">CHANGE PASSWORD</h2>
        <p class="text-center" style="color: red">${message}</p>
        <p class="text-center">Please enter your username and your email, we'll send an random password to your inbox:</p>
        <form method="post" action="${pageContext.request.contextPath}/account">
            <input type="hidden" name="action" value="reset-password">
            <div class="mb-3 form-floating">
                <input type="text" class="form-control" id="username" name="username" placeholder="Your username" required value="${username}">
                <label for="username" class="form-label">Username</label>
                <div id="errorUsername" class="invalid-feedback"></div>
            </div>
            <div class="mb-3 form-floating">
                <input type="email" class="form-control" id="email" name="email" placeholder="Your email" required value="${email}">
                <label for="email" class="form-label">Email</label>
                <div id="errorEmail" class="invalid-feedback"></div>
            </div>
            <input id="changePasswordBtn" class="btn btn-primary w-100" type="submit" value="Send me new password">
        </form>
        <a href="${pageContext.request.contextPath}/account/login.jsp" class="btn btn-secondary w-100 mt-1">Back to login page</a>
    </div>
</div>
<%
    session.removeAttribute("message");
%> <!-- remove all temporary variables -->
<script src="${pageContext.request.contextPath}/assets/js/reset-password.js" type="text/javascript" defer></script>
</body>
</html>
