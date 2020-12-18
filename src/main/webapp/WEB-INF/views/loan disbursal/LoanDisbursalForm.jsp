<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/navbar.jsp"%>

<html>
<head>
<title>Loan Disbursal Form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<style>
		.table {
			width:80%;
		}
		.required:after {
			content:" *";
			color: red;
		}
	</style>
</head>
<body>

	<div class="container-fluid">
		<!-- Section Heading -->
		<div class="row pt-3 pl-3 flex-column">
			<h2 class="  display-3" style="font-size: 30px">
				<b> Loan Disbursal Form </b>
			</h2>
		</div>
		<hr width="" color="#b3b3b3">
	</div>

	<!-- Form Container -->
	<div class="container-fluid">
			<div class="row">
				<div class="col-sm-3">
					<form action="/customerloandisbursal" method="get" >
					<div class="form-group">
						<label class="font-weight-bold ">Customer Code#</label>
						<input type="text" class="form-control" name="customerCode" placeholder="Enter customer_Id here..." requored="required" /><input type="submit" value="Submit">
					</div>
					</form>
				</div>
				
				<div class="col-sm-3 offset-sm-4">
					<form action="/loandisbursalDetails" method="get">
					<div class="form-group">
						<label class="font-weight-bold ">Loan Application#</label>
						<input type="text" class="form-control" name="loanApplicationNumber" required placeholder="Enter LoanApp_Id here..."/><input type="submit" value="Submit">

					</div>
					</form>
				</div>
			</div>
			<hr width="" color="#b3b3b3">
	</div>

</body>
</html>