<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

   <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Receipt Search Screen</title>
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
    <br>
    <div class="row">
        <div class="col-sm-10 col-12">
            <h2 class="display-3" style="font-size: 30px">
                <strong>Receipt/Payment(Search)</strong>
            </h2>
        </div>
        <div class="col-sm-2 col-12">
            <button type="button" onclick='location.href="<%= request.getContextPath()%>/newReceipt"' class="btn btn-primary" id="newReceipt">Create Receipt</button>
            <%--<button type="button" class="btn btn-primary" id="newReceipt">Create Receipt</button>--%>
        </div>
    </div>

    <hr>=40070
</div>

<!-- Form Container -->
<div class="container-fluid">
    <form:form method="Post" action="get">
        <div class="row">
            <div class="col-sm-3">

                <div class="form-group">
                    <form:label cssClass="font-weight-bold required-field">Receipt Type</form:label>
                    <form:select class="form-control" name="receiptType">
                        <form:option value="Select One Option" label="select"/>
                    </form:select>
                </div>
                <div class="form-group">
                    <form:label cssClass="font-weight-bold">Receipt basis</form:label>
                    <form:select cssClass="form-control" name="receiptBasis">
                        <form:option value="Select One Option" label="select" />
                    </form:select>
                </div>
                <div class="col-sm-3 offset-sm-4">

                <div class="form-group">
                    <label for="loanAccount" class="font-weight-bold required-field">Loan Account#</label>
                    <form:input type="text" cssClass="form-control" name="loanAccount"/>
                </div>

                <div class="form-group">
                    <label for="receiptRef" class="font-weight-bold required-field">Receipt Ref#</label>
                    <form:input type="text" cssClass="form-control" name="receiptRef"/>
                </div>
            </div>
        </div>

        <hr width="" color="#b3b3b3">

        <div class="row" style="margin-bottom:20px">
            <div class="col-sm-3 offset-sm-10">
                <button type="button"  id="save" class="btn btn-primary">Search</button>
                <button type="button" id="clear" class="btn btn-primary">Clear</button>
            </div>
        </div>

    </form:form>
</div>
</body>
</html>