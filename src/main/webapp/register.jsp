<%--<jsp:useBean id="email" scope="request" type="java.lang.String"/>--%>
<%--<jsp:useBean id="phoneNumber" scope="request" type="java.lang.String"/>--%>
<%--<jsp:useBean id="invoiceAddress" scope="request" type="java.lang.String"/>--%>
<%--<jsp:useBean id="shippingAddress" scope="request" type="java.lang.String"/>--%>
<%--<jsp:useBean id="billingAddress" scope="request" type="java.lang.String"/>--%>
<%--<jsp:useBean id="dateOfBirth" scope="request" type="java.lang.String"/>--%>
<%--<jsp:useBean id="fullName" scope="request" type="java.lang.String"/>--%>
<%--<jsp:useBean id="username" scope="request" type="java.lang.String"/>--%>
<%--
  Created by IntelliJ IDEA.
  User: LAPTOP ACER
  Date: 28/06/2024
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- responsive -->
    <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="assets/js/register.js" type="text/javascript" defer></script> <!-- link to register.js -->
    <title>Register</title>
    <style>
        .required {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="h1 text-lg-center mt-3">REGISTER NEW ACCOUNT</div> <!-- h1: heading 1, text larger center -->
        <form method="post" action="register-servlet">
            <div class="row"> <!-- row: a row in a grid system -->
                <div class="col-lg-6"> <!-- col-lg-6: column large 6, only apply for screen with width >= 996px -->
                    <div class="h3">Account</div> <!-- h3: heading 3 -->
                    <div class="mb-3"> <!-- mb-3: margin bottom 3 -->
                        <label for="username" class="form-label">Username<span class="required">*</span></label>
                        <input type="text" class="form-control" id="username" name="username" aria-describedby="usernameHelp" required value="${username}">
                        <div id="usernameHelp" class="form-text" style="display: none">Username contains lowercase letters and numbers but starts with a lowercase letter, length 6-30.</div>
                        <div id="errorUsername" class="required">${errorUsername}</div>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password<span class="required">*</span></label>
                        <input type="password" class="form-control" id="password" name="password" aria-describedby="passwordHelp" required>
                        <div id="passwordHelp" class="form-text" style="display: none">Password must be at least 8 characters long, contain at least one lowercase letter, one uppercase letter, one digit, and one special character
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="passwordConfirm" class="form-label">Confirm your password<span class="required">*</span></label>
                        <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" aria-describedby="passwordConfirmHelp" required>
                        <div id="passwordConfirmHelp" class="form-text" hidden>Retype your password</div>
                        <div id="errorPasswordConfirm" class="required">${errorPasswordConfirm}</div>
                    </div>
                    <hr/>
                    <div class="h3">Customer information</div>
                    <div class="mb-3">
                        <label for="fullName" class="form-label">Full name</label>
                        <input type="text" class="form-control" id="fullName" name="fullName" value="${fullName}">
                    </div>
                    <div class="mb-3">
                        <% String gender = request.getParameter("gender"); %>
                        <label for="gender" class="form-label">Gender</label>
                        <select id="gender" class="form-select" name="gender">
                            <option value="" <%= (gender == null || gender.isEmpty()) ? "selected" : "" %>></option>
                            <option value="Male" <%= "Male".equals(gender) ? "selected" : "" %>>Male</option>
                            <option value="Female" <%= "Female".equals(gender) ? "selected" : "" %>>Female</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="dateOfBirth" class="form-label">Date of birth</label>
                        <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${dateOfBirth}">
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="h3">Address</div>
                    <div class="mb-3">
                        <label for="billingAddress" class="form-label">Billing Address</label>
                        <input type="text" class="form-control" id="billingAddress" name="billingAddress" aria-describedby="billingAddressHelp" value="${billingAddress}">
                        <div id="billingAddressHelp" class="form-text"></div>
                    </div>
                    <div class="mb-3">
                        <label for="shippingAddress" class="form-label">Shipping Address</label>
                        <input type="text" class="form-control" id="shippingAddress" name="shippingAddress" aria-describedby="shippingAddressHelp" value="${shippingAddress}">
                        <div id="shippingAddressHelp" class="form-text"></div>
                    </div>
                    <div class="mb-3">
                        <label for="invoiceAddress" class="form-label">Invoice Address</label>
                        <input type="text" class="form-control" id="invoiceAddress" name="invoiceAddress" aria-describedby="invoiceAddressHelp" value="${invoiceAddress}">
                        <div id="invoiceAddressHelp" class="form-text"></div>
                    </div>
                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Phone number</label>
                        <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="${phoneNumber}">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${email}">
                        <div id="errorEmail" class="required"></div>
                    </div>
                    <hr/>
                    <div class="mb-3 form-check">
                        <label class="form-check-label" for="acceptTermCondition">
                            Accept the terms and conditions<span class="required">*</span>
                        </label>
                        <input class="form-check-input" type="checkbox" value="" id="acceptTermCondition" required>
                    </div>
                    <div class="mb-3 form-check">
                        <label class="form-check-label" for="subscribeToNewsletter">
                            Receive promotional emails
                        </label>
                        <input class="form-check-input" type="checkbox" value="" id="subscribeToNewsletter">
                    </div>
                    <input id="submit" class="btn btn-primary" type="submit" value="Submit" disabled>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
