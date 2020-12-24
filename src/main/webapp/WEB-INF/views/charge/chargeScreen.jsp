<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" import="java.util.*" %>
<%@ page import = "java.util.ResourceBundle" %>
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

<% ResourceBundle resource = ResourceBundle.getBundle("status");
  String approve = resource.getString("status.approved");
  String reject = resource.getString("status.rejected"); %>
<div class="container-fluid">
    <br>
    <div class="row">
        <div class="col-sm-10 col-12">
            <h2 class="display-3" style="font-size: 30px">
                <strong>Charge Definition</strong>
            </h2>
        </div>
        <div class="col-sm-2 col-12">
            <security:authorize access = "hasRole('MAKER')">
                <button type="button" onclick='location.href="<%= request.getContextPath()%>/charges/newChargeCreation"' class="btn btn-primary" id="newCharge">New Charge Definition</button>
            </security:authorize>
            <security:authorize access = "hasRole('CHECKER')">
                <button type="button" class="btn btn-primary" id="newCharge" disabled = "disabled">New Charge Definition</button>
            </security:authorize>
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
                  <security:authorize access="hasRole('MAKER')">
                    <td>${charge.chargeCode}</td>
                  </security:authorize>
                  <security:authorize access="hasRole('CHECKER')">
                    <td><a href = "<%= request.getContextPath()%>/charges/${charge.chargeCode}">${charge.chargeCode}</a></td>
                  </security:authorize>
                  <td>${charge.chargeCodeName}</td>
                  <td>${charge.chargeDescription}</td>
                  <td>${charge.transactionEvent}</td>
                  <td>${charge.createdBy}</td>
                  <td>${charge.status}</td>
                  <td>${charge.authorizedBy}</td>
                  <security:authorize access = "hasRole('MAKER')">
                    <td>
                        <c:set var = "chargeStatus" value ="${charge.status}"/>
                        <c:set var = "approved" value = "<%=approve%>"/>
                        <c:set var = "rejected" value = "<%=reject%>" />
                        <c:choose>

                            <c:when test = "${(chargeStatus ==  approved) || (chargeStatus == rejected)}">
                                <a href = "<%= request.getContextPath()%>/charges/displayApplication/${charge.chargeCode}">Display</a>
                            </c:when>
                            <c:otherwise>
                                <a href="<%= request.getContextPath()%>/charges/edit/${charge.chargeCode}">Edit</a>
                                |
                                <a href="<%= request.getContextPath()%>/charges/delete/${charge.chargeCode}">Delete</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                  </security:authorize>
                  <security:authorize access = "hasRole('CHECKER')">
                    <td>
                        <a href="#" disabled = "disabled" style="pointer-events:none; cursor:default;color:lightgrey;">Edit</a>
                        |
                        <a href="#" disabled = "disabled" style="pointer-events:none; cursor:default;color:lightgrey;">Delete</a>
                    </td>
                  </security:authorize>
                 </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
