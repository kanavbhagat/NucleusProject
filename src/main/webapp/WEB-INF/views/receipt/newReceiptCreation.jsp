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
    <form:form method="Post" modelAttribute="receipt" action="registerReceipt">
        <div class="row">
            <div class="col-sm-3">

                <div class="form-group">
                    <label for="receiptNo" class="font-weight-bold required-field">Receipt No</label>
                    <form:input type="number" class="form-control" path="receiptNo"/>
                    <font color="red"><form:errors path="receiptNo"/></font>
                </div>

                <div class="form-group">
                    <label for="paymentMode" class="font-weight-bold required-field">Payment Mode</label>
                    <form:select class="form-control" path="paymentMode">
                        <form:option value="" disabled="${'true'}" selected="true" label="Select One Option"/>
                        <form:option value="Cash" label="Cash" />
                        <form:option value="Cheque" label="Cheque" />
                    </form:select>
                    <font color="red"><form:errors path="paymentMode"/></font>
                </div>

                <div class="form-group">
                    <label path="loanApplicationValue"  class="font-weight-bold required-field">Loan Account #</label>
                    <form:input type="number" class="form-control" path="loanApplicationValue"/>
                    <font color="red"><form:errors path="loanApplicationValue"/></font>
                </div>

                <div class="form-group">
                    <label path="receiptAmount" class="font-weight-bold required-field">Receipt Amount</label>
                    <form:input type="number"  class="form-control" path="receiptAmount"/>
                    <font color="red"><form:errors path="receiptAmount"/></font>
                </div>

                <div class="form-group">
                    <label path="remark" class="font-weight-bold ">Remark</label>
                    <form:textarea class="form-control"  rows="3" path="remarks"></form:textarea>
                </div>
            </div>

            <div class="col-sm-3 offset-sm-4">

                <div class="form-group">
                    <label for="receiptType" class="font-weight-bold required-field">Receipt Type</label>
                    <form:select class="form-control" path="receiptType">
                        <form:option value="" disabled="${'true'}" selected="true" label="Select One Option"/>
                        <form:option value="Payment" label="Payment" />
                        <form:option value="Receipt" label="Receipt" />
                    </form:select>
                    <font color="red"><form:errors path="receiptType"/></font>
                </div>

                <div class="form-group">
                    <label for="receiptBasis" class="font-weight-bold required-field">Receipt Basis</label>
                    <form:select class="form-control" path="receiptBasis">
                        <form:option value="" disabled="${'true'}" selected="true" label="Select One Option"/>
                       <form:option value="Against Single Loan" label="Against Single Loan"/>
                    </form:select>
                    <font color="red"><form:errors path="receiptBasis"/></font>
                </div>

                <div class="form-group">
                    <label for="dateOfReceipt" class="font-weight-bold required-field">Date of Receipt</label>
                    <form:input type="date" class="form-control" path="dateOfReceipt" placeholder="dd/mm/yyyy"/>
                    <font color="red"><form:errors path="dateOfReceipt"/></font>
                </div>

                <div class="form-group">
                    <label for="receiptPurpose" class="font-weight-bold required-field">Receipt Purpose</label>
                    <form:select class="form-control" path="receiptPurpose">
                       <form:option value="" disabled="${'true'}" selected="true" label="Select One Option"/>
                       <form:option value="Any Due" label="Any Due"/>
                       <form:option value="Charges" label="Charges"/>
                       <form:option value="installment" label="Installment"/>
                       <form:option value="disbursal" label="Disbursal"/>
                    </form:select>
                    <font color="red"><form:errors path="receiptPurpose"/></font>

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
                <div class="form-check form-check-inline">
                     <form:radiobutton path="autoAllocation"  value="yes"/>Yes
                </div>
                <div class="form-check form-check-inline">
                    <form:radiobutton path="autoAllocation" value="no"/>No
                </div>
                <font color="red"><form:errors path="autoAllocation"/></font>
            </div>
        </div>

        <hr width="" color="#b3b3b3">
            <div class="row" style="margin-bottom:20px">
                <div class="col-sm-3 offset-sm-9">
                    <button type="save" class="btn btn-primary" id="save">Save</button>
                    <button type="reset" class="btn btn-primary" id="clear">Clear</button>
                </div>
            </div>

    </form:form>

</div>
</body>
</html>
