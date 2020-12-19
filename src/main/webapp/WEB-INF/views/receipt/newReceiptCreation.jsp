<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

   <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>New Receipt/Payment</title>
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
    <!-- NavBar Starts -->
    <jsp:include page="/navbar.jsp" />
    <!-- NavBar Ends -->

    <div class="row pt-3 pl-3 flex-column">
        <h2 class="  display-3" style="font-size: 30px">
            <b> Receipt/Payment(Create) </b>
        </h2>
    </div>

    <hr width="" color="#b3b3b3">

</div>

<!-- Form Container -->
<div class="container-fluid">
    <form:form method="Post" modelAttribute="receipt" action="add">
        <div class="row">
            <div class="col-sm-3">

                <div class="form-group">
                    <label for="receiptNumber" class="font-weight-bold required-field">Receipt No</label>
                    <form:input type="text" class="form-control" path="receiptNo"/>
                </div>

                <div class="form-group">
                    <label for="paymentMode" class="font-weight-bold required-field">Payment Mode</label>
                    <form:select class="form-control" path="paymentMode">
                        <form:option value="Cash" label="Cash" />
                        <form:option value="Checque" label="Checque" />
                    </form:select>
                </div>

                <div class="form-group">
                    <label for="loanAccount" class="font-weight-bold required-field">Loan Account #</label>
                    <form:input type="text" class="form-control" path="loanApplicationNumber"/>
                </div>

                <div class="form-group">
                    <label for="receiptAmount" class="font-weight-bold required-field">Receipt Amount</label>
                    <form:input type="number" class="form-control" path="receiptAmount"/>
                </div>

                <div class="form-group">
                    <label for="remark" class="font-weight-bold ">Remark</label>
                    <form:textarea class="form-control"  rows="3" path="remarks"></form:textarea>
                </div>
            </div>

            <div class="col-sm-3 offset-sm-4">

                <div class="form-group">
                    <label for="receiptType" class="font-weight-bold required-field">Receipt Type</label>
                    <form:select class="form-control" path="receiptType">

                        <form:option value="Payment" label="Payment" />
                        <form:option value="Receipt" label="Receipt" />
                    </form:select>
                </div>

                <div class="form-group">
                    <label for="receiptBasis" class="font-weight-bold required-field">Receipt Basis</label>
                    <form:select class="form-control" path="receiptBasis">
                       <form:option value="ASL" label="Against Single Loan"/>
                       <form:option value="AML" label="Against Multiple Loan"/>
                    </form:select>
                </div>

                <div class="form-group">
                    <label for="dateOfReceipt" class="font-weight-bold required-field">Date of Receipt</label>
                    <input type="date" class="form-control" path="dateOfReceipt" placeholder="dd/mm/yy">
                </div>

                <div class="form-group">
                    <label for="receiptPurpose" class="font-weight-bold required-field">Receipt Purpose</label>
                    <form:select class="form-control" path="receiptPurpose">
                       <%-- <form:option value="" disabled>Select One Option</form:option>--%>
                       <form:option value="AnyDue" label="AnyDue"/>
                       <form:option value="Charges" label="Charges"/>
                    </form:select>

                </div>

                <label class="font-weight-bold required-field">Auto Allocation</label>
                <br>

                <%--<div class="form-check form-check-inline">
                    <form:input type="radio" class="form-check-input" path="autoAllocation" />
                    <label class="form-check-label" >Yes</label>
                </div>

                <div class="form-check form-check-inline">
                    <form:input type="radio" class="form-check-input" path="autoAllocation" />
                    <label class="form-check-label" >No</label>
                </div>--%>

                <form:radiobutton path="autoAllocation" value="yes"/>Yes
                <form:radiobutton path="autoAllocation" value="no"/>No

            </div>
        </div>

        <hr width="" color="#b3b3b3">
            <div class="row" style="margin-bottom:20px">
                <div class="col-sm-3 offset-sm-9">
                    <button type="save" class="btn btn-primary" id="save">Save</button>
                    <button type="button" class="btn btn-primary" id="clear">Clear</button>
                </div>
            </div>

    </form:form>

</div>
</body>
</html>
