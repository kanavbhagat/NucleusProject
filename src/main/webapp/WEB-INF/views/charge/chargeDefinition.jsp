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
    <security:authorize access = "hasRole('MAKER')">
        <center><font color=red>${msg}</font></center><br>
        <h3 class="p-2">Create Charge</h3>
    </security:authorize>
    <security:authorize access = "hasRole('CHECKER')">
        <h3 class="p-2">Charge Details</h3>
    </security:authorize>
    <hr/>
    <form:form class="p-2 needs-validation" method = "Post" modelAttribute = "newChargeData">
        <div class="form-row">
            <div class="form-group col-sm-3">
                <form:label path = "chargeCode" class="font-weight-bold required-field">Charge Code</form:label>
                <security:authorize access = "hasRole('MAKER')">
                    <form:input path = "chargeCode" class="form-control"/>
                </security:authorize>
                <security:authorize access = "hasRole('CHECKER')">
                    <form:input path = "chargeCode" class="form-control" readonly = "true"/>
                </security:authorize>
                <form:errors path = "chargeCode" class = "text-danger"></form:errors>
            </div>
            <div class="form-group col-sm-3 offset-sm-4">
                <form:label path = "chargeCodeName" class="font-weight-bold required-field">Charge Code Name</form:label>
                <security:authorize access = "hasRole('MAKER')">
                    <form:input path = "chargeCodeName" class="form-control"/>
                </security:authorize>
                <security:authorize access = "hasRole('CHECKER')">
                    <form:input path = "chargeCodeName" class="form-control" readonly = "true"/>
                </security:authorize>
                <form:errors path = "chargeCodeName" class = "text-danger"></form:errors>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-sm-3">
                <form:label path = "chargeDescription" class="font-weight-bold">Charge Description</form:label>
                <security:authorize access = "hasRole('MAKER')">
                    <form:textarea path = "chargeDescription" class="form-control" rows="3"/>
                </security:authorize>
                <security:authorize access = "hasRole('CHECKER')">
                    <form:textarea path = "chargeDescription" class="form-control" rows="3" readonly = "true"/>
                </security:authorize>
            </div>
            <div class="form-group col-sm-3 offset-sm-4">
                <form:label path="transactionEvent" class="font-weight-bold required-field">Transaction Event</form:label>
                <security:authorize access = "hasRole('MAKER')">
                    <form:select class="form-control" path="transactionEvent">
                        <form:options items="${eventList}"></form:options>
                    </form:select>
                </security:authorize>
                <security:authorize access = "hasRole('CHECKER')">
                    <form:input path = "transactionEvent" class="form-control" readonly = "true"/>
                </security:authorize>
                <form:errors path = "transactionEvent" class = "text-danger"></form:errors>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-sm-3">
                <form:label path="chargePaymentType" class="font-weight-bold required-field">Charge Payment Type</form:label><br>
                 <security:authorize access = "hasRole('MAKER')">
                    <strong><form:radiobuttons path = "chargePaymentType" items = "${paymentTypeList}" /></strong>
                 </security:authorize>
                 <security:authorize access = "hasRole('CHECKER')">
                     <strong><form:radiobuttons path = "chargePaymentType" items = "${paymentTypeList}"/></strong>
                 </security:authorize>
                <form:errors path = "chargePaymentType" class = "text-danger"></form:errors>
            </div>
        </div>
        <hr/>
        <h5>Computation Details</h5>
        <div class="form-row">
            <div class="form-group col-sm-3">
                <form:label path="chargeType" class="font-weight-bold required-field">Charge Type</form:label>
                <security:authorize access = "hasRole('MAKER')">
                    <form:select class="form-control" path="chargeType">
                        <form:options items="${chargeTypeList}" />
                    </form:select>
                </security:authorize>
                <security:authorize access = "hasRole('CHECKER')">
                    <form:input path = "chargeType" class="form-control" readonly = "true"/>
                </security:authorize>
                <form:errors path = "chargeType" class = "text-danger"></form:errors>
            </div>
            <div class="form-group col-sm-3 offset-sm-4">
                <form:label path="chargeAmount" class="font-weight-bold">Charge Amount</form:label>
                <security:authorize access = "hasRole('MAKER')">
                    <form:input path="chargeAmount" class="form-control" min="0"/>
                </security:authorize>
                <security:authorize access = "hasRole('CHECKER')">
                    <form:input path="chargeAmount" class="form-control" min="0" readonly = "true"/>
                </security:authorize>
                <form:errors path = "chargeAmount" class = "text-danger"></form:errors>
            </div>
        </div>
        <hr/>
        <div class="form-row">
            <div class="form-group col-sm-3 offset-sm-7">
            <security:authorize access = "hasRole('MAKER')">
                <input type="submit" class="btn btn-primary" name ="action" value="Save"/>
                <input type="submit" class="btn btn-primary" name ="action" value="Save & Request Approval"/>
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
