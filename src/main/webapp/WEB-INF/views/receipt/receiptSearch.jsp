<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Payment Search Screen</title>
    <link rel="stylesheet" href="styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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

            </ul>
        </div>
    </nav>
    <!-- NavBar Ends -->

    <br>
    <div class="row">
        <div class="col-sm-10 col-12">
            <h2 class="display-3" style="font-size: 30px">
                <strong>Receipt/Payment(Search)</strong>
            </h2>
        </div>
        <div class="col-sm-2 col-12">
            <button type="button" onclick='location.href="<%= request.getContextPath()%>/newReceipt"' class="btn btn-primary" id="newReceipt">Create Receipt</button>
            <%--<button type="button" class="btn btn-primary" id="newReceipt">Create Receipt</button>--%>
        </div>
    </div>

    <hr>

</div>

<!-- Form Container -->
<div class="container-fluid">
    <form>
        <div class="row">
            <div class="col-sm-3">

                <div class="form-group">
                    <label for="receiptType" class="font-weight-bold required-field">Receipt Type</label>
                    <select class="form-control" id="receiptType">
                        <option value="" selected disabled hidden>Select One Option</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="receiptBasis" class="font-weight-bold">Receipt basis</label>
                    <select class="form-control" id="receiptBasis">
                        <option value="" selected disabled hidden>Select One Option</option>
                    </select>
                </div>

            </div>
            <div class="col-sm-3 offset-sm-4">

                <div class="form-group">
                    <label for="loanAccount" class="font-weight-bold required-field">Loan Account#</label>
                    <input type="text" class="form-control" id="loanAccount">
                </div>

                <div class="form-group">
                    <label for="receiptRef" class="font-weight-bold required-field">Receipt Ref#</label>
                    <input type="text" class="form-control" id="receiptRef">
                </div>

            </div>
        </div>

        <hr>

        <div class="row" style="margin-bottom:20px">
            <div class="col-sm-3 offset-sm-10">
                <button type="button"  id="save" class="btn btn-primary">Save</button>
                <button type="button" id="clear" class="btn btn-primary">Clear</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>