<%@ page import="model.Customer" %>
<%@ page import="java.sql.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: LAPTOP ACER
  Date: 07/07/2024
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- responsive -->
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/js/register.js" type="text/javascript" defer></script> <!-- link to register.js -->
    <title>Change account info</title>
    <style>
        .required {
            color: red;
        }
    </style>
</head>
<body>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Expires", "0");
%>
<%
    Customer customer = (Customer) session.getAttribute("loggedCustomer");
    String fullName = customer.getFullName();
    String gender = customer.getGender();
    Date dateOfBirth = customer.getDateOfBirth();
    String billingAddress = customer.getBillingAddress();
    String shippingAddress = customer.getShippingAddress();
    String invoiceAddress = customer.getInvoiceAddress();
    String phoneNumber = customer.getPhoneNumber();
    String email = customer.getEmail();
    boolean subscribeToNewsletter = customer.isSubscribeToNewsletter();
%>
<div class="container">
    <div class="h1 text-lg-center mt-3">CHANGE ACCOUNT INFORMATION</div> <!-- h1: heading 1, text larger center -->
    <p style="color: red;">${message}</p>
    <% session.removeAttribute("message"); %>
    <form method="post" action="${pageContext.request.contextPath}/user">
        <input type="hidden" name="action" value="change-information">
        <div class="row"> <!-- row: a row in a grid system -->
            <div class="col-lg-6"> <!-- col-lg-6: column large 6, only apply for screen with width >= 996px -->
                <div class="h3">Customer information</div>
                <div class="mb-3">
                    <label for="fullName" class="form-label">Full name</label>
                    <input type="text" class="form-control" id="fullName" name="fullName" value="<%=fullName%>">
                </div>
                <div class="mb-3">
<%--                    <% String gender = request.getParameter("gender"); %>--%>
                    <label for="gender" class="form-label">Gender</label>
                    <select id="gender" class="form-select" name="gender">
                        <option value="" <%= (gender == null || gender.isEmpty()) ? "selected" : "" %>></option>
                        <option value="Male" <%= "Male".equals(gender) ? "selected" : "" %>>Male</option>
                        <option value="Female" <%= "Female".equals(gender) ? "selected" : "" %>>Female</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="dateOfBirth" class="form-label">Date of birth</label>
                    <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="<%=dateOfBirth%>" placeholder="<%=dateOfBirth%>">
                </div>
            </div>
            <div class="col-lg-6">
                <div class="h3">Address</div>
                <div class="mb-3">
                    <label for="billingAddress" class="form-label">Billing Address</label>
                    <input type="text" class="form-control" id="billingAddress" name="billingAddress" aria-describedby="billingAddressHelp" value="<%=billingAddress%>">
                    <div id="billingAddressHelp" class="form-text"></div>
                </div>
                <div class="mb-3">
                    <label for="shippingAddress" class="form-label">Shipping Address</label>
                    <input type="text" class="form-control" id="shippingAddress" name="shippingAddress" aria-describedby="shippingAddressHelp" value="<%=shippingAddress%>">
                    <div id="shippingAddressHelp" class="form-text"></div>
                </div>
                <div class="mb-3">
                    <label for="invoiceAddress" class="form-label">Invoice Address</label>
                    <input type="text" class="form-control" id="invoiceAddress" name="invoiceAddress" aria-describedby="invoiceAddressHelp" value="<%=invoiceAddress%>">
                    <div id="invoiceAddressHelp" class="form-text"></div>
                </div>
                <div class="mb-3">
                    <label for="phoneNumber" class="form-label">Phone number</label>
                    <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="<%=phoneNumber%>">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%=email%>">
                    <div id="errorEmail" class="required"></div>
                </div>
                <hr/>
                <div class="mb-3 form-check">
                    <label class="form-check-label" for="subscribeToNewsletter">
                        Receive promotional emails
                    </label>
                    <input class="form-check-input" type="checkbox" value="true" id="subscribeToNewsletter" name="subscribeToNewsletter"
                        <%= subscribeToNewsletter ? "checked" : "" %> >
                </div>
                <input id="submit" class="btn btn-primary" type="submit" value="Save information">
                <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary ms-3">Back to Home</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>

</html>
