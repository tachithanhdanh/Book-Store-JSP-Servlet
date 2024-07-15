<%@ page import="model.Customer" %><%--
  Created by IntelliJ IDEA.
  User: LAPTOP ACER
  Date: 07/07/2024
  Time: 7:18 PM
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
    <title>Profile</title>
    <style>
        body{
            /*margin-top:20px;*/
            color: #1a202c;
            text-align: left;
            background-color: #e2e8f0;
        }
        .main-body {
            padding: 15px;
        }
        .card {
            box-shadow: 0 1px 3px 0 rgba(0,0,0,.1), 0 1px 2px 0 rgba(0,0,0,.06);
        }

        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid rgba(0,0,0,.125);
            border-radius: .25rem;
        }

        .card-body {
            flex: 1 1 auto;
            min-height: 1px;
            padding: 1rem;
        }

        .gutters-sm {
            margin-right: -8px;
            margin-left: -8px;
        }

        .gutters-sm>.col, .gutters-sm>[class*=col-] {
            padding-right: 8px;
            padding-left: 8px;
        }
        .mb-3, .my-3 {
            margin-bottom: 1rem!important;
        }

        .bg-gray-300 {
            background-color: #e2e8f0;
        }
        .h-100 {
            height: 100%!important;
        }
        .shadow-none {
            box-shadow: none!important;
        }
    </style>
</head>
<body>
<%@include file="/header.jsp"%>
<%--<jsp:include page="/header.jsp"/>--%>
<div class="container mt-3">
    <div class="main-body">

        <!-- Breadcrumb -->
        <nav aria-label="breadcrumb" class="main-breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/user">User</a></li>
                <li class="breadcrumb-item active" aria-current="page">User Profile</li>
            </ol>
        </nav>
        <!-- /Breadcrumb -->

        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <%
                                Customer loggedCustomer = (Customer) session.getAttribute("loggedCustomer");
                                String avatar = loggedCustomer.getAvatar();
                            if (avatar == null || avatar.isEmpty()) {
                                avatar = "https://bootdey.com/img/Content/avatar/avatar7.png";
                            } else {
                                avatar = "/user/img/" + avatar;
                            }
                            %>
                            <img src="<%=avatar%>" alt="Admin" class="rounded-circle border-primary border" width="200" height="200">
                            <div class="mt-3">
                                <h4>${loggedCustomer.fullName}</h4>
                            </div>
                            <div class="mt-3">
                                <a class="btn btn-primary" target="_blank" href="${pageContext.request.contextPath}/user/change-avatar.jsp">Change Avatar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="h3 mb-3">Account Information</div>
                        </div>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Username</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${loggedCustomer.username}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-primary " target="_self" href="${pageContext.request.contextPath}/user/change-password.jsp">Change password</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="h3 mb-3">User Information</div>
                        </div>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Full Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${loggedCustomer.fullName}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${loggedCustomer.email}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Phone</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${loggedCustomer.phoneNumber}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Gender</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${loggedCustomer.gender}
                            </div>
                        </div>
                    </div>
<%--                </div>--%>
<%--                <div class="card mb-3">--%>
                    <div class="card-body">
                        <div class="row">
                            <div class="h3 mb-3">Address</div>
                        </div>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Billing address</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${loggedCustomer.billingAddress}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Shipping address</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${loggedCustomer.shippingAddress}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Invoice address</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${loggedCustomer.invoiceAddress}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-primary " target="_blank" href="${pageContext.request.contextPath}/user/change-info.jsp">Change information</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>
<%@include file="/footer.jsp"%>
</body>
</html>
