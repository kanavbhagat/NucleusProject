<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file = "/navbar.jsp"%>
<html>
<head>
<title>Loan Closure Details</title>
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

	<!-- Section Heading -->
	<div class="row pt-3 pl-3 flex-column">
		<h2 class="  display-3" style="font-size: 30px">
			<b> Loan Closure Details
			 </b>
		</h2>
	</div>

	<hr width="" color="#b3b3b3">

	</div>
	<!-- End Of Container -->

	<div class="container-fluid">
		<div class="row px-3 flex-column">
			<table id="example" class="table table-striped table-bordered display" style="width:100%">
		        <thead>
		            <tr>
						<th>Loan Application Number</th>
						<th>Loan Amount</th>
						<th>Disbursal Date</th>
						<th>Tenure</th>
						<th>AuthorizedBy</th>
						<th>Agreement Date</th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr>
						<td><c:out value="${loanApp.loanApplicationNumber}" /></td>
						<td><c:out value="${loanApp.loanAmountRequested}" /></td>
						<td><c:out value="${loanApp.authorizedDate}" /></td>
						<td><c:out value="${loanApp.tenure}" /></td>
						<td><c:out value="${loanApp.authorizedBy}" /></td>
						<td><c:out value="${loanApp.agreementDate}" /></td>
		            </tr>
		        </tbody>

		    </table>

		</div>
	</div>


</body>
</html>