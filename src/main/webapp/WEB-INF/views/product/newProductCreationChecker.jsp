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

        .error{
            color: red;
        }
    </style>
</head>

<body>

<div class="container-fluid">

    <jsp:include page="/navbar.jsp" />

    <div class="row pt-3 pl-3 flex-column">
        <h2 class="  display-3" style="font-size: 30px">
            <b> Review Product Details </b>
        </h2>
    </div>

    <hr width="" color="#b3b3b3">

</div>

<sec:authorize access="hasRole('CHECKER')">
    <div class="container-fluid">
        <form action="${product.productCode}/update" method="post">
            <div class="row">
                <div class="col-sm-3">

                    <div class="form-group">
                        <label class="font-weight-bold required-field">Product Code:</label>
                        <input class="form-control" disabled="disabled" value="${product.productCode}"/>
                    </div>

                    <div class="form-group">
                        <label class="font-weight-bold">Product Description:</label>
                        <textarea rows="3" disabled="disabled" class="form-control">${product.productDescription}</textarea>
                    </div>

                    <div class="form-group">
                        <label class="font-weight-bold">Maximum Exposure Amount:</label>
                        <input type="number" disabled="disabled" value="${product.maxExposureAmount}" class="form-control"/>
                    </div>

                </div>
                <div class="col-sm-3 offset-sm-4">

                    <div class="form-group">
                        <label class="font-weight-bold required-field">Product Name:</label>
                        <input class="form-control" disabled="disabled" value="${product.productName}"/>
                    </div>

                    <div class="form-group">
                        <label class="font-weight-bold required-field">Product Type:</label>
                        <select class="form-control">
                            <option value="-"  disabled="disabled" selected="true">${product.productType}</option>
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
                            <label class="required-field">Repayment Policy</label>
                        </td>
                        <td>
                            <select class="form-control">
                                <option value="-"  disabled="disabled" selected>${product.repaymentPolicyCode.policyCode}</option>
                            </select>
                        </td>
                        <td><input class="form-control" id="repaymentDesc" type="text" value="${product.repaymentPolicyCode.policyDescription}" disabled></td>
                    </tr>
                    <tr>
                        <td>
                            <label class="required-field">Eligibility Policy</label>
                            <br>
                        </td>
                        <td>
                            <select class="form-control">
                                <option value="-"  disabled="disabled" selected>${product.eligibilityPolicyCode.policyCode}</option>
                            <select>
                        </td>
                        <td><input class="form-control" id="eligibilityDesc" type="text" value="${product.eligibilityPolicyCode.policyDescription}" disabled></td>
                    </tr>
                    <tr>
                    <c:if test="${!empty product.chargeCodePolicy}">
                        <td>Charge Policy</td>
                        <td>
                            <select class="form-control">
                                <option value="-"  disabled="disabled" selected> ${product.chargeCodePolicy.chargePolicyCode} </option>s
                            </select>
                        </td>
                        <td><input class="form-control" id="chargeDesc" type="text" value="${product.chargeCodePolicy.chargePolicyDesc}" disabled></td>
                    </c:if>
                    <c:if test="${empty product.chargeCodePolicy}">
                        <td>Charge Policy</td>
                        <td>
                            <select class="form-control">
                                <option value="-"  disabled="disabled" selected/>None</option>
                            </select>
                        </td>
                        <td><input class="form-control" id="chargeDesc" type="text" disabled></td>
                    </c:if>
                    </tr>

                </table>
            </div>
            <hr width="" color="#b3b3b3">
            <div class="row" style="margin-bottom:20px">
                <div class="col-sm-3 offset-sm-9">
                    <c:if test = "${product.status != 'APPROVED'}">
                        <button type="submit" class="btn btn-primary" name ="action" value="REJECTED">Reject</button>
                        <button type="submit" class="btn btn-primary" name ="action" value="APPROVED">Approve</button>
                    </c:if>
                    <c:if test = "${product.status == 'APPROVED'}">
                        <button type="submit" disabled="disabled" class="btn btn-primary" name ="action" value="REJECTED">Reject</button>
                        <button type="submit" disabled="disabled" class="btn btn-primary" name ="action" value="APPROVED">Approve</button>
                    </c:if>
                </div>
            </div>
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>
    </sec:authorize>



</div>
</body>
</html>
