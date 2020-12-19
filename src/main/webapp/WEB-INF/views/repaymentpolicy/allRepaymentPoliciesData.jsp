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
	<%--<!-- NavBar Starts -->
		<nav class="navbar navbar-expand-sm navbar-light bg-light" >
	        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
	          <span class="navbar-toggler-icon"></span>
	        </button>

	        <div class="collapse navbar-collapse" id="navbarTogglerDemo03 ">
	          <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

	            <li class="nav-item mx-2">
	              <a class="nav-link" href="#">Product <span class="sr-only">(current)</span></a>
	            </li>

	            <li class="nav-item dropdown dmenu mx-2">
	            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	              Policy Setup
	            </a>
	            <div class="dropdown-menu sm-menu">
	              <a class="dropdown-item" href="#">Link 1</a>
	              <a class="dropdown-item" href="#">Link 2</a>
	              <a class="dropdown-item" href="#">Link 3</a>
	            </div>
	          </li>

	          <li class="nav-item dropdown dmenu mx-2">
	            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	              Parameters
	            </a>
	            <div class="dropdown-menu sm-menu">
	              <a class="dropdown-item" href="#">Link 1</a>
	              <a class="dropdown-item" href="#">Link 2</a>
	              <a class="dropdown-item" href="#">Link 3</a>
	            </div>
	          </li>

	          <li class="nav-item mx-2">
	              <a class="nav-link" href="#">Application</a>
	            </li>

	          <li class="nav-item mx-2">
	            <a class="nav-link" href="#">Receipt</a>
	          </li>

	          <li class="nav-item dropdown dmenu mx-2">
	            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	              Accounting
	            </a>
	            <div class="dropdown-menu sm-menu">
	              <a class="dropdown-item" href="#">Link 1</a>
	              <a class="dropdown-item" href="#">Link 2</a>
	              <a class="dropdown-item" href="#">Link 3</a>
	            </div>
	          </li>

	          <li class="nav-item mx-2">
	            <a class="nav-link" href="#">Customer Service</a>
	          </li>

	          <li class="nav-item dropdown dmenu mx-2">
	            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	              Report
	            </a>
	            <div class="dropdown-menu sm-menu">
	              <a class="dropdown-item" href="#">Link 1</a>
	              <a class="dropdown-item" href="#">Link 2</a>
	              <a class="dropdown-item" href="#">Link 3</a>
	            </div>
	          </li>

	          </ul>
	          <div class="social-part">
	            <i class="fa fa-facebook" aria-hidden="true"></i>
	            <i class="fa fa-twitter" aria-hidden="true"></i>
	            <i class="fa fa-instagram" aria-hidden="true"></i>
	          </div>
	        </div>
	     </nav>

	<!-- NavBar Ends --> --%>

	<jsp:include page="/navbar.jsp" />

	<!-- Section Heading -->
	<div class="row pt-3 pl-3 flex-column">
		<h2 class="  display-3" style="font-size: 30px">
			<b> Repayment Policies
			 </b>
		</h2>
		<div class=" px-4 mt-0 align-self-end ">
			<button type="button" onclick="location.href = '<%= request.getContextPath()%>/showRepaymentPolicy/add';" class="btn btn-primary">New Repayment Policy</button>
		</div>
	</div>

	<hr width="" color="#b3b3b3">

	</div>
	<!-- End Of Container -->
    <c:url var="addUrl" value="/showRepaymentPolicy/add" />
	<div class="container-fluid">
		<div class="row px-3 flex-column">
			<!-- Show n entries -->
			<!-- Source : https://stackoverflow.com/questions/41436805/how-to-display-a-table-with-10-rows-per-page -->
			<table id="example" class="table table-striped table-bordered display" style="width:100%">
                <thead>
                    <tr>
                        <th>repaymentPolicyCode</th>
                        <th>repaymentPolicyName</th>
                        <th>repaymentPolicyDescription</th>
                        <th>Created By</th>
                        <th>Reviewed By</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>

                <c:forEach items="${newRepaymentPolicies}" var="newRepaymentPolicy">
                    <c:url var="editUrl" value="/showRepaymentPolicy/edit?policyCode=${newRepaymentPolicy.policyCode}" />
                    <c:url var="deleteUrl" value="/showRepaymentPolicy/delete?policyCode=${newRepaymentPolicy.policyCode}" />
                    <c:url var="checkUrl" value="/showRepaymentPolicy/check?policyCode=${newRepaymentPolicy.policyCode}" />
                    <tr>
                        <td style="text-align: center;"><a href="${checkUrl}"><c:out value="${newRepaymentPolicy.policyCode}" /></a></td>
                        <td><c:out value="${newRepaymentPolicy.policyName}" /></td>
                        <td><c:out value="${newRepaymentPolicy.policyDescription}" /></td>
                        <td><c:out value="${newRepaymentPolicy.createdBy}" /></td>
                        <td><c:out value="${newRepaymentPolicy.authorizedBy}" /></td>
                        <td><a href="${editUrl}">Edit</a> | <a href="${deleteUrl}">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
		</table>
    <c:if test="${empty showRepaymentPolicy}">
        There are currently no Policies in the list. <a href="${addUrl}">Add New</a> a Policy.
    </c:if>
	</body>
</html>