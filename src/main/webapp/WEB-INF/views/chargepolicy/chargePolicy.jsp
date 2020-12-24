<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
      <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
      <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
    .table {
            width:80%;
    }
    .required:after {
        content:" *";
        color: red;
    }
    .error-messages {
        color:red;
    }
</style>
  <title>Charge Policy Creation Screen</title>
</head>

<body>
 <div class="container-fluid">
<jsp:include page="/navbar.jsp" />

    <form:form action = "newChargePolicy" modelAttribute= "chargePolicy" >
    <input name="csrfToken" value="${_csrf.token}" type="hidden">
  <h3 style="padding-bottom : 50px;">Create Charge Policies</h3>
  <hr>
  <div class="row">
    <div class="feature-box col-lg-6 col-md-4 col-sm-12">
      <label class="required" ><b>Charge Policy Code</b></label>
      <form:input path="chargePolicyCode"  id = "chargePolicyCode"  class="form-control" style="width : 400px" required="required"/>
      <form:errors path = "chargePolicyCode" cssClass = "error" style = "color:red"></form:errors>
       <p style = "color : red">${exception}</p>
    </div>
    <div class="feature-box col-lg-6 col-md-4 col-sm-12">
      <label class="required" ><b>Charge Policy Name</b></label>
      <form:input path="chargePolicyName" id = "chargePolicyName" class="form-control" style="width : 400px" required="required"/>
      <form:errors path = "chargePolicyName" cssClass = "error" style = "color:red"></form:errors>

    </div>

  </div>
  <div class="row">
    <div class="feature-box col-lg-2 col-md-4 col-sm-12">
       <label><b>Charge Policy Description</b></label>
      <form:textarea path="chargePolicyDesc"  id = "chargePolicyDesc" class="form-control" style="width : 400px" rows="3" />
      <form:errors path = "chargePolicyDesc" cssClass = "error" style = "color:red"></form:errors>
    </div>
  </div>



  <hr>

  <h3 style="padding-bottom : 50px;">Charges</h3>
  <div class="input-group mb-3">
    <table class="table table-bordered" style = "width : 1400px">
      <thead>
        <tr>
          <th scope="col">Charge Code</th>
          <th scope="col">Charge Code Name</th>
        </tr>
      </thead>
  <tbody>
        <tr>
          <th scope="row"><form:select path="charge.chargeCode" class = "custom-select" id = "chargeCode">
                            			<form:options items="${chargeCodeList}" />
                            		</form:select></th>
          <td><form:input path="chargeCodeName" class="form-control" style="width : 400px" id = "chargeCodeName"/></td>

        </tr>
      </tbody>
    </table>
  </div>
  <div class="float-right">
    <button type="submit" class="btn btn-primary" name ="action" value="save">Save</button>
    <button type="submit" class="btn btn-primary" name ="action" value="saveAndRequest">Save and Request Approval</button>
  </div>

  </div>
</form:form>
 <div class="container-fluid">
</body>
<script>
  $(document).ready(function(){

    $("#chargeCode").change(function(){
      var token = $('input[name="csrfToken"]').attr('value');
      var charge  = {};
      charge["chargeCode"] =$('#chargeCode').find(":selected").text();
      charge["chargeCodeName"] =  "default";

      $.ajax({
          				type : "POST",
          				contentType : "application/json",
          				url : "newChargePolicy/getCharge",
          				data : JSON.stringify(charge),
          				dataType : 'json',
          				 headers: {
                                              'X-CSRF-Token': token
                                         },
          				success : function(data) {

          					$('#chargeCodeName').val(data.chargeCodeName);
                              $('#chargeCodeName').attr('readonly', true);

          				}

          			});
    });
  });
  </script>

</html>



