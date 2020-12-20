<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Loan Application</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <%--
    <script type="text/javascript">
		$(document).ready(function() {
		    $('#productTable').DataTable();
		} );
	</script>
	--%>

</head>

<body>

<div class="container-fluid">

    <jsp:include page="/navbar.jsp" />

    <div class="row pt-3 pl-3 flex-column">
    		<h2 class="  display-3" style="font-size: 30px">
    			<b> Applications
    			 </b>
    		</h2>

    		<div class=" px-4 mt-0 align-self-end ">
    			<button type="button" class="btn btn-primary">Create Application</button>
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
    		                <th>Application ID</th>
    		                <th>Customer ID</th>
    		                <th>Customer Name</th>
    		                <th>Product Type</th>
    		                <th>Phone No</th>
    		                <th>Created By</th>
    		                <th>Disbursal Status</th>
    		                <th>Reviewed By</th>
    		                <th>Actions</th>
    		            </tr>
    		        </thead>
    		        <tbody>
    		            <tr>
    		                <td style="text-align: center;"></td>
    		               <td></td>
    		               <td></td>
    		               <td></td>
    		               <td></td>
    		               <td></td>
    		               <td></td>
    		               <td></td>
    		                <td><button type="button" class="btn btn-link">Edit</button> | <button type="button" class="btn btn-link">Delete</button></td>
    		            </tr>
    		             <tr>
    		                <td style="text-align: center;"></td>
    		               <td></td>
    		               <td></td>
    		               <td></td>
    		               <td></td>
    		               <td></td>
    		               <td></td>
    		               <td></td>
    		                <td><button type="button" class="btn btn-link">Edit</button> | <button type="button" class="btn btn-link">Delete</button></td>
    		            </tr>
    		        </tbody>

    		    </table>

    		</div>
    	</div>
</div>

<br>