<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

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
<jsp:include page="/navbar.jsp" />
 <div class="container-fluid">
        <div class="row pt-3 pl-3 flex-column">
        	<h2 class="display-3" style="font-size: 30px">
        		<b>Charge Policies</b>
        	</h2>
            <sec:authorize access="hasRole('MAKER')">
        	<div class=" px-4 mt-0 align-self-end ">
       			<a class="btn btn-primary" href="<%= request.getContextPath()%>/chargePolicy/newChargePolicy">New Charge Policy</a>
       		</div>
       		</sec:authorize>

        </div>

        <hr width="" color="#b3b3b3">

    </div>



<form:form method = "Post" modelAttribute = "chargePolicy">

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

		                <th>Status</th>
		                <th>Created By</th>
		                <th>Reviewed By</th>
		                <th>Actions</th>
		            </tr>
		        </thead>
		        <tbody>
		           <c:forEach items="${chargePolicyList}" var="chargePolicy" varStatus="tagStatus">
                     <tr>
                       <td>
                        <sec:authorize access="hasRole('CHECKER')">
                            <c:if test = "${chargePolicy.status == 'PENDING'}">
                            <a href="<%= request.getContextPath()%>/chargePolicy/get/${chargePolicy.chargePolicyCode}">
                                ${chargePolicy.chargePolicyCode}
                            </a>
                            </c:if>

                              <c:if test = "${chargePolicy.status == 'SAVED'}">
                                                                     ${chargePolicy.chargePolicyCode}
                                                          </c:if>
                            <c:if test = "${chargePolicy.status == 'REJECTED'}">
                                        ${chargePolicy.chargePolicyCode}
                             </c:if>
                            <c:if test = "${chargePolicy.status == 'APPROVED'}">
                                        ${chargePolicy.chargePolicyCode}
                             </c:if>
                          </sec:authorize>
                                		            <sec:authorize access="hasRole('MAKER')">
                                		                 ${chargePolicy.chargePolicyCode}
                                		            </sec:authorize>


                        </td>
                       <td>${chargePolicy.chargePolicyName}</td>
                       <td>${chargePolicy.chargePolicyDesc}</td>

                       <td>${chargePolicy.status}</td>
                       <td>${chargePolicy.createdBy}</td>
                       <td>${chargePolicy.authorizedBy}</td>
                        <td>
                            <sec:authorize access="hasRole('MAKER')">
                                <c:if test = "${chargePolicy.status == 'PENDING'}">
                                    <a href="<%= request.getContextPath()%>/chargePolicy/edit/${chargePolicy.chargePolicyCode}">Edit</a>  |  <a href="<%= request.getContextPath()%>/chargePolicy/delete/${chargePolicy.chargePolicyCode}">Delete</a>
                                </c:if>
                                <c:if test = "${chargePolicy.status == 'SAVED'}">
                                     <a href="<%= request.getContextPath()%>/chargePolicy/edit/${chargePolicy.chargePolicyCode}">Edit</a>  |  <a href="<%= request.getContextPath()%>/chargePolicy/delete/${chargePolicy.chargePolicyCode}">Delete</a>
                                </c:if>
                            </sec:authorize>
                            <sec:authorize access="hasRole('CHECKER')">
                                Edit  |  Delete
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                Edit  |  Delete
                            </sec:authorize>
                        </td>

                     </tr>
                   </c:forEach>

		        </tbody>

		    </table>

		</div>
</form:form>
</body>