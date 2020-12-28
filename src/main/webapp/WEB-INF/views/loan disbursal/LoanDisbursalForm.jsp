<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Loan Disbursal Service </title>
    <link rel="stylesheet" href="styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="container-fluid">
    <div class="row pt-3 pl-3 flex-column">
        <h2 class="  display-3" style="font-size: 30px">
            <b> Loan Disbursal Service</b>
        </h2>
    </div>

    <hr width="" color="#b3b3b3">

</div>

<!-- Form Container -->
<div class="container-fluid">
    <form  action="" name="disbursalform" id="disbursalform" onsubmit="return onsubmitform();" method="get">
        <div class="row">
            <div class="col-sm-3">

                <div class="form-group">
                    <label for="customerCode" class="font-weight-bold ">Customer#</label>
                    <input type="text" class="form-control" name="customerCode" id="customerCode" required oninput="customerf()" />
                </div>


            </div>
            <div class="col-sm-3 offset-sm-4">

                <div class="form-group">
                    <label for="loanApplicationNumber" class="font-weight-bold ">Loan Application Number#</label>
                    <input type="number" class="form-control" name="loanApplicationNumber" id="loanApplicationNumber" oninput="loanApplicationf()" required />
                </div>

            </div>
        </div>

        <hr width="" color="#b3b3b3">

        <div class="row">
            <div class="col-sm-3 offset-sm-10">
                <button type="submit" class="btn btn-primary" id="search" disabled="true">Search</button>
                <button type="button" class="btn btn-primary" id="clear" onclick="resetf()">Reset</button>
            </div>
        </div>

    </form>
</div>

<script>
    function customerf() {
        document.getElementById("loanApplicationNumber").disabled= true;
        document.getElementById("search").disabled= false;
    }
    function loanApplicationf() {
        document.getElementById("customerCode").disabled= true;
        document.getElementById("search").disabled= false;
    }
    function resetf() {
        document.getElementById("customerCode").disabled= false;
        document.getElementById("loanApplicationNumber").disabled= false;
        document.getElementById("customerCode").value = "";
        document.getElementById("loanApplicationNumber").value = "";
        document.getElementById("search").disabled= true;
    }
    function onsubmitform() {
        if(document.getElementById("customerCode").value != "") {
            document.disbursalform.action ="customerloandisbursal";
        }
        else
        if(document.getElementById("loanApplicationNumber").value != "") {
            document.disbursalform.action ="loandisbursalDetails";
        }
        return true;
    }
</script>
</body>
</html>