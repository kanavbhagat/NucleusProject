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
<title>Edit Charge</title>
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
			<b>Create Charge</b>
		</h2>
	</div>
	<hr width="" color="#b3b3b3">
    </div>

    <!-- Placeholders  -->
    	<div class="container-fluid">
    		<form:form method = "post" action = "addEdited" modelAttribute = "newCharge">
    			<!-- Section 1 -->
    		    <div class="form-group row pt-2 pl-3">
    			      <div class="col-md-3">
    			        <label class="required" for="chargeCode" >Charge Code</label><br>
    					<form:input path="chargeCode" type="text" class="form-control" id="chargeCode" name="chargeCode" required="required" readonly="true"/>
    			      </div>

    			      <div class="col-md-3">
    			        <label class="required" for="chargeCodeName">Charge Name</label><br>
    					<form:input path="chargeCodeName" type="text" id="chargeCodeName" name="chargeCodeName" class="form-control" required="required" readonly="true"/>
    			      </div>
    		    </div>

    		    <div class="form-group row pt-2 pl-3">
    				<div class="col-md-3">
    					<label for="chargeDescription">Charge Description</label><br>
    					<form:textarea path="chargeDescription" class="form-control"  id="chargeDescription" name="chargeDescription"/>
    				</div>
    				<div class="col-md-3">
                        <form:label path="transactionEvent" class="font-weight-bold">Transaction Event</form:label>
                        <form:select class="form-control" path="transactionEvent">
                            <form:options items="${eventList}"></form:options>
                        </form:select>
                        <form:errors path = "transactionEvent" class = "text-danger"></form:errors>
                    </div>
                </div>
                <div class="form-group row pt-2 pl-3">
                    <div class="col-md-3">
                        <form:label path="chargePaymentType" class="font-weight-bold">Charge Payment Type</form:label><br>
                        <form:radiobutton path = "chargePaymentType" value="Receivable"/><strong>Receivable</strong>
                        <form:radiobutton path = "chargePaymentType" value="Payable"/><strong>Payable</strong>
                        <form:radiobutton path = "chargePaymentType" value="Both"/><strong>Both</strong><br>
                        <form:errors path = "chargePaymentType" class = "text-danger"></form:errors>
                    </div>
                </div>
                <hr/>
                <h5>Computation Details</h5>
                <div class="form-group row pt-2 pl-3">
                    <div class="col-md-3">
                        <form:label path="chargeType" class="font-weight-bold">Charge Type</form:label>
                        <form:select class="form-control" path="chargeType">
                            <form:options items="${chargeTypeList}" />
                        </form:select>
                        <form:errors path = "chargeType" class = "text-danger"></form:errors>
                    </div>
                    <div class="col-md-3 offset-sm-4">
                        <form:label path="chargeAmount" class="font-weight-bold">Charge Amount</form:label>
                        <form:input path="chargeAmount" class="form-control" min="0"/>
                        <form:errors path = "chargeAmount" class = "text-danger"></form:errors>
                    </div>
                </div>
                <hr/>


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