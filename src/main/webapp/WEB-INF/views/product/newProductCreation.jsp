<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>New Product</title>
    <link rel="stylesheet" href="styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .required-field::after{
             content: "*";
             color: red;
        }
    </style>
</head>

<body>

<div class="container-fluid">

    <jsp:include page="/navbar.jsp" />

    <div class="row pt-3 pl-3 flex-column">
        <h2 class="  display-3" style="font-size: 30px">
            <b> Create Products </b>
        </h2>
    </div>

    <hr width="" color="#b3b3b3">

</div>

<!-- Form Container -->
<div class="container-fluid">
    <form:form modelAttribute="product" method="post">
        <div class="row">
            <div class="col-sm-3">

                <div class="form-group">
                    <form:label path="productCode" cssClass="font-weight-bold required-field">Product Code:</form:label>
                    <form:input cssClass="form-control" path="productCode"/>
                    <form:errors path="productCode"/>
                </div>

                <div class="form-group">
                    <form:label path="productDescription" cssClass="font-weight-bold">Product Description:</form:label>
                    <form:textarea rows="3" cssClass="form-control" path="productDescription"/>
                </div>

                <div class="form-group">
                    <form:label path="maxExposureAmount" cssClass="font-weight-bold">Maximum Exposure Amount:</form:label>
                    <form:input type="number" cssClass="form-control" path="maxExposureAmount"/>
                </div>

            </div>
            <div class="col-sm-3 offset-sm-4">

                <div class="form-group">
                    <form:label path="productName" cssClass="font-weight-bold required-field">Product Name:</form:label>
                    <form:input cssClass="form-control" path="productName"/>
                    <form:errors path="productCode"/>
                </div>

                <div class="form-group">
                    <label for="productType" class="font-weight-bold required-field">Product Type</label>
                    <select class="form-control" id="productType">
                        <option value="" selected disabled hidden>Select One Option</option>
                    </select>
                </div>

            </div>
        </div>

        <hr width="" color="#b3b3b3">
        <div class="row pt-3 pl-3 flex-column">
            <h2 class="  display-3" style="font-size: 30px">
                <b> Policies </b>
            </h2>
        </div>
        <div class="row col-sm-8">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">Policy</th>
                    <th scope="col">Policy Name</th>
                    <th scope="col">Policy Description</th>
                </tr>
                </thead>

                <tr>
                    <td>
                        <label for="repaymentPolicy" class="required-field">Repayment Policy</label>
                    </td>
                    <td>
                        <select class="form-control" id="repaymentPolicy">
                            <option value="" selected disabled hidden>Choose a Policy</option>
                        </select>
                    </td>
                    <td><input class="form-control" type="text" disabled></td>
                </tr>
                <tr>
                    <td>
                        <label for="eligibilityPolicy" class="required-field">Eligibility Policy</label>
                    </td>
                    <td>
                        <select class="form-control" id="eligibilityPolicy">
                            <option value="" selected disabled hidden>Choose a Policy</option>
                        </select>
                    </td>
                    <td><input class="form-control" type="text" disabled></td>
                </tr>
                <tr>
                    <td>Charge Policy</td>
                    <td>
                        <select class="form-control" id="chargePolicy">
                            <option value="" selected disabled hidden>Choose a Policy</option>
                        </select>
                    </td>
                    <td><input class="form-control" type="text" disabled></td>
                </tr>

            </table>
        </div>
    </form:form>
    <hr width="" color="#b3b3b3">
    <div class="row" style="margin-bottom:20px">
        <div class="col-sm-3 offset-sm-9">
            <button type="button" class="btn btn-primary" id="save">Save</button>
            <button type="button" class="btn btn-primary" id="saveAndRequest">Save & Request Approval</button>
        </div>
    </div>
</div>
</body>
</html>
