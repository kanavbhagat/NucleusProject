<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 16-12-2020
  Time: 03:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../../resources/css/appstyles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <title>New Payment</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/navbar.jsp" />
    <div class="row m-3">
        <h3>Payment Cash (Create)</h3>
        <div class="offset-9">
            <button type="button" class="btn btn-primary" id="save">Save</button>
            <button type="button" class="btn btn-warning" id="clear">Clear</button>
            <button type="button" class="btn btn-danger" id="cancel">Cancel</button>
        </div>
    </div>
    <div class="card mb-3">
        <div class="card body p-3">
           <form:form method="post" action="newPayment" modelAttribute="payment">
            <div class="row">
                <div class="form-group col-sm-3">
                    <label class="font-weight-bold required">Loan Application Number</label>
                    <form:input cssClass="form-control required" path="loanApplicationNumber"></form:input>
                </div>
                <div class="form-group col-sm-3 offset-4">
                    <label class="font-weight-bold required">Payment Code</label>
                    <form:input path="paymentCode" cssClass="form-control required"></form:input>
                </div>
            </div>
               <div class="row">
                <div class="form-group col-sm-3">
                    <label class="font-weight-bold required">Payment Amount</label>
                    <form:input cssClass="form-control required" path="paymentAmount"></form:input>
                </div>
                   <div class="form-group col-sm-3 offset-4">

                   </div>
               </div>
           </form:form>
        </div>
    </div>
</div>
</body>
</html>
