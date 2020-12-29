<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Repayment Schedule</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <style>
    h1 {text-align: center;}
    </style>
</head>
<body>
 <div style="width:90%;border: 1px solid black;padding-left:20px;margin:5px;" class ="ss" >
<div class="container-fluid">
    <jsp:include page="/navbar.jsp" />

</div>

 <div class="row">
        <div class="col-sm-10 col-12">
            <h2 class="display-3" style="font-size: 30px">
                <strong>Repayment Schedule</strong>
				<br>
				<br>
				<div style="font-size:20px;" >
                <strong><label for="loanAccountNumber">Loan Account# </label></strong>
				</div>
                <input type="Number" id="loanAccountNumber" name="loanAccountNumber"><br>
            </h2>
        </div>
        <div  style = "padding-top: 200px; class="col-sm-2 col-12" >
            <button type="button" class="btn btn-primary" id="showReport">show report</button>
            <button type="button" class="btn btn-primary" id="clear">clear</button>


        </div>
    </div>
</div>
</body>
</html>
