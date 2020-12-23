<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>


<!DOCTYPE html>
<html>

<head>
    <title>Applications</title>
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
        });

    </script>
</head>

<body>

    <div class="container-fluid">
        <header>
               <jsp:include page="/navbar.jsp" />
           </header>


        <!-- Section Heading -->
        <div class="row pt-3 pl-3 flex-column">
            <h2 class="  display-3" style="font-size: 30px">
                <b> Applications
                </b>
            </h2>

            <div class=" px-4 mt-0 align-self-end ">
                <button type="button" onclick='location.href="<%=request.getContextPath() %>/newCustomer"' class="btn btn-primary" id="newLoanApplication">Create Application</button>
            </div>
        </div>

        <hr width="" color="#b3b3b3">

    </div>
    <!-- End Of Container -->

    <div class="container-fluid">
        <div class="row px-3 flex-column">
            <!-- Show n entries -->
            <!-- Source : https://stackoverflow.com/questions/41436805/how-to-display-a-table-with-10-rows-per-page -->
            <table id="example" class="table table-striped table-bordered display" style="width:100%">
                <thead>
                    <tr>
                        <th>Application ID</th>
                        <th>Customer ID</th>
                        <th>Customer Name</th>
                        <th>Product Type</th>
                        <th>Created By</th>
                        <th>Disbursal Status</th>
                        <th>Modified By</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="loan" items="${loanApplications}">
                        <tr>
                            <td>${loan.loanApplicationNumber}</td>
                            <td>${loan.customerCode.customerCode}</td>
                            <td>${loan.customerCode.firstName}</td>
                            <td></td>
                            <td>${loan.createdBy}</td>
                            <td>${loan.status}</td>
                            <td>${loan.modifiedBy}</td>
                            <td><a href="#">Edit</a> | <a href="#">Delete</a></td>
                        </tr>

                    </c:forEach>
                </tbody>

            </table>

        </div>
    </div>
</body>

</html>
