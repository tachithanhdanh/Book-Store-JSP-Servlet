<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/assets/js/popper.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <title>Website bán hàng</title>
</head>
<body>
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
                    <a class="nav-link active" aria-current="page" href="#">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Giảm giá cực sốc</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Ấn vô để thấy bất ngờ
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Áo thun giảm 50%</a></li>
                        <li><a class="dropdown-item" href="#">Quần jean giảm 90%</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Nhận 100 đô miễn phí</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" aria-disabled="true">Hết hàng</a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Nội dung" aria-label="Search">
                <button class="btn btn-outline-success me-2" type="submit">Tìm</button>
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
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <!-- Menu Left -->
            <div class="list-group">
                <a href="#" class="list-group-item list-group-item-action">
                    Thời trang nam
                </a>
                <a href="#" class="list-group-item list-group-item-action">Thời trang nữ</a>
                <a href="#" class="list-group-item list-group-item-action">Dành cho bé</a>
            </div>
        </div>
        <!-- End Menu Left -->
        <!-- Slider and products -->
        <div class="col-lg-9">
            <!-- Slider -->
            <div id="carouselExampleCaptions" class="carousel slide">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="img/slider/1.png" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="img/slider/2.png" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="img/slider/3.png" class="d-block w-100" alt="...">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
            <!-- End Slider -->
            <!-- Product -->
            <div class="row">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;" width="200">
                        <img src="img/product/1.png" class="card-img-top" alt="Anh 1">
                        <div class="card-body">
                            <p class="card-text">San pham 1.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;">
                        <img src="img/product/2.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">Test thu.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 18rem;">
                        <img src="img/product/3.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">Test thu.</p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End product -->
        </div>
    </div>
</div>
<!-- Footer -->
<footer class="py-3 my-4">
    <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Home</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Features</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Pricing</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">FAQs</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">About</a></li>
    </ul>
    <p class="text-center text-muted">© 2022 Company, Inc</p>
</footer>
<!-- End Footer -->
</body>
</html>