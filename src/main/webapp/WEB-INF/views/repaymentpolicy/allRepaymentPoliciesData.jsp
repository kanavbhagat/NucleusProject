<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="security"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
			<b> Repayment Policies
			 </b>
		</h2>
		<div class=" px-4 mt-0 align-self-end ">
		    <sec:authorize access="hasRole('MAKER')">
                    <button type="button" onclick="location.href = '<%= request.getContextPath()%>/showRepaymentPolicy/add';" class="btn btn-primary">New Repayment Policy</button>
            </sec:authorize>
            <sec:authorize access="hasRole('CHECKER')">
                    <button type="button" disabled="disabled" onclick="location.href = '<%= request.getContextPath()%>/showRepaymentPolicy/add';" class="btn btn-primary">New Repayment Policy</button>
            </sec:authorize>

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
                        <th>Status</th>
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
                        <sec:authorize access="hasRole('MAKER')">
                                <td style="text-align: center;"><c:out value="${newRepaymentPolicy.policyCode}" /></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('CHECKER')">
                                <td style="text-align: center;"><a href="${checkUrl}"><c:out value="${newRepaymentPolicy.policyCode}" /></a></td>
                        </sec:authorize>

                        <td><c:out value="${newRepaymentPolicy.policyName}" /></td>
                        <td><c:out value="${newRepaymentPolicy.policyDescription}" /></td>
                        <td><c:out value="${newRepaymentPolicy.status}" /></td>
                        <td><c:out value="${newRepaymentPolicy.createdBy}" /></td>
                        <td><c:out value="${newRepaymentPolicy.authorizedBy}" /></td>
                        <sec:authorize access="hasRole('MAKER')">
                                <td><a href="${editUrl}">Edit</a> | <a href="${deleteUrl}">Delete</a></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('CHECKER')">
                                <td>
                                 <a href="${editUrl}" disabled="disabled" style="pointer-events:none; cursor:default;color:lightgrey;">Edit</a>
                                 |
                                 <a href="${deleteUrl}" disabled="disabled" style="pointer-events:none; cursor:default;color:lightgrey;">Delete</a>
                                 </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </tbody>
		</table>
	</body>
</html>