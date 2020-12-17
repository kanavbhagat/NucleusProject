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


    <script type="text/javascript">
         function getRepaymentDesc(){
         var text = '#repaymentData #'+ $( "#repaymentPolicyCode option:selected" ).text();
            $('#repaymentDesc').val($(text).text())
         }

         function getEligibilityDesc(){
         var text = '#eligibilityData #'+ $( "#eligibilityPolicyCode option:selected" ).text();
            $('#eligibilityDesc').val($(text).text())
         }

		$(document).ready(function() {
		    console.log("elo");
            $( "#repaymentPolicyCodeString" ).change(function() {
                getRepaymentDesc();
            });

            $( "#eligibilityPolicyCodeString" ).change(function() {
                getEligibilityDesc();
             });
		});
	</script>

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
    <form:form modelAttribute="product" method="post" action="newProduct">
        <div class="row">
            <div class="col-sm-3">

                <div class="form-group">
                    <form:label path="productCode" cssClass="font-weight-bold required-field">Product Code:</form:label>
                    <form:input required="required" cssClass="form-control" path="productCode"/>
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
                    <form:input required="required" cssClass="form-control" path="productName"/>
                    <form:errors path="productCode"/>
                </div>

                <div class="form-group">
                    <form:label path="productType" cssClass="font-weight-bold required-field">Product Type:</form:label>
                    <form:select required="required" path="productType" cssClass="form-control">
                        <form:option value="-"  disabled="${'true'}" selected="true" label="Select One Option"/>
                        <form:options items="${productTypes}" />
                    </form:select>
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
                        <form:label path="repaymentPolicyCode" cssClass="required-field">Repayment Policy</form:label>
                    </td>
                    <td>
                        <form:select required="required" path="repaymentPolicyCodeString" cssClass="form-control">
                            <form:option value="-"  disabled="${'true'}" selected="true" label="Choose a Policy"/>
                            <form:options items="${repaymentPolicies}" itemLabel="policyName" itemValue="policyCode" />
                        </form:select>
                    </td>
                    <td><input class="form-control" id="repaymentDesc" type="text" disabled></td>
                </tr>
                <tr>
                    <td>
                        <form:label path="eligibilityPolicyCode" cssClass="required-field">Eligibility Policy</form:label>
                    </td>
                    <td>
                        <form:select required="required" path="eligibilityPolicyCodeString" cssClass="form-control">
                            <form:option value="-"  disabled="${'true'}" selected="true" label="Choose a Policy"/>
                            <form:options items="${eligibilityPolicies}" itemLabel="policyName" itemValue="policyCode"/>
                        </form:select>
                    </td>
                    <td><input class="form-control" id="eligibilityDesc" type="text" disabled></td>
                </tr>
                <tr>
                    <td>Charge Policy</td>
                    <td>
                        <form:select  path="chargeCodePolicy" cssClass="form-control">
                            <form:option value="-"  disabled="${'true'}" selected="true" label="Choose a Policy"/>
                            <form:options items="${chargePolicies}" />
                        </form:select>
                    </td>
                    <td><form:input cssClass="form-control" disabled="${'true'}" path="chargeCodePolicy"/></td>
                </tr>

            </table>
        </div>
        <hr width="" color="#b3b3b3">
        <div class="row" style="margin-bottom:20px">
            <div class="col-sm-3 offset-sm-9">
                <button type="submit" class="btn btn-primary" id="save">Save</button>
                <button type="button" class="btn btn-primary" id="saveAndRequest">Save & Request Approval</button>
            </div>
        </div>
    </form:form>

<table id="repaymentData" hidden>
    <thead>
    <th> policy code <th>
    <th> policy description <th>
    </thead>
    <tbody>
            <c:if test="${!empty repaymentPolicies}">
            <c:forEach var="policy" items="${repaymentPolicies}">
                 <tr>
                  <td>${policy.policyCode}</td>
                  <td id=${policy.policyName}>${policy.policyDescription}</td>
                 </tr>
                </c:forEach>
            </c:if>
    </tbody>
</table>


<table id="eligibilityData" hidden>
    <thead>
    <th> policy code <th>
    <th> policy description <th>
    </thead>
    <tbody>
            <c:if test="${!empty eligibilityPolicies}">
            <c:forEach var="policy" items="${eligibilityPolicies}">
                 <tr>
                  <td>${policy.policyCode}</td>
                  <td id=${policy.policyName}>${policy.policyDescription}</td>
                 </tr>
                </c:forEach>
            </c:if>
    </tbody>
</table>
</div>
</body>
</html>
