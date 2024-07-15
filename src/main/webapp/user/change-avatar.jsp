<%--
  Created by IntelliJ IDEA.
  User: LAPTOP ACER
  Date: 15/07/2024
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- responsive -->
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/assets/js/popper.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <title>Change avatar</title>
</head>
<body>
<%@include file="/header.jsp"%>
<div class="container d-flex justify-content-center align-items-center" style="min-height: 70vh;">
    <div class="col-lg-6 border p-4 shadow-sm bg-white rounded mt-5">
        <div class="h1 text-lg-center mt-3">CHANGE AVATAR</div> <!-- h1: heading 1, text larger center -->
        <p style="color: red;">${message}</p>
        <% session.removeAttribute("message"); %>
        <form method="post" action="${pageContext.request.contextPath}/user/change-avatar" enctype="multipart/form-data">
            <%--        <input type="hidden" name="action" value="change-avatar">--%>
            <label class="form-label" for="avatar">Upload your new avatar</label>
            <input type="file" class="form-control" id="avatar" name="avatar" required/>
                <div class="text-center">
                    <input id="changeAvatarBtn" class="btn btn-primary w-50 mt-3 align-content-center" type="submit" value="Change avatar">
                </div>

        </form>
    </div>
</div>
<%@include file="/footer.jsp"%>
</body>
</html>
