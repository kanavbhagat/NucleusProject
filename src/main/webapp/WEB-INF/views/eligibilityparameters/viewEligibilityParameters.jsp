<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file = "/navbar.jsp"%>
<html>
<head>
<title>Eligibility Parameter Maker</title>
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
			<b> Eligibility Parameters
			 </b>
		</h2>
<div class=" px-4 mt-0 align-self-end ">
<sec:authorize access="hasRole('MAKER')">
			<a class="btn btn-primary" href="<%= request.getContextPath()%>/main/createparameter">New Eligibility Parameter</a>
			</sec:authorize>
		</div>

	</div>

	<hr width="" color="#b3b3b3">

	</div>
	<!-- End Of Container -->

	<div class="container-fluid">
    		<div class="row px-3 flex-column">
    			<!-- Show n entries -->
    			<!-- Source : https://stackoverflow.com/questions/41436805/how-to-display-a-table-with-10-rows-per-page -->
    			<table id="example" class="table table-striped table-bordered display" style="width:100%">
    		        <thead>
    		            <tr>
    		                <th>Eligibility Parameter Code</th>
    		                <th>Eligibility Parameter Name</th>
    		                <th>Eligibility Parameter Description</th>
    		                <th>Created By</th>
    		                <th>Status</th>
    		                <th>Reviewed By</th>
    		                <th>Actions</th>
    		            </tr>
    		        </thead>
    		        <tbody>
    		        <c:forEach items="${parameters}" var="parameter">
    		            <tr>
    		            <td>
                         <security:authorize access="hasRole('CHECKER')">
                         <c:if test = "${parameter.status ne 'Inactive'}">
                         <a href="<%= request.getContextPath()%>/main/get/${parameter.parameterCode}">
                            ${parameter.parameterCode}
                         </a>
                         </c:if>
                         <c:if test = "${parameter.status == 'Inactive'}">
                         ${parameter.parameterCode}
                         </c:if>
                         </security:authorize>
                         <security:authorize access="hasRole('MAKER')">
                         ${parameter.parameterCode}
                         </security:authorize>
                         <security:authorize access="isAnonymous()">
                         ${parameter.parameterCode}
                         </security:authorize>
                         </td>
    		                <td><c:out value="${parameter.parameterName}" /></td>
    		                <td><c:out value="${parameter.parameterDescription}" /></td>
    		                <td><c:out value="${parameter.createdBy}" /></td>
    		                <td><c:out value="${parameter.status}" /></td>
    		                <td><c:out value="${parameter.authorizedBy}" /></td>
    		                <sec:authorize access="hasRole('MAKER')">
    		                <td><a href="<%= request.getContextPath()%>/main/edit/${parameter.parameterCode}">Edit</a>  |  <a href="<%= request.getContextPath()%>/main/delete/${parameter.parameterCode}">Delete</a></td>
    		                </sec:authorize>
    		                <sec:authorize access="hasRole('CHECKER')">
                            <td><a>Edit</a>  |  <a>Delete</a></td>
                            </sec:authorize>
    		            </tr>

                    </c:forEach>
    		        </tbody>

    		    </table>

    		</div>
    	</div>

</body>
</html>