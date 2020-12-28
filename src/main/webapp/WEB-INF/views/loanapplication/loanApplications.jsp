<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Loan Application</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"> </script>

    <script type="text/javascript">
        $(function() {
            $("#customerCoccde").autocomplete({
                source: "customerAutoComplete",
                minLength:2
             });
         });
    </script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#loanApplicationsTable').DataTable();
        } );
    </script>


</head>

<body>

<div class="container-fluid">

    <jsp:include page="/navbar.jsp" />

    <div class="row pt-3 pl-3 flex-column">
            <h2 class="  display-3" style="font-size: 30px">
                <b> Applications
                 </b>
            </h2>

            <div class=" px-4 mt-0 align-self-end ">
                <sec:authorize access="hasRole('MAKER')">
                <button type="button" onclick='location.href="<%= request.getContextPath()%>/newCustomer"' class="btn btn-primary" id="newLoanApplication">New Application</button>
                </sec:authorize>
                <sec:authorize access="hasRole('CHECKER')">
                    <button type="button" disabled="disabled" onclick='location.href="<%= request.getContextPath()%>/newCustomer"' class="btn btn-primary" id="newLoanApplication">New Application</button>
                </sec:authorize>
            </div>
        </div>

        <hr width="" color="#b3b3b3">

        </div>
    <div class="container-fluid">
            <div class="row px-3 flex-column">
                <!-- Show n entries -->
                <!-- Source : https://stackoverflow.com/questions/41436805/how-to-display-a-table-with-10-rows-per-page -->
                <table id="loanApplicationsTable" class="table table-striped table-bordered display" style="width:100%">
                    <thead>
                        <tr>
                            <th>Application ID</th>
                            <th>Customer ID</th>
                            <th>Customer Name</th>
                            <th>Product Type</th>
                            <th>Create Date</th>
                            <th>Created By</th>
                            <th>Disbursal Status</th>
                            <th>Reviewed By</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${!empty loanApplications}">
                                   <c:forEach var="loan" items="${loanApplications}">
                                        <c:url var="checkUrl" value="/loanApplication/check?loanApplicationNumber=${loan.loanApplicationNumber}" />
                                        <c:url var="editUrl" value="/loanApplication/edit?loanApplicationNumber=${loan.loanApplicationNumber}" />
                                        <c:url var="deleteUrl" value="/loanApplication/delete?loanApplicationNumber=${loan.loanApplicationNumber}" />

                                        <tr>
                                        <sec:authorize access="hasRole('MAKER')">
                                            <td>${loan.loanApplicationNumber}</td>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('CHECKER')">
                                            <c:if test = "${loan.status == 'PENDING'}">
                                                 <td><a href="${checkUrl}">${loan.loanApplicationNumber}</a></td>
                                            </c:if>
                                            <c:if test = "${loan.status != 'PENDING'}">
                                                 <td>${loan.loanApplicationNumber}</td>
                                            </c:if>

                                         </sec:authorize>
                                         <td>${loan.customerCode.customerCode}</td>
                                         <td>${loan.customerCode.firstName}</td>
                                         <td>${loan.productCode.productName}</td>
                                         <td>${loan.createDate}</td>
                                         <td>${loan.createdBy}</td>
                                         <td>${loan.status}</td>
                                         <td>${loan.authorizedBy}</td>
                                        <sec:authorize access="hasRole('MAKER')">
                                            <c:if test = "${loan.status == 'APPROVED'}">
                                                 <td style="color:grey"><a>Edit</a> | <a>Delete</a></td>
                                            </c:if>
                                            <c:if test = "${loan.status != 'APPROVED'}">
                                                <td><a href="${editUrl}">Edit</a> | <a href="${deleteUrl}">Delete</a></td>
                                            </c:if>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('CHECKER')">

                                           <td style="color:grey"><a>Edit</a> | <a>Delete</a></td>

                                        </sec:authorize>
                                        </tr>
                                       </c:forEach>
                                   </c:if>
                    </tbody>

                </table>

            </div>
        </div>
</div>

<br>z