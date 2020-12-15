<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file = "/index.jsp"%>
<head>
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
    		<form>
    			<!-- Section 1 -->
    		    <div class="form-group row pt-2 pl-3">
    			      <div class="col-md-3">
    			        <label class="required" for="policyName" >Eligibility Policy Code</label><br>
    					<input type="text" class="form-control"  id="policyCode" name="policyCode">
    			      </div>

    			      <div class="col-md-3">
    			      </div>

    			      <div class="col-md-3">
    			        <label class="required" for="policyName">Eligibility Policy Name</label><br>
    					<input type="text" id="policyName" name="policyName" class="form-control">
    			      </div>
    		    </div>

    		    <div class="row pt-2 pl-3">
    				<div class="col-md-3">
    					<label for="policyDescription">Eligibility Policy Description</label><br>
    					<textarea class="form-control"  id="policyDescription" name="policyDescription"></textarea>
    				</div>
    			</div>
    			<br><br>

    			<!-- Section 2 -->
                <hr width="" color="#b3b3b3">
                <div class="row pt-3 px-3 d-flex justify-content-end">
                    <button type="button" class="btn btn-primary">Add Eligibility Parameter</button>
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
    				      <tr class="d-flex">
    				        <td class="col-7" style="text-align:center;"><select class="custom-select"></select></td>
    				        <td class="col-5" style="text-align:center;"> <input type="text" id="pname" name="fname"> </td>
    				      </tr>

    				    </tbody>
    				  </table>
    			</div>
                <hr width="" color="#b3b3b3">
    			<div class="row pt-3 px-3 d-flex justify-content-end">
                	<div class="px-2">
          				<button type="button" class="btn btn-primary">Save</button>
       				</div>
                	<div>
           				<button type="button" class="btn btn-primary">Save & Request Approval</button>
       				</div>

                </div>
        </form>
</body>
</html>