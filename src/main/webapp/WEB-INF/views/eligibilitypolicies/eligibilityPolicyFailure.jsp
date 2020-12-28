<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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

<title>Error</title>
<style>
</style>
</head>
<body>
<jsp:include page="/navbar.jsp" />
	<hr style="width: 98%">

	<div class="container pt-4 ">
		<div class="jumbotron text-center" style="background-color:rgba(255,82,82 ,0.4);">

              <%-- Insert Main Message Below --%>
              <h1 class="display-4"> Error Occurred</h1>

              <%-- Insert Message Desc Here --%>
              <p class="lead"><strong>Eligibility Policy ${operation}</strong></p>

              <p class="lead">${errorMsg}</p>

              <hr>

              <div class="d-flex justify-content-center">

                <p class="lead mt-4 " style="border:1px; border-style:solid; border-color:rgba(255,138,128 ,1); padding: 5px;background-color:rgba(255,138,128 ,0.1);width: 50%;">
                <strong>Eligibility Policy Code : </strong> ${policyCode}</p>

              </div>
              <div class="d-flex justify-content-center">
                        <a href="<%= request.getContextPath()%>/eligibilityPolicy/">
                        <button class="btn btn-info">Go To Eligibility Policy List</button>
                    </a>
              </div>
		</div>
	</div>
</body>
</html>