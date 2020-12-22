<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
</head>

<body class="container-fluid">
    <header>
        <jsp:include page="/navbar.jsp" />
    </header>



    <article>
        <form:form class="font-weight-bold mb-5" modelAttribute="loanApplications" method="post">
            <section>
                <div class="row">
                    <a href="../customer/customerInfo.jsp" class="col-lg-2 col-md-2 col-6">Customer Information</a>
                    <a class="col-lg-2 col-md-2 col-6">Loan Information</a>

                </div>
            </section>

            <hr>



            <section>
                <div>
                    <div class="row ">
                        <div class="form-group col-sm-3">
                            <label>Loan Application Number<a class="text-danger">*</a></label>
                            <form:input type="number" class="form-control" path="loanApplicationNumber" />
                        </div>


                    </div>
                    <div class="row ">
                        <div class="form-group col-sm-3">
                            <label>Loan Amount Requested<a class="text-danger">*</a></label>
                            <form:input type="number" class="form-control" path="loanAmountRequested" />
                        </div>

                        <div class="form-group col-sm-3 offset-4">
                            <label>Tenure<a class="text-danger">*</a></label>
                            <form:input type="number" class="form-control" path="tenure" />
                        </div>
                    </div>

                    <div class="row ">
                        <div class="form-group col-sm-3">
                            <label>Rate<a class="text-danger">*</a></label>
                            <form:input type="number" class="form-control" path="rate" />
                        </div>

                        <div class="form-group col-sm-3 offset-4">
                            <label>Agreement Date<a class="text-danger">*</a></label>
                            <form:input type="date" class="form-control" path="agreementDate" />
                        </div>
                    </div>
                    <div class="row ">
                        <div class="form-group col-sm-3">
                            <label>Installment Due Date<a class="text-danger">*</a></label>
                            <form:input type="date" class="form-control" path="installmentDueDate" />
                        </div>

                        <div class="form-group col-sm-3 offset-4">
                            <label>Create Date<a class="text-danger">*</a></label>
                            <form:input type="date" class="form-control" path="createDate" />
                        </div>
                    </div>
                    <div class="row ">
                        <div class="form-group col-sm-3">
                            <label>Created By<a class="text-danger">*</a></label>
                            <form:input type="text" class="form-control" path="createdBy" />
                        </div>

                        <div class="form-group col-sm-3 offset-4">
                            <label> Modified Date<a class="text-danger">*</a></label>
                            <form:input type="date" class="form-control" path="modifiedDate" />
                        </div>
                    </div>

                    <div class="row ">
                        <div class="form-group col-sm-3">
                            <label>Modified By<a class="text-danger">*</a></label>
                            <form:input type="text" class="form-control" path="modifiedBy" />
                        </div>

                        <div class="form-group col-sm-3 offset-4">
                            <label> Authorized Date<a class="text-danger">*</a></label>
                            <form:input type="date" class="form-control" path="authorizedDate" />
                        </div>
                    </div>
                    <div class="row ">
                        <div class="form-group col-sm-3">
                            <label>Authorized By<a class="text-danger">*</a></label>
                            <form:input type="text" class="form-control" path="authorizedBy" />
                        </div>
                    </div>
                </div>
            </section>
            <hr>
            <div class="text-center">
                <input class="btn-primary" type="submit" value="Move to Next Stage">
            </div>
        </form:form>
    </article>
</body>

</html>
