<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Receipt Search Result </title>
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
            $('#receiptSearchResult').DataTable();
            } );

	</script>
</head>

<body>

<div class="container-fluid">

    <jsp:include page="/navbar.jsp" />

        <!-- Show n entries -->
        <!-- Source : https://stackoverflow.com/questions/41436805/how-to-display-a-table-with-10-rows-per-page -->
        <table id="receiptSearchResult" class="table table-striped table-bordered display" style="width:100%">
            <thead>
            <tr>
                <th>Receipt Type</th>
                <th>Receipt basis</th>
                <th>Loan Account#</th>
                <th>Receipt Ref#</th>
            </tr>
            </thead>
            <tbody>
                        <c:if test="${!empty listReceipts}">
                        <c:forEach var="receipt" items="${listReceipts}">
                             <tr>
                              <td>${receipt.receiptType}</td>
                              <td>${receipt.receiptBasis}</td>
                              <td>${receipt.loanApplicationNumber}</td>
                              <td>${receipt.receiptNo}</td>
                             </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
            </table>
    </div>

</div>
</body>
</html>
