<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="../../../resources/css/appstyles.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
      <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
      <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <title>Charge Policy Approval Screen</title>
</head>

<body>
 <div class="container-fluid">
<jsp:include page="/navbar.jsp" />
    <form:form method = "Post" action = "../updateStatus/${chargePolicyForApproval.chargePolicyCode}" modelAttribute= "chargePolicyForApproval">

  <h3 style="padding-bottom : 50px;">Create Charge Policies</h3>
  <hr>
  <div class="row">
    <div class="feature-box col-lg-6 col-md-4 col-sm-12">
      <p class="font-weight-bold" style="font-size : 1rem">Charge Policy Code</p>
      <form:input path="chargePolicyCode" class="form-control" style="width : 400px"  disabled="true"/>

    </div>
    <div class="feature-box col-lg-6 col-md-4 col-sm-12">
      <p class="font-weight-bold" style="font-size : 1rem; padding-bottom:0px">Charge Policy Name</p>
      <form:input path="chargePolicyName" class="form-control" style="width : 400px" disabled="true"/>

    </div>

  </div>
  <div class="row">
    <div class="feature-box col-lg-2 col-md-4 col-sm-12">
      <p class="font-weight-bold" style="font-size : 1rem; padding-top : 20px">Charge Policy Description</p>
      <form:textarea path="chargePolicyDesc" class="form-control" style="width : 400px" rows="3" disabled="true" />
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
          <td scope="row"><form:input path="charge.chargeCode" class="form-control" style="width : 400px" id = "chargeCode" disabled = "true"/></td>
          <td><input type="text" class="form-control" style="width : 400px" value = "${chargeCodeName}" id = "chargeCodeName" readonly = "readonly"/></td>

        </tr>
      </tbody>
    </table>
  </div>
  <div class="float-right">
    <button type="submit" class="btn btn-primary" name ="action" value="Approve">Approve</button>
    <button type="submit" class="btn btn-primary" name ="action" value="Reject">Reject</button>
  </div>

  </div>
</form:form>
</div>
</body>

</html>
