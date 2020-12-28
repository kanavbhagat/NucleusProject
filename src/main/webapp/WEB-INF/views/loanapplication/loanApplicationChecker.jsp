<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="security"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>

    <title>Loan Information Cheker</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
                           <form:input type="number" class="form-control" path="loanApplicationNumber" readonly = "true"/>
                      </div>
                       <div class="form-group col-sm-3 offset-4">
                                                  <label>Customer Code<a class="text-danger">*</a></label>
                                                  <form:input type="text" class="form-control" path="customerCode.customerCode" readonly="true" />
                                                  </div>
                       </div>
                    <div class="row ">
                         <div class="form-group col-sm-3">
                                <form:label path="productType" cssClass="font-weight-bold required-field">Product Type:</form:label>
                                <form:input type="text" class="form-control" value="${loanApplication.productCode.productName}"
                                path="ProductType" readonly = "true"/>
                                  </div>
                        <div class="form-group col-sm-3 offset-4">
                            <label>Loan Amount Requested<a class="text-danger">*</a></label>
                            <form:input type="number" class="form-control" path="loanAmountRequested" readonly = "true"/>
                        </div>

                    </div>
                    <div class="row ">


                        <div class="form-group col-sm-3 ">
                            <label>Tenure(in Years)<a class="text-danger">*</a></label>
                            <form:input type="number" class="form-control" path="tenure" readonly = "true" />
                        </div>

                        <div class="form-group col-sm-3 offset-4">
                            <label>Rate<a class="text-danger">*</a></label>
                            <form:input type="number" class="form-control" path="rate" readonly = "true"/>
                        </div>
                    </div>

                    <div class="row ">

                        <div class="form-group col-sm-3 ">
                            <label>Agreement Date<a class="text-danger">*</a></label>
                            <form:input type="date" class="form-control" value="${loanApplication.agreementDate}" path="agreementDate" readonly = "true" />
                        </div>

                        <div class="form-group col-sm-3 offset-4">
                            <label>Installment Due Date<a class="text-danger">*</a></label>
                            <form:input type="date" class="form-control" value="${loanApplication.installmentDueDate}" path="installmentDueDate" readonly = "true"/>

                        </div>

                    </div>

                </div>
            </section>
            <hr>
            <div class="text-center">
                <input class="btn-primary" type="submit" name="op" value="reject">
                <input class="btn-primary" type="submit" name="op" value="approve">
            </div>
        </form:form>
    </article>




</body>

</html>