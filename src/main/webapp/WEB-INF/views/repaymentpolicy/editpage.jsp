<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>New Repayment Policy</title>
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
    <script type="text/javascript">
        $(document).ready(function() {


        window.onload = function() {
            document.getElementById("minTenure").onchange();
            document.getElementById("maxTenure").onchange();
        };



            function validate_form ()
               {
                   valid = true;
                   if (document.newRepaymentPolicyAttribute.minTenure.value>document.newRepaymentPolicyAttribute.maxTenure.value)
                   {
                        alert ( "Min Tenure should be less than or equal to Maximum Tenure" );
                        valid = false;
                   }

                   return valid;
               }
           } );
    </script>
</head>

<body>


<div class="container-fluid">

    <jsp:include page="/navbar.jsp" />

    <div class="row pt-3 pl-3 flex-column">
        <h2 class="  display-3" style="font-size: 30px">
            <b> Create Repayment Policies </b>
        </h2>
    </div>

    <hr width="" color="#b3b3b3">

</div>

<!-- Form Container -->

<div class="container-fluid">
<c:url var="saveUrl" value="showRepaymentPolicy/edit" />
<form:form modelAttribute="newRepaymentPolicyAttribute" method="POST" >
	<div class="row">
                <div class="col-sm-3">

                    <div class="form-group">
                        <form:label path="policyCode" class="font-weight-bold required-field">Repayment Policy Code</form:label>
                        <form:input path="policyCode" type="text" class="form-control" id="policyCode" disabled="true" />
                    </div>

                    <div class="form-group">
                        <form:label path="policyDescription" class="font-weight-bold">Repayment Policy Description</form:label>
                        <form:textarea path="policyDescription" class="form-control" id="policyDescription" rows="3"/>
                    </div>


                    <div class="form-group">
                        <form:label path="repaymentFrequency" class="font-weight-bold required-field">Repayment Frequency</form:label>
                        <form:select path="repaymentFrequency" class="form-control" id="repaymentFrequency">
                            <option value="repaymentFrequency1">Monthly</option>
                            <option value="repaymentFrequency2">Quarterly</option>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <form:label path="maxTenure" class="font-weight-bold">Maximum Tenure(Months)</form:label>
                        <form:input path="maxTenure" onkeypress="return event.charCode >= 48" min="0" onchange="document.getElementById('defaultTenure').max=this.value;" type="number" class="form-control" id="maxTenure"/>
                    </div>


                    <div class="form-group">
                        <form:label path="interestRateType" class="font-weight-bold required-field">Interest Rate Type</form:label>
                        <form:select path="interestRateType" class="form-control" id="interestRateType">
                            <option value="interestRateType1">Fixed</option>
                            <option value="interestRateType2">Floating</option>
                        </form:select>
                    </div>

                </div>

                <div class="col-sm-3 offset-sm-4">

                        <div class="form-group">
                            <form:label path="policyName" class="font-weight-bold required-field">Repayment Policy Name</form:label>
                            <form:input path="policyName" type="text" class="form-control" id="policyName" required="true"  pattern="[A-Za-z].{3,30}" title="Only Alphanumeric characters allowed. Length should be from 3 to 30."/>
                        </div>



                        <div class="form-group">
                            <form:label path="date" class="font-weight-bold required-field">Instalment Due Date</form:label>
                            <form:input path="date" type="date" required="true" class="form-control" id="date" placeholder="dd/mm/yy"/>
                        </div>

                        <br>
                        <br>

                        <div class="form-group">
                            <form:label path="minTenure" class="font-weight-bold">Minimum Tenure(Months)</form:label>
                            <form:input path="minTenure" onkeypress="return event.charCode >= 48" min="0" onchange="document.getElementById('defaultTenure').min=this.value;" type="number" class="form-control" id="minTenure"/>
                        </div>

                        <div class="form-group">
                            <form:label path="defaultTenure" class="font-weight-bold">Default Tenure(Months)</form:label>
                            <form:input path="defaultTenure" onkeypress="return event.charCode >= 48" min="0" type="number" class="form-control" id="defaultTenure"/>
                        </div>

                        <div class="form-group">
                            <form:label path="defaultRate" class="font-weight-bold required-field">Default Rate</form:label>
                            <form:input path="defaultRate" onkeypress="return event.charCode >= 48"   type="number" class="form-control" id="defaultRate" step=".01"/>
                        </div>
                </div>
    </div>

<hr width="" color="#b3b3b3">
    <div class="row" style="margin-bottom:20px">
        <div class="col-sm-3 offset-sm-9">
            <input type="submit" class="btn btn-primary" name="update" value="Update" />
            <input type="submit" class="btn btn-primary" name="updateApprove" value="Update & Request Approval" />
        </div>
    </div>



</form:form>
</body>
</html>