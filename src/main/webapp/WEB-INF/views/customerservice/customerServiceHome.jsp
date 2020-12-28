<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Service</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        table, td, th {
          border: 1px solid #E8E8E8;
        }

        table {
            background-color:#F8F9FA;
            width:250px;
        }
    </style>

</head>
<body>
    <div class="container-fluid">

        <jsp:include page="/navbar.jsp" />

        <div class="row pt-3 pl-3 flex-column">
            <h2 class="  display-3" style="font-size: 30px">
                <b> Customer Service Home </b>
            </h2>
        </div>

        <hr width="" color="#b3b3b3">
    </div>

    <div class="container-fluid"
        <br>
        <nav class="flex-column">
            <table>
                <tr>
                    <td><a class="nav-link" href="<%= request.getContextPath()%>/customerLoanSearch" id="loanSummary">Loan Summary</a></td>
                </tr>
                <tr>
                    <td><a class="nav-link" href="<%= request.getContextPath()%>/customerdetailsform" id="custDetails">Customer Details</a>
                </tr>
                <tr>
                    <td><a class="nav-link" href="<%= request.getContextPath()%>/getRepaymentScheduleReportSearchPage" id="repaySchedule">Repayment Schedule</a></td>
                </tr>
                <tr>
                    <td><a class="nav-link" href="<%= request.getContextPath()%>/loanDisbursalForm" id="disDetails">Disbursal Details</a></td>
                </tr>
                <%--
                <tr>
                    <td><a class="nav-link" href="<%= request.getContextPath()%>/loanClosureForm" id="disDetails">Loan Closure search</a></td>
                </tr>
                --%>
            </table>
        </nav>
    </div>
</body>
</html>