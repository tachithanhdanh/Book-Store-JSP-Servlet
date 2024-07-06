<%--
  Created by IntelliJ IDEA.
  User: LAPTOP ACER
  Date: 06/07/2024
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Only page</title>
</head>
<body>
Hello <strong>${loggedCustomer.username}</strong><br>
Here is your information:<br>
Username: ${loggedCustomer.username}<br>
Full name: ${loggedCustomer.fullName}<br>
Email: ${loggedCustomer.email}<br>
Phone: ${loggedCustomer.phoneNumber}<br>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>
