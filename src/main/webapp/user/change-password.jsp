<%--
  Created by IntelliJ IDEA.
  User: LAPTOP ACER
  Date: 06/07/2024
  Time: 9:27 PM
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
    <title>Change password</title>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="col-lg-4 border p-4 shadow-sm bg-white rounded mt-5">
        <h2 class="text-center">CHANGE PASSWORD</h2>
        <p class="text-center" style="color: red">${message}</p>
        <p class="text-center">Password must be at least 8 characters long, contains at least one lowercase letter, one uppercase letter, one digit, and one special character (#?!@$%^&*-).</p>
        <form method="post" action="${pageContext.request.contextPath}/user">
            <input type="hidden" name="action" value="change-password">
            <div class="mb-3 form-floating">
                <input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="Current password" required value="${currentPassword}">
                <label for="currentPassword" class="form-label">Current password</label>
                <div id="errorCurrentPassword" class="invalid-feedback">${errorCurrentPassword}</div>
            </div>
            <div class="mb-3 form-floating">
                <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="New password" required value="${newPassword}">
                <label for="newPassword" class="form-label">New password</label>
                <div id="errorNewPassword" class="invalid-feedback">${errorNewPassword}</div>
            </div>
            <div class="mb-3 form-floating">
                <input type="password" class="form-control" id="newPasswordRetype" name="newPasswordRetype" placeholder="Retype new password" required value="${newPasswordRetype}">
                <label for="newPasswordRetype" class="form-label">Retype new password</label>
                <div id="errorNewPasswordRetype" class="invalid-feedback">${errorNewPasswordRetype}</div>
            </div>
            <a href="#">Forgotten your password?</a>
            <div class="form-check text-start my-3">
                <input class="form-check-input" type="checkbox" value="true" id="logOutDevices" name="logOutDevices">
                <label class="form-check-label" for="logOutDevices">
                    Log out of other devices. Choose this if someone else used your account.
                </label>
            </div>
            <input id="changePasswordBtn" class="btn btn-primary w-100" type="submit" value="Change password" disabled>
        </form>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary w-100 mt-1">Back to Home</a>
    </div>
</div>
<%
    session.removeAttribute("errorCurrentPassword");
    session.removeAttribute("errorNewPassword");
    session.removeAttribute("errorNewPasswordRetype");
    session.removeAttribute("currentPassword");
    session.removeAttribute("newPassword");
    session.removeAttribute("newPasswordRetype");
    session.removeAttribute("message");
%> <!-- remove all temporary variables -->
<script src="${pageContext.request.contextPath}/assets/js/change-password.js" type="text/javascript" defer></script>
</body>
</html>
