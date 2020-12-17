<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>Create Eligibility Policy</title>
<style>
    .table {
            width:80%;
        }
    .required:after {
        content:" *";
        color: red;
    }
</style>
</head>
<body>
    <div class="container-fluid">
	<div class="row pt-3 pl-3">
		<h2 class="display-3" style="font-size: 30px">
			<b>Create Eligibility Policies</b>
		</h2>
	</div>
	<hr width="" color="#b3b3b3">
    </div>

    <!-- Placeholders  -->
    	<div class="container-fluid">
    		<form:form method = "post" action="add" modelAttribute = "eligibilityPolicy">
    			<!-- Section 1 -->
    		    <div class="form-group row pt-2 pl-3">
    			      <div class="col-md-3">
    			        <label class="required" for="policyName" >Eligibility Policy Code</label><br>
    					<form:input path="policyCode" type="text" class="form-control" id="policyCode" name="policyCode" required="required"/>
    			      </div>

    			      <div class="col-md-3">
    			      </div>

    			      <div class="col-md-3">
    			        <label class="required" for="policyName">Eligibility Policy Name</label><br>
    					<form:input path="policyName" type="text" id="policyName" name="policyName" class="form-control" required="required"/>
    			      </div>
    		    </div>

    		    <div class="row pt-2 pl-3">
    				<div class="col-md-3">
    					<label for="policyDescription">Eligibility Policy Description</label><br>
    					<form:textarea path="policyDescription" class="form-control"  id="policyDescription" name="policyDescription"/>
    				</div>
    			</div>
    			<br><br>

    			<!-- Section 2 -->
                <hr width="" color="#b3b3b3">
                <div class="row pt-3 px-3 d-flex justify-content-end">
                    <button type="button" id="addButton" class="btn btn-primary">Add Eligibility Parameter</button>
   				</div>
    			<div class="row pt-3 pl-3">
    				<p class="display-2" style="font-size: 22px;">
    					<b> Eligibility Parameters </b>
    				</p>
    			</div>

    			<div class="row pt-3 px-3">
    				<table class="table table-bordered">
    				    <thead>
    				      <tr class="d-flex">
    				        <th class="col-7" style="text-align:left;">Eligibility Parameter Name</th>
    				        <th class="col-5" style="text-align:left;">Description</th>
    				      </tr>
    				    </thead>
    				    <tbody>
    				    <c:forEach items="${eligibilityPolicy.eligibilityParameterList}" var="eligibilityParameter">
    				      <tr class="d-flex">
    				        <td class="col-7" style="text-align:center;">
    				            <form:select path="eligibilityParameter" class="custom-select">
    				            <c:forEach items="${allEligibilityParameterList}" var="eligibilityParameterMaster">
                                    <form:option value="${eligibilityParameterMaster.parameterName}"/>
                                </c:forEach>
                                </form:select>
                            </td>
    				        <td class="col-5" style="text-align:center;">
    				            <textarea class="form-control" disabled>${allEligibilityParameterList[0].parameterDescription}</textarea> </td>
    				      </tr>
    				    </c:forEach>
    				    </tbody>
    				  </table>

    			</div>

                <hr width="" color="#b3b3b3">
    			<div class="row pt-3 px-3 d-flex justify-content-end">
                	<div class="px-2">
          				<input type="submit" class="btn btn-primary" name ="action" value="Save">
       				</div>
                	<div>
           				<input type="submit" class="btn btn-primary" name ="action" value="Save & Request Approval"/>
       				</div>
                </div>

        </form:form>
    </div>
</body>
</html>