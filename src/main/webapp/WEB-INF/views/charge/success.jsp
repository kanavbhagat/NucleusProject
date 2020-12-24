<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.Date" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<title>SuccessPage</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

    <jsp:include page="/navbar.jsp" />

	<hr style="width: 98%">

	<div class="container pt-4 ">
		<div class="jumbotron text-center" style="background-color:rgba(141, 181, 150, 0.3);">

              <%-- Insert Main Message Below --%>
              <h1 class="display-4">${head}</h1>

              <%-- Insert Message Desc Here --%>
              <p class="lead"><strong>${msg}</strong></p>

              <hr>

              <div class="d-flex justify-content-center">

                <%-- Insert Dynamic Message Here Eg- policy code etc --%>
                <p class="lead mt-4 " style="border:1px; border-style:solid; border-color:rgba(141, 181, 150, 0.8); padding: 5px;background-color:rgba(141, 181, 150, 0.1);width: 50%;">
                <strong>Charge Code : </strong> ${chargeCode}</p>

              </div>
              <%-- Put your link in the value field below where you want to redirect to. --%>
              <div class="d-flex justify-content-center">
                <security:authorize access = "hasRole('MAKER')">
                    <a href="<%= request.getContextPath()%>/charges/makerList">
                </security:authorize>
                <security:authorize access = "hasRole('CHECKER')">
                    <a href = "<%= request.getContextPath()%>/charges/checkerList">
                </security:authorize>
                    <button class="btn btn-info">Go To Charge List</button>
                    </a>
              </div>
		</div>
	</div>
</body>
</html>