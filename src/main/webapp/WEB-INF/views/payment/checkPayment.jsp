<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 16-12-2020
  Time: 03:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
           prefix="security"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../..">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#paymentTable').DataTable();
        } );
    </script>
    <style>
        td {
            text-align: center;
            vertical-align: middle;
        }
        th {
            text-align: center;
            vertical-align: middle;
        }
    </style>
    <title>Check Payment</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/navbar.jsp" />
        <div class="row mt-3 ml-3">
            <h3>Payments</h3>
        </div>
    <div class="d-flex justify-content-end">
        <sec:authorize access="hasRole('MAKER')">
            <a class="btn btn-primary" href="<%= request.getContextPath()%>/payment/newPayment">New Payment</a>
        </sec:authorize>
        <sec:authorize access="hasRole('CHECKER')">
            <button class="btn btn-primary" disabled="disabled">New Payment</button>
        </sec:authorize>
    </div>
    <hr width="" color="#b3b3b3">
</div>
<div class="container-fluid">
    <div class="row px-3 flex-column">
        <table id="paymentTable" class="table table-striped table-bordered display" style="width:100%">
            <thead>
            <tr>
                <th>Loan Application Number</th>
                <th>Customer Code</th>
                <th>Payment Amount</th>
                <th>Status</th>
                <th>Created By</th>
                <th>Reviewed By</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${paymentList}" var="singlePayment">
                    <tr>
                        <sec:authorize access="hasRole('MAKER')">
                        <td>${singlePayment.loanApplicationNumber}</td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('CHECKER')">
                            <td><a href="#">${singlePayment.loanApplicationNumber}</a></td>
                        </sec:authorize>
                        <td>${singlePayment.customerCode}</td>
                        <td>${singlePayment.paymentAmount}</td>
                        <td>${singlePayment.paymentStatus}</td>
                        <td>${singlePayment.madeBy}</td>
                        <td>${singlePayment.reviewedBy}</td>
                        <sec:authorize access="hasRole('MAKER')">
                            <td>
                                <a href="<%=request.getContextPath()%>/payment/editPayment/${singlePayment.loanApplicationNumber}">Edit</a>
                                |
                                <a href="<%=request.getContextPath()%>/payment/deletePayment/${singlePayment.loanApplicationNumber}">Delete</a>
                            </td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('CHECKER')">
                            <td>
                                <a href="${editUrl}" disabled="disabled" style="pointer-events:none; cursor:default;color:lightgrey;">Edit</a>
                                |
                                <a href="${deleteUrl}" disabled="disabled" style="pointer-events:none; cursor:default;color:lightgrey;">Delete</a>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
