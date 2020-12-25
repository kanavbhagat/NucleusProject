<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ChargeDefinition</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
    <style>
            .required-field::after{
                content: "*";
                color: red;
            }
    </style>
</head>
<body>
<jsp:include page="/navbar.jsp" />
    <h3 class="p-2">Charge Details</h3>
    <hr/>
    <form:form class="p-2 needs-validation" method = "Post" modelAttribute = "newChargeData">
        <div class="form-row">
            <div class="form-group col-sm-3">
                <form:label path = "chargeCode" class="font-weight-bold required-field">Charge Code</form:label>
                <form:input path = "chargeCode" class="form-control" readonly = "true"/>
            </div>
            <div class="form-group col-sm-3 offset-sm-4">
                <form:label path = "chargeCodeName" class="font-weight-bold required-field">Charge Code Name</form:label>
                <form:input path = "chargeCodeName" class="form-control" readonly = "true"/>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-sm-3">
                <form:label path = "chargeDescription" class="font-weight-bold">Charge Description</form:label>
                <form:textarea path = "chargeDescription" class="form-control" rows="3" readonly = "true"/>
            </div>
            <div class="form-group col-sm-3 offset-sm-4">
                <form:label path="transactionEvent" class="font-weight-bold required-field">Transaction Event</form:label>
                <form:input path = "transactionEvent" class="form-control" readonly = "true"/>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-sm-3">
                <form:label path="chargePaymentType" class="font-weight-bold required-field">Charge Payment Type</form:label><br>
                <strong><form:radiobuttons path = "chargePaymentType" items = "${paymentTypeList}"/></strong>
            </div>
        </div>
        <hr/>
        <h5>Computation Details</h5>
        <div class="form-row">
            <div class="form-group col-sm-3">
                <form:label path="chargeType" class="font-weight-bold required-field">Charge Type</form:label>
                <form:input path = "chargeType" class="form-control" readonly = "true"/>
            </div>
            <div class="form-group col-sm-3 offset-sm-4">
                <form:label path="chargeAmount" class="font-weight-bold">Charge Amount</form:label>
                <form:input path="chargeAmount" class="form-control" min="0" readonly = "true"/>
            </div>
        </div>
        <hr/>
        <div class="form-row">
            <div class="form-group col-sm-3 offset-sm-7">
            <security:authorize access = "hasRole('MAKER')">
                <button type="button" class="btn btn-primary" onclick='location.href="<%= request.getContextPath()%>/charges/makerList"'>Back</button>
            </security:authorize>
            <security:authorize access = "hasRole('CHECKER')">
                <input type="submit" class="btn btn-primary" name ="action" value="Approve"/>
                <input type="submit" class="btn btn-primary" name ="action" value="Reject"/>
            </security:authorize>
            </div>
        </div>
    </form:form>
</body>
</html>
