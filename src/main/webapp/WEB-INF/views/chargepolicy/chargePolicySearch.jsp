<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
  <link rel="stylesheet" href="form.css">
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
  <title> Charge Policy Search Screen</title>
</head>
<body>
<form:form method = "Post" modelAttribute = "chargePolicy">
<div class="row pt-3 pl-3 flex-column">
		<h2 class="  display-3" style="font-size: 30px">
			<b> Products
			 </b>
		</h2>

		<div class=" px-4 mt-0 align-self-end ">
			<button type="button" class="btn btn-primary">New Product</button>
		</div>
	</div>

	<hr width="" color="#b3b3b3">

	</div>
<div class="container-fluid">
		<div class="row px-3 flex-column">
			<!-- Show n entries -->
			<!-- Source : https://stackoverflow.com/questions/41436805/how-to-display-a-table-with-10-rows-per-page -->
			<table id="example" class="table table-striped table-bordered display" style="width:100%">
		        <thead>
		            <tr>
		                <th>Charge Policy Code</th>
		                <th>Charge Policy Name</th>
		                <th>Charge Policy Description</th>
		                <th>Product Type</th>
		                <th>Status</th>
		                <th>Created By</th>
		                <th>Reviewed By</th>
		                <th>Actions</th>
		            </tr>
		        </thead>
		        <tbody>
		           <c:forEach items="${chargePolicyList}" var="chargePolicy" varStatus="tagStatus">
                     <tr>
                       <td>${chargePolicy.chargePolicyCode}</td>
                       <td>${chargePolicy.chargePolicyName}</td>
                       <td>${chargePolicy.chargePolicyDesc}</td>
                     </tr>
                   </c:forEach>

		        </tbody>

		    </table>

		</div>
</form:form>
</body>