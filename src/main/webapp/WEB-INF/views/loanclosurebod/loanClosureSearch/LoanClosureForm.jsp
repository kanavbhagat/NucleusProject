<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Loan Closure Search</title>
    <link rel="stylesheet" href="styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

     <script type="text/javascript">
                function customerf() {
                        document.getElementById("loanApplicationNumber").disabled= true;
                        document.getElementById("search").disabled= false;
                    }
                    function loanApplicationf() {
                        document.getElementById("customerCode").disabled= true;
                        document.getElementById("search").disabled= false;
                    }
                function checkEmpty(){
                    var value1 = document.getElementById("customerCode").value;
                    var value2 = document.getElementById("loanApplicationNumber").value;

                    if ( (!value1 || !value1.trim()) && (!value2 || !value2.trim()) ){
                        $("#error").show();
                        return false;
                    }

                    return true;
                }

        		$(document).ready(function() {
                    $( "#error" ).hide();
        		});

     </script

</head>

<body>

<div class="container-fluid">

    <jsp:include page="/navbar.jsp" />

    <div class="row pt-3 pl-3 flex-column">
        <h2 class="  display-3" style="font-size: 30px">
            <b> Loan Closure Service</b>
        </h2>
    </div>

    <hr width="" color="#b3b3b3">

</div>

<!-- Form Container -->
<div class="container-fluid">
    <form  action="customerLoanClosure" method="post">
        <div class="row">
            <div class="col-sm-3">

                <div class="form-group">
                    <label for="customerCode" class="font-weight-bold ">Customer #</label>
                    <input type="text" id="customerCode" class="form-control" oninput="customerf()" name="customerCode" title="Customer Id should be alphanumeric with no spaces around"  pattern="^[a-zA-Z0-9]*$"/>
                </div>


            </div>
            <div class="col-sm-3 offset-sm-4">

                <div class="form-group">
                    <label for="loanApplicationNumber" class="font-weight-bold ">Loan Account #</label>
                    <input type="number" id="loanApplicationNumber" oninput="loanApplicationf()" class="form-control" name="loanApplicationNumber"/>
                </div>

            </div>
        </div>

        <hr width="" color="#b3b3b3">

        <span id="error" style="color:red;">Please enter a value in at least one field</span>

    <div class="row">
        <div class="col-sm-3 offset-sm-10">
            <button type="submit" onclick="return checkEmpty()" class="btn btn-primary" id="search">Search</button>
            <button type="reset" class="btn btn-primary" id="clear">Clear</button>
        </div>
    </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>

</body>
</html>
