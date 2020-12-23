<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Product Overview</title>
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
		    $('#receiptTable').DataTable();
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
                <strong>Receipt Overview</strong>
            </h2>
        </div>
    </div>
</div>

<br>
<div class="container-fluid">
    <div class="row px-3 flex-column">
        <!-- Show n entries -->
        <!-- Source : https://stackoverflow.com/questions/41436805/how-to-display-a-table-with-10-rows-per-page -->
        <table id="receiptTable" class="table table-striped table-bordered display" style="width:100%">
            <thead>
            <tr>
                <th>Receipt Ref#</th>
                <th>Loan Account#</th>
                <th>Receipt Type</th>
                <th>Receipt Basis</th>
                <th>Receipt Amount</th>
                <th>Receipt Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty receipts}">
            <c:forEach var="receipt" items="${receipts}">
                 <tr>
                      <td>${receipt.receiptNo}</td>
                      <td>${receipt.loanApplicationNumber.loanApplicationNumber}</td>
                      <td>${receipt.receiptType}</td>
                      <td>${receipt.receiptBasis}</td>
                      <td>${receipt.receiptAmount}</td>
                      <td>${receipt.receiptStatus}</td>
                      <sec:authorize access="hasRole('CHECKER')">
                        <c:if test = "${receipt.receiptStatus == 'Approved' || receipt.receiptStatus == 'Settled'}">
                            <td style="color:grey;">Approve | Reject</td>
                        </c:if>
                        <c:if test = "${receipt.receiptStatus != 'Approved' && receipt.receiptStatus != 'Settled'}">
                            <td>
                                <a href="<%= request.getContextPath()%>/receiptChecker/approve/${receipt.receiptNo}">Approve</a> |
                                <a href="<%= request.getContextPath()%>/receiptChecker/reject/${receipt.receiptNo}">Reject</a>
                            </td>
                        </c:if>

                      </sec:authorize>
                      <sec:authorize access="hasRole('MAKER')">
                        <td style="color:grey;">Approve | Reject</td>
                      </sec:authorize>
                 </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>
