<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Repayment Policy Maker</title>
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
		    $('#example').DataTable();
		} );
	</script>
</head>
<body>

	<div class="container-fluid">

	<jsp:include page="/navbar.jsp" />

	<!-- Section Heading -->
	<div class="row pt-3 pl-3 flex-column">
		<h2 class="  display-3" style="font-size: 30px">
			<b> Repayment Schedule Report
			 </b>
		</h2>
		<h3> Loan Application Id : ${rslist.get(0).getLoanApplicationNumber()} </h3>
	</div>

	<hr width="" color="#6c757d">

	</div>
	<!-- End Of Container -->




	<div class="container-fluid">
		<div class="row px-3 flex-column">
			<!-- Show n entries -->
			<!-- Source : https://stackoverflow.com/questions/41436805/how-to-display-a-table-with-10-rows-per-page -->
			<table id="example" class="table table-striped table-bordered display" style="width:100%">
		        <thead>
		            <tr>
		                <!--<th>Loan Application Number</th>-->
                        <th>Installment Number</th>
                        <th>Opening Balance</th>
                        <th>Interest Component</th>
                        <th>Principal Component</th>
                        <th>Installment Amount</th>
                        <th>ClosingBalance</th>
                        <th>DueDate</th>
                        <th>BillFlag</th>

		            </tr>
		        </thead>
		        <tbody>
<c:forEach items="${rslist}" var="RepaymentSchedule">
<tr>

        	<!--<td><c:out value="${RepaymentSchedule.loanApplicationNumber}" /></td>-->
        	<td><c:out value="${RepaymentSchedule.installmentNumber}" /></td>
            <td><c:out value="${RepaymentSchedule.openingBalance}" /></td>
        	<td><c:out value="${RepaymentSchedule.interestComponent}" /></td>
        	<td><c:out value="${RepaymentSchedule.principalComponent}" /></td>
            <td><c:out value="${RepaymentSchedule.emi}" /></td>
        	<td><c:out value="${RepaymentSchedule.closingBalance}" /></td>
        	<td><c:out value="${RepaymentSchedule.dueDate}" /></td>
        	<td><c:out value="${RepaymentSchedule.billFlag}" /></td>

</tr>
	</c:forEach>

</tbody>

		    </table>


		</div>
	</div>
	</body>

</html>