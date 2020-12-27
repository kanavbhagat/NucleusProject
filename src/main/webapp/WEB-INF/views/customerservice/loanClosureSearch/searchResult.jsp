
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Loan closure Search</title>
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
       		$(document).ready(function() {
       		    $('#CustomerTable').DataTable();
       		    $('#LoanApplicationsTable').DataTable();
       		} );
       	</script>
</head>

<body>

<div class="container-fluid">

    <jsp:include page="/navbar.jsp" />
    <br>
    <div class="row">
            <div class="col-sm-10 col-12">
                <h2 class="display-3" style="font-size: 30px">
                    <strong>Customer Details</strong>
                </h2>

            </div>
        </div>
    <hr width="" color="#b3b3b3">
</div>

<div class="container-fluid">
    <div class="row px-3 flex-column">
        <!-- Show n entries -->
        <!-- Source : https://stackoverflow.com/questions/41436805/how-to-display-a-table-with-10-rows-per-page -->
        <table id="CustomerTable" class="table table-striped table-bordered display" style="width:100%">
            <thead>
            <tr>
                <th>Customer Code</th>
                <th>Customer First Name</th>
                <th>Customer Last Name</th>
                <th>DOB</th>
                <th>Nationality</th>
                <th>Occupation Type</th>
                <th>Total Work Experience</th>
                <th>Organization Name</th>

            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty customer}">
            <c:forEach var="cust" items="${customer}">
                 <tr>
                  <td>${cust.customerCode}</td>
                  <td>${cust.firstName}</td>
                  <td>${cust.lastName}</td>
                  <td>${cust.dateOfBirth}</td>
                  <td>${cust.nationality}</td>
                  <td>${cust.occupationType}</td>
                  <td>${cust.totalWorkExperience}</td>
                  <td>${cust.organizationName}</td>
                 </tr>
            </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
<br>
<br>
<div class="container-fluid">
    <br>
    <div class="row">
            <div class="col-sm-10 col-12">
                <h2 class="display-3" style="font-size: 30px">
                    <strong>Loan Application Details</strong>
                </h2>

            </div>
        </div>
    <hr width="" color="#b3b3b3">
</div>
<div class="container-fluid">
    <div class="row px-3 flex-column">
        <table id="LoanApplicationsTable" class="table table-striped table-bordered display" style="width:100%">
            <thead>
                <tr>
                    <th>Loan Application Number</th>
                    <th>Customer Code</th>
                    <th>Product Code</th>
                    <th>Loan Amount Requested</th>
                    <th>Tenure</th>
                    <th>Rate</th>
                    <th>Agreement Date</th>
                    <th>Installment Due Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${!empty loanApplications}">
                    <c:forEach var="loan" items="${loanApplications}">
                        <tr>
                            <td>${loan.loanApplicationNumber}</td>
                            <td>${loan.customerCode.customerCode}</td>
                            <td>${loan.productCode.productCode}</td>
                            <td>${loan.loanAmountRequested}</td>
                            <td>${loan.tenure}</td>
                            <td>${loan.rate}</td>
                            <td>${loan.agreementDate}</td>
                            <td>${loan.installmentDueDate}</td>
                            <td>${loan.status}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>