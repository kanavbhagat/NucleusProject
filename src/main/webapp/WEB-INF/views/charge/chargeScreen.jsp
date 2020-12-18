<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Charge Definition Screen</title>
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
		    $('#chargeTable').DataTable();
		} );
	</script>
</head>

<body>
<jsp:include page="/navbar.jsp" />
<div class="container-fluid">
    <br>
    <div class="row">
        <div class="col-sm-10 col-12">
            <h2 class="display-3" style="font-size: 30px">
                <strong>Charge Definition</strong>
            </h2>
        </div>
        <div class="col-sm-2 col-12">
            <button type="button" onclick='location.href="<%= request.getContextPath()%>/newChargeCreation"' class="btn btn-primary" id="newCharge">New Charge Definition</button>
        </div>
    </div>
</div>

<br>
<div class="container-fluid">
    <div class="row px-3 flex-column">
        <!-- Show n entries -->
        <!-- Source : https://stackoverflow.com/questions/41436805/how-to-display-a-table-with-10-rows-per-page -->
        <table id="chargeTable" class="table table-striped table-bordered display" style="width:100%">
            <thead>
            <tr>
                <th>Charge Code</th>
                <th>Charge Name</th>
                <th>Charge Description</th>
                <th>Transaction Event</th>
                <th>Created By</th>
                <th>Status</th>
                <th>Reviewed By</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="charge" items="${chargeList}">
                 <tr>
                  <td>${charge.chargeCode}</td>
                  <td>${charge.chargeCodeName}</td>
                  <td>${charge.chargeDescription}</td>
                  <td>${charge.transactionEvent}</td>
                  <td>${charge.createdBy}</td>
                  <td>${charge.status}</td>
                  <td>${charge.authorizedBy}</td>
                  <td><a href="#">Edit</a> | <a href="#">Delete</a></td>
                 </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
