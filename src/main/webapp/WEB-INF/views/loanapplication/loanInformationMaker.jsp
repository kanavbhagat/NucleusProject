<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="security"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>

    <title>Loan Information</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
                function validateDate(){
                var str1=document.getElementById('agDate').value;
                var str2=document.getElementById('inDate').value;

                if(str1>str2)
                {
                    alert("Installment Due date must be greater tha agreement date");
                    return false;
                }
                }
            </script>
</head>

<body class="container-fluid">
    <header>
        <jsp:include page="/navbar.jsp" />
    </header>



    <article>
        <form:form class="font-weight-bold mb-5" modelAttribute="loanApplication" method="post">

            <hr>



            <section>
                <div>
                  <div class="row ">
                      <div class="form-group col-sm-3">
                           <label>Loan Application Number<a class="text-danger">*</a></label>
                           <form:input type="number" class="form-control" path="loanApplicationNumber" readonly="true" />
                           <form:errors path = "loanApplicationNumber" cssClass = "error"><p style = "color:red">Please put a valid 10 digit number having first digit as 1</p></form:errors>
                      </div>
                      <div class="form-group col-sm-3 offset-4">
                            <label>Customer Code<a class="text-danger">*</a></label>
                            <form:input type="text" class="form-control" path="customerCode.customerCode" readonly="true" />
                      </div>
                       </div>
                    <div class="row ">
                         <div class="form-group col-sm-3">
                                <form:label path="productType" cssClass="font-weight-bold required-field">Product Type<a class="text-danger">*</a></form:label>
                                 <form:select path="productType" cssClass="form-control">
                                  <form:option value="${loanApplication.productCode.productName}"
                                  selected="true" label="${loanApplication.productCode.productName}"/>

                                       <form:options items="${productType}" />
                                 </form:select>
                                 <form:errors path = "productType" cssClass = "error" style = "color:red"></form:errors>
                                  </div>
                        <div class="form-group col-sm-3 offset-4">
                            <label>Loan Amount Requested<a class="text-danger">*</a></label>
                            <form:input type="number" class="form-control"
                             max="${loanApplication.productCode.maxExposureAmount}" path="loanAmountRequested" />
                            <form:errors path = "loanAmountRequested" cssClass = "error" style = "color:red"></form:errors>
                        </div>

                    </div>
                    <div class="row ">


                        <div class="form-group col-sm-3 ">
                            <label>Tenure(in Years)<a class="text-danger">*</a></label>
                            <form:input type="number" class="form-control"
                             path="tenure" />
                            <form:errors path = "tenure" cssClass = "error" style = "color:red"></form:errors>
                        </div>

                        <div class="form-group col-sm-3 offset-4">
                            <label>Rate<a class="text-danger">*</a></label>
                            <form:input type="number" class="form-control" path="rate" />
                            <form:errors path = "rate" cssClass = "error" style = "color:red"></form:errors>
                        </div>
                    </div>

                    <div class="row ">

                        <div class="form-group col-sm-3 ">
                            <label>Agreement Date<a class="text-danger">*</a></label>
                            <form:input type="date" id="agDate" class="form-control" value="${loanApplication.agreementDate}" path="agreementDate" />
                            <form:errors path = "agreementDate" cssClass = "error" style = "color:red"><p style="color:red">Invalid Date</p></form:errors>
                        </div>

                        <div class="form-group col-sm-3 offset-4">
                            <label>Installment Due Date<a class="text-danger">*</a></label>
                            <form:input type="date" id="inDate" class="form-control" value="${loanApplication.installmentDueDate}" path="installmentDueDate" />
                            <form:errors path = "installmentDueDate" cssClass = "error" style = "color:red"><p style="color:red">Invalid Date</p></form:errors>
                        </div>

                    </div>

                </div>
            </section>
            <hr>
            <div class="text-center">
                <input class="btn-primary" onclick="return validateDate()" type="submit" name="op" value="Update">
                <input class="btn-primary" onclick="return validateDate()" type="submit" name="op" value="Update and Request">
            </div>
        </form:form>
    </article>




</body>

</html>