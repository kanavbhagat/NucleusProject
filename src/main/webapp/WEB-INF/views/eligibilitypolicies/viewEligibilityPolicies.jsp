<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@ include file = "/navbar.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
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
<title>Eligibility Policies</title>
<style>
</style>
</head>
<body>
    <div class="container-fluid">
        <div class="row pt-3 pl-3 flex-column">
        	<h2 class="display-3" style="font-size: 30px">
        		<b>Eligibility Policies</b>
        	</h2>

        	<div class=" px-4 mt-0 align-self-end ">
       			<a class="btn btn-primary" href="<%= request.getContextPath()%>/eligibilityPolicy/new">New Eligibility Policy</a>
       		</div>
        </div>

        <hr width="" color="#b3b3b3">

    </div>
    <!-- End Of Container -->

    <div class="container-fluid">
        <div class="row px-3 flex-column">
        		<table id="example" class="table table-striped table-bordered display" style="width:100%">
        		    <thead>
        		        <tr>
        		            <th>Eligibility Policy Code</th>
        		            <th>Eligibility Policy Name</th>
        		            <th>Eligibility Policy Description</th>
        		            <th>Created By</th>
        		            <th>Status</th>
        		            <th>Reviewed By</th>
        		            <th>Actions</th>
        		        </tr>
        		    </thead>
        		    <tbody>
        		        <c:forEach items="${eligibilityPolicyList}" var="eligibilityPolicy" varStatus="tagStatus">
        		        <tr>
        		            <td>
        		            <a href="<%= request.getContextPath()%>/eligibilityPolicy/get/${eligibilityPolicy.policyCode}">
        		                ${eligibilityPolicy.policyCode}
        		            </a>
        		            </td>
        		            <td>${eligibilityPolicy.policyName}</td>
        		            <td>${eligibilityPolicy.policyDescription}</td>
        		            <td>${eligibilityPolicy.createdBy}</td>
        		            <td>${eligibilityPolicy.status}</td>
                            <td>${eligibilityPolicy.authorizedBy}</td>
       		                <td><a href="<%= request.getContextPath()%>/eligibilityPolicy/edit/${eligibilityPolicy.policyCode}">Edit</a>  |  <a href="<%= request.getContextPath()%>/eligibilityPolicy/delete/${eligibilityPolicy.policyCode}">Delete</a></td>
       		            </tr>
       		            </c:forEach>
       		        </tbody>
       		    </table>
        </div>
    </div>
</body>
<script>
$(document).ready(function() {
    var url = new URL(window.location.href);

    var insertStatus = url.searchParams.get("insertStatus");
    var deleteStatus = url.searchParams.get("deleteStatus");
    var updateStatus = url.searchParams.get("updateStatus");
    var editStatus = url.searchParams.get("editStatus");

    if(insertStatus === "true") {
        alert("Eligibility Policy Added Successfully!");
    } else if(insertStatus === "false") {
        alert("Action Unsuccessful!");
    }

    if(deleteStatus === "true") {
        alert("Eligibility Policy Deleted Successfully!");
    } else if(deleteStatus === "false") {
        alert("Action Unsuccessful!");
    }

    if(updateStatus === "true") {
        alert("Status Change Successful!");
    } else if(updateStatus === "false") {
        alert("Action Unsuccessful!");
    }

    if(editStatus === "true") {
        alert("Eligibility Policy Edited Successfully!");
    } else if(editStatus === "false") {
        alert("Action Unsuccessful!");
    }
});
</script>
</html>