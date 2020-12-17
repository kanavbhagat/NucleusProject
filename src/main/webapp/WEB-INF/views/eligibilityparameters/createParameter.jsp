<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file = "/navbar.jsp"%>
<html>

<head>
    <title>Allocation Policy Creator</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    <style>
    .required label:after {
    		    color: #e32;
    		    content: ' *';
    		    display:inline;
    		}

    </style>
</head>
<body>

<div class="container-fluid">

    <div class="row pt-3 pl-3">
        <h2 class="display-3" style="font-size: 30px">
            <b> Create Eligibility Parameters
            </b>
        </h2>
    </div>


    <hr width="" color="#b3b3b3">



</div>


<div class="container-fluid">
    <form:form action="insertparameter" method="Post" modelAttribute="eligibilityParameter">


        <div class="form-group row pt-2 pl-3">
            <div class="col-md-3 required">
                <label>Eligibility Parameter Code</label><br>
                <form:input type="text" class="form-control"  id="parameterCode" name="parameterCode" path="parameterCode" required="required"/>
            </div>

            <div class="col-md-3">
            </div>

            <div class="col-md-3 required">
                <label>Eligibility Parameter Name</label><br>
                <form:input type="text" class="form-control"  id="parameterName" name="parameterName" path="parameterName" required="required"/>
            </div>
        </div>

        <div class="row pt-2 pl-3">
            <div class="col-md-3">
                <label>Eligibility Parameter Description</label><br>
                <form:input type="text" class="form-control"  id="parameterDescription" name="parameterDescription" path="parameterDescription"/>
            </div>

            <div class="col-md-3">
            </div>

            <div class="col-md-3 ">
                <label >Parameter Min Value</label><br>
                <form:input type="text" class="form-control"  id="minValue" name="minValue" path="minValue"/>
            </div>

        </div>
<br>
        <div class="form-group row pt-2 pl-3">
            <div class="col-md-3">
                <label>Parameter Max Value</label><br>
                <form:input type="text" class="form-control"  id="maxValue" name="maxValue" path="maxValue"/>
            </div>


            </div>

        <div class="row pt-3 px-3 d-flex justify-content-end">
            <div class="px-2">
                <button type="submit" class="btn btn-primary" name="action1">Save</button>
            </div>
            <div>
                <button type="submit" class="btn btn-primary" name="action2">Save & Request Approval</button>
            </div>


        </div>

    </form:form>
</div>
</body>
</html>