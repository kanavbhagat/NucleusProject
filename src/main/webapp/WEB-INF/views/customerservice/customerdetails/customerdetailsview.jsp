<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <title>Customer Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
            $('#example').DataTable();
        } );
    </script>

</head>
<body>
<div class="container-fluid mt-2">

    <jsp:include page="/navbar.jsp" />

</div>

<hr style="width: 98%">

<div class="container pt-4 ">

    <div class="row d-flex justify-content-center">
        <h1 class="display-4">Customer Details</h1>
    </div>
    <div class="row d-flex justify-content-center mt-5 mb-2">

        <table class="table table-striped table-hover table-bordered table-sm ">
            <tr>
                <td>Customer Code :</td>
                <td><c:out value="${customerObj.customerCode}" /></td>
            </tr>
            <tr>
                <td>First Name :</td>
                <td><c:out value="${customerObj.firstName}" /></td>
            </tr>
            <tr>
                <td>Last Name :</td>
                <td><c:out value="${customerObj.lastName}" /></td>
            </tr>
            <tr>
                <td>Date of Birth :</td>
                <td><c:out value="${customerObj.dateOfBirth}" /></td>
            </tr>
            <tr>
                <td>Nationality :</td>
                <td><c:out value="${customerObj.nationality}" /></td>
            </tr>
            <tr>
                <td>Occupation Type :</td>
                <td><c:out value="${customerObj.occupationType}" /></td>
            </tr>
            <tr>
                <td>Total Work Experience :</td>
                <td><c:out value="${customerObj.totalWorkExperience}" /></td>
            </tr>
            <tr>
                <td>Organization Name :</td>
                <td><c:out value="${customerObj.organizationName}" /></td>
            </tr>
        </table>

    </div>

    <div class="container pt-4 ">

        <div class="row d-flex justify-content-center">
            <h1 class="display-4" style="font-size: 20px">Address Details</h1>
        </div>
        </div>
        <div class="container-fluid">
            <div class="row px-3 flex-column">
                <table id="example" class="table table-striped table-bordered display" style="width:100%">
                    <thead>
                    <tr>
                        <th>Address ID</th>
                        <th>House No</th>
                        <th>City</th>
                        <th>State</th>
                        <th>Country</th>
                        <th>Pincode</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${customerObj.addresses}" var="addressObj">
                            <tr>
                                <td><c:out value="${addressObj.addressId}" /></td>
                                <td><c:out value="${addressObj.houseNo}" /></td>
                                <td><c:out value="${addressObj.city}" /></td>
                                <td><c:out value="${addressObj.state}" /></td>
                                <td><c:out value="${addressObj.country}" /></td>
                                <td><c:out value="${addressObj.pinCode}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>

            </div>
        </div>

    </table>
        </div>
        </div>
</div>
</body>
</html>