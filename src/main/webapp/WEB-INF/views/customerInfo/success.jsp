<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html lang="en">
<head>

    <title>Document</title>
      <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
 <jsp:include page="/navbar.jsp" />
    <h1>Successfully registered customer</h1>

     <ul>
     <li><h2>address addition is : ${b}</h2></li>
     <li><h2>customer addition is : ${a}</h2></li>
     <li><h2>loan addition is : ${c}</h2></li>
     </ul>

</body>
</html>