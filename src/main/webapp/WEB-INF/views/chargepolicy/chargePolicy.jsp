<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
  <title></title>
</head>

<body>
<jsp:include page="/navbar.jsp" />
    <form:form method = "Post" modelAttribute= "chargePolicy">

  <h3 style="padding-bottom : 50px;">Create Charge Policies</h3>
  <hr>
  <div class="row">
    <div class="feature-box col-lg-6 col-md-4 col-sm-12">
      <p class="font-weight-bold" style="font-size : 1rem">Charge Policy Code</p>
      <form:input path="chargePolicyCode" class="form-control" style="width : 400px"/>

    </div>
    <div class="feature-box col-lg-6 col-md-4 col-sm-12">
      <p class="font-weight-bold" style="font-size : 1rem; padding-bottom:0px">Charge Policy Name</p>
      <form:input path="chargePolicyName" class="form-control" style="width : 400px"/>

    </div>

  </div>
  <div class="row">
    <div class="feature-box col-lg-2 col-md-4 col-sm-12">
      <p class="font-weight-bold" style="font-size : 1rem; padding-top : 20px">Charge Policy Description</p>
      <form:textarea path="chargePolicyDesc" class="form-control" style="width : 400px" rows="3" />
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
          <th scope="row"><form:select path="chargeCode" class = "custom-select">
                            			<form:options items="${chargeCodeList}" />
                            		</form:select></th>
          <td><form:input path="chargeCodeName" class="form-control" style="width : 400px"/></td>

        </tr>
      </tbody>
    </table>
  </div>
  <div class="float-right">
    <button type="submit" class="btn btn-primary">Save</button>
    <button type="submit" class="btn btn-primary">Save & Request Approval</button>
  </div>
</form:form>
</body>

</html>



