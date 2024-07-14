<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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
<%--<jsp:include page="/header.jsp"/>--%>
<%@ include file="/header.jsp"%>
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
<%@include file="/footer.jsp"%>
<%--<jsp:include page="/footer.jsp"/>--%>
</body>
</html>