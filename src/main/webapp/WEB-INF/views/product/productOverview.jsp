<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Product Overview</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script type="text/javascript">
		$(document).ready(function() {
		    $('#productTable').DataTable();
		} );
	</script>
</head>

<body>

<div class="container-fluid">

    <jsp:include page="/navbar.jsp" />

    <br>
    <div class="row">
        <div class="col-sm-10 col-12">
            <h2 class="display-3" style="font-size: 30px">
                <strong>Products</strong>
            </h2>
        </div>
        <div class="col-sm-2 col-12">
            <button type="button" onclick='location.href="<%= request.getContextPath()%>/newProduct"' class="btn btn-primary" id="newProduct">New Product</button>
        </div>
    </div>
</div>

<br>
<div class="container-fluid">
    <div class="row px-3 flex-column">
        <!-- Show n entries -->
        <!-- Source : https://stackoverflow.com/questions/41436805/how-to-display-a-table-with-10-rows-per-page -->
        <table id="productTable" class="table table-striped table-bordered display" style="width:100%">
            <thead>
            <tr>
                <th>Product Code</th>
                <th>Product Name</th>
                <th>Product Description</th>
                <th>Product Type</th>
                <th>Status</th>
                <th>Created By</th>
                <th>Reviewed By</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty products}">
            <c:forEach var="product" items="${products}">
                 <tr>
                  <td>${product.productCode}</td>
                  <td>${product.productName}</td>
                  <td>${product.productDescription}</td>
                  <td>${product.productType}</td>
                  <td>${product.status}</td>
                  <td>${product.createdBy}</td>
                  <td>${product.authorizedBy}</td>
                  <td><a href="#">Edit</a> | <a href="#">Delete</a></td>
                 </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>
