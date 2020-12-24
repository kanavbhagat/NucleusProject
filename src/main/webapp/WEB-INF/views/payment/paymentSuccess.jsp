<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 12/24/2020
  Time: 7:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Payment Success</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<hr style="width: 98%">

<div class="container pt-4 ">
    <div class="jumbotron text-center" style="background-color:rgba(141, 181, 150, 0.3);">


        <h1 class="display-4">Payment ${status} Successfully!</h1>

        <p class="lead"><strong>Your payment has been ${status} successfully.</strong></p>

        <hr>

        <div class="d-flex justify-content-center">

            <%-- Insert Dynamic Message Here Eg- policy code etc --%>
            <p class="lead mt-4 " style="border:1px; border-style:solid; border-color:rgba(141, 181, 150, 0.8); padding: 5px;background-color:rgba(141, 181, 150, 0.1);width: 50%;">
                <strong>Loan Application Number: </strong> ${loanID}</p>

        </div>
        <div class="d-flex justify-content-center">
            <a href="<%= request.getContextPath()%>/payment/">
                <button class="btn btn-info">Go To Payment</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
