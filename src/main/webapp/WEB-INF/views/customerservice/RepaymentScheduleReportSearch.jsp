<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>New Repayment Policy</title>
    <link rel="stylesheet" href="styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .required-field::after{
             content: "*";
             color: red;
        }
    </style>
</head>

<body>


<div class="container-fluid">


    <jsp:include page="/navbar.jsp" />

    <div class="row pt-3 pl-3 flex-column">
        <h2 class="  display-3" style="font-size: 30px">
            <b>  Repayment Schedule </b>
        </h2>
    </div>

    <hr width="" color="#6c757d">

    <div class="container-fluid">

        <form method="GET" action="<%= request.getContextPath()%>/getRepaymentScheduleReport">

            <div class="row">
                <div class="col-sm-3">
                    <div class="form-group">
                        <label class="font-weight-bold required-field">Loan Application Number:</label><br>
                        <input name="appNo" type="text" class="form-control" id="appNo"/><br>
                    </div>
                </div>
            </div>

            <hr width="" color="#6c757d">

            <div class="row" style="margin-bottom:20px">

                <div class="col-sm-3 offset-sm-9">
                    <input type="submit" class="btn btn-primary" name="showreport" value="Show Report" />
                    <input type="reset" class="btn btn-primary" name="clear" value="Clear" />
                </div>

            </div>
        </form>
    </div>
</body>
</html>
