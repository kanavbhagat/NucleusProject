<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Customer Details Form </title>
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

<div class="container-fluid">

    <jsp:include page="/navbar.jsp" />

    <div class="row pt-3 pl-3 flex-column">
        <h2 class="  display-3" style="font-size: 30px">
            <b> Customer Details Form</b>
        </h2>
    </div>

    <hr width="" color="#b3b3b3">

</div>

<!-- Form Container -->
<div class="container-fluid">
    <form  action="customerdetails" name="customerdetailsform" id="customerdetailsform" method="get">
        <div class="row">
            <div class="col-sm-3">

                <div class="form-group">
                    <label for="customerCode" class="font-weight-bold ">Customer#</label>
                    <input type="text" class="form-control" name="customerCode" id="customerCode" required oninput="customerf()" />
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
        document.getElementById("search").disabled= false;
    }
    function resetf() {
        document.getElementById("customerCode").value = "";
        document.getElementById("search").disabled= true;
    }

</script>
</body>
</html>