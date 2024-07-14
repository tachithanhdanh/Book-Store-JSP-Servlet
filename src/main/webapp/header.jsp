<%--
  Created by IntelliJ IDEA.
  User: LAPTOP ACER
  Date: 08/07/2024
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="https://getbootstrap.com/docs/5.3/assets/brand/bootstrap-logo.svg" alt="Bootstrap" width="30" height="24">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/index.jsp">Homepage</a>
                </li>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="#">Giảm giá cực sốc</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item dropdown">--%>
<%--                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">--%>
<%--                        Ấn vô để thấy bất ngờ--%>
<%--                    </a>--%>
<%--                    <ul class="dropdown-menu">--%>
<%--                        <li><a class="dropdown-item" href="#">Áo thun giảm 50%</a></li>--%>
<%--                        <li><a class="dropdown-item" href="#">Quần jean giảm 90%</a></li>--%>
<%--                        <li><hr class="dropdown-divider"></li>--%>
<%--                        <li><a class="dropdown-item" href="#">Nhận 100 đô miễn phí</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link disabled" aria-disabled="true">Hết hàng</a>--%>
<%--                </li>--%>
            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Nội dung" aria-label="Search">
                <button class="btn btn-outline-success me-2" type="submit">Search</button>
                <c:if test="${loggedCustomer == null}">
                    <div class="w-50 text-center">
                        <a href="${pageContext.request.contextPath}/account/login.jsp" class="btn btn-primary active" role="button" aria-pressed="true" style="white-space: nowrap;">
                            Log in
                        </a>
                    </div>
                </c:if>
                <c:if test="${loggedCustomer != null}">
                    <%--                    Hello <strong>${loggedCustomer.username}</strong><br>--%>
                    <%--                    <a href="${pageContext.request.contextPath}/logout">Logout</a>--%>
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Account
                        </button>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="#">My order</a></li>
                            <li><a class="dropdown-item" href="#">Notifications</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/profile.jsp">Account information</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/change-info.jsp">Change information</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/change-password.jsp">Change password</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user?action=logout">Logout</a></li>
                        </ul>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</nav>
<!-- End Navbar -->