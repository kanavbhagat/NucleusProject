<!DOCTYPE html>
<html lang="en">

<head>

    <title>Loan Information</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body class="container-fluid">
    <header>
       <!-- NavBar Starts -->
    <nav class="navbar navbar-expand-sm navbar-light bg-secondary mb-3" >
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarTogglerDemo03 ">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

                <li class="nav-item mx-2">
                    <a class="nav-link" href="<%= request.getContextPath()%>/product">Product <span class="sr-only">(current)</span></a>
                </li>

                <li class="nav-item dropdown dmenu mx-2">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarPolicyDropdown" data-toggle="dropdown">
                        Policy Setup
                    </a>
                    <div class="dropdown-menu sm-menu">
                        <a class="dropdown-item" href="<%= request.getContextPath()%>/showRepaymentPolicy">Repayment Policy</a>
                        <a class="dropdown-item" href="<%= request.getContextPath()%>/eligibilityPolicy/">Eligibility Policy</a>
                        <a class="dropdown-item" href="#">Link 3</a>
                    </div>
                </li>

                <li class="nav-item dropdown dmenu mx-2">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarParametersDropdown" data-toggle="dropdown">
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
                    <a class="nav-link dropdown-toggle" href="#" id="navbarAccountingDropDown" data-toggle="dropdown">
                        Accounting
                    </a>
                    <div class="dropdown-menu sm-menu">
                        <a class="dropdown-item" href="<%= request.getContextPath()%>/newPayment">New Payment</a>
                        <a class="dropdown-item" href="#">Link 2</a>
                        <a class="dropdown-item" href="#">Link 3</a>
                    </div>
                </li>

                <li class="nav-item mx-2">
                    <a class="nav-link" href="#">Customer Service</a>
                </li>

                <li class="nav-item dropdown dmenu mx-2">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarReportDropdown" data-toggle="dropdown">
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
    </header>



    <article>
        <form class="font-weight-bold mb-5" action="/loan.do">
            <section>
                <div class="row">
                    <a href="../customer/customerInfo.jsp" class="col-lg-2 col-md-2 col-6">Customer Information</a>
                    <a class="col-lg-2 col-md-2 col-6">Loan Information</a>

                </div>
            </section>

            <hr>



            <section>
                <div>
                    <div class="row ">
                        <div class="form-group col-lg-4 col-md-6 col-12 col-xl-4 ">
                            <label for="pwd">Loan Application No<a class="text-danger">*</a></label>
                            <input type="password" class="form-control" id="pwd" placeholder="Enter Loan Application No" name="Lid">
                        </div>

                        <div class="form-group col-lg-6 col-md-6 col-12 col-xl-4 ml-xl-5 pl-5">
                            <label for="sel1">Produt Type<a class="text-danger">*</a></label>
                            <select class="form-control" id="sel1" name="productType">
                                <option>Auto Loan</option>
                                <option>Property Loan</option>

                            </select>
                        </div>
                    </div>

                    <div class="row ">
                        <div class="form-group col-lg-4 col-md-6 col-12 col-xl-4 ">
                            <label for="sel1">Product<a class="text-danger">*</a></label>
                            <select class="form-control" id="sel1">
                                <option>Select One option</option>
                                <option>Home Loan</option>
                                <option>Car Loan</option>

                            </select>
                        </div>

                        <div class="form-group col-lg-6 col-md-6 col-12 col-xl-4 ml-xl-5 pl-5">
                            <label for="pwd">Loan Amount Requested<a class="text-danger">*</a></label>
                            <input type="password" class="form-control" id="pwd" placeholder="Enter Loan Amount Requested" name="loanAmountRequested">
                        </div>
                    </div>

                    <div class="row ">
                        <div class="form-group col-lg-4 col-md-6 col-12 col-xl-4 ">
                            <label for="pwd">Tenure(months)<a class="text-danger">*</a></label>
                            <input type="password" class="form-control" id="pwd" placeholder="Enter Tenue" name="tenure">
                        </div>

                        <div class="form-group col-lg-6 col-md-6 col-12 col-xl-4 ml-xl-5 pl-5">
                            <label for="pwd">Rate<a class="text-danger">*</a></label>
                            <input type="password" class="form-control" id="pwd" placeholder="Enter rate" name="rate">
                        </div>
                    </div>

                    <div class="row ">
                        <div class="form-group col-lg-4 col-md-6 col-12 col-xl-4 ">
                            <label for="pwd">Agreement Date</label>
                            <input type="password" class="form-control" id="pwd" placeholder="Enter Agreement date" name="date">
                        </div>

                        <div class="form-group col-lg-6 col-md-6 col-12 col-xl-4 ml-xl-5 pl-5">
                            <label for="pwd">Installment Due Date<a class="text-danger">*</a></label>
                            <input type="password" class="form-control" id="pwd" placeholder="Enter Installment due date" name="due date">
                        </div>
                    </div>


                </div>
            </section>
            <hr>
            <div class="text-center">
                <input class="btn-primary" type="submit" value="Move to Next Stage">
            </div>
        </form>
    </article>
</body>

</html>