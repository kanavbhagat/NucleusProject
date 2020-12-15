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
    <!-- NavBar Starts -->
    <nav class="navbar navbar-expand-sm navbar-light bg-light" >
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarTogglerDemo03 ">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

                <li class="nav-item mx-2">
                    <a class="nav-link" href="#">Product <span class="sr-only">(current)</span></a>
                </li>

                <li class="nav-item dropdown dmenu mx-2">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop1" data-toggle="dropdown">
                        Policy Setup
                    </a>
                    <div class="dropdown-menu sm-menu">
                        <a class="dropdown-item" href="#">Link 1</a>
                        <a class="dropdown-item" href="#">Link 2</a>
                        <a class="dropdown-item" href="#">Link 3</a>
                    </div>
                </li>

                <li class="nav-item dropdown dmenu mx-2">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">
                        Parameters
                    </a>
                    <div class="dropdown-menu sm-menu">
                        <a class="dropdown-item" href="#">Link 1</a>
                        <a class="dropdown-item" href="#">Link 2</a>
                        <a class="dropdown-item" href="#">Link 3</a>
                    </div>
                </li>

                <li class="nav-item mx-2">
                    <a class="nav-link" href="#">Application</a>
                </li>

                <li class="nav-item mx-2">
                    <a class="nav-link" href="#">Receipt</a>
                </li>

                <li class="nav-item dropdown dmenu mx-2">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop3" data-toggle="dropdown">
                        Accounting
                    </a>
                    <div class="dropdown-menu sm-menu">
                        <a class="dropdown-item" href="#">Link 1</a>
                        <a class="dropdown-item" href="#">Link 2</a>
                        <a class="dropdown-item" href="#">Link 3</a>
                    </div>
                </li>

                <li class="nav-item mx-2">
                    <a class="nav-link" href="#">Customer Service</a>
                </li>

                <li class="nav-item dropdown dmenu mx-2">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop4" data-toggle="dropdown">
                        Report
                    </a>
                    <div class="dropdown-menu sm-menu">
                        <a class="dropdown-item" href="#">Link 1</a>
                        <a class="dropdown-item" href="#">Link 2</a>
                        <a class="dropdown-item" href="#">Link 3</a>
                    </div>
                </li>
                <li class="nav-item dropdown dmenu mx-2">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop5" data-toggle="dropdown">
                        BOD
                    </a>
                    <div class="dropdown-menu sm-menu">
                        <a class="dropdown-item" href="#">Link 1</a>
                        <a class="dropdown-item" href="#">Link 2</a>
                        <a class="dropdown-item" href="#">Link 3</a>
                    </div>
                </li>

            </ul>
        </div>
    </nav>
    <!-- NavBar Ends -->

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
