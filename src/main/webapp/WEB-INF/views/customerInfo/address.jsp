<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
 <title>Address Information</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="container-fluid">
    <header>
         <jsp:include page="/navbar.jsp" />
    </header>
      <spring:form class="font-weight-bold mb-5" modelAttribute="address" method="post" >
     <h2 class="mb-lg-3">Address Details</h2>
                    <div class="row ">
                        <div class="form-group col-sm-3">
                            <label for="houseNo">House No</label>
                            <spring:input type="number" class="form-control" id="houseNo" path="houseNo"/>

                        </div>

                        <div class="form-group col-sm-3 offset-4">
                            <label for="country">Country</label>
                            <spring:select class="form-control" id="country1" path="country">
                                 <spring:option value="india">India</spring:option>
                                 <spring:option value="china">China</spring:option>


                            </spring:select>
                        </div>
                    </div>

                    <div class="row ">
                        <div class="form-group col-sm-3">
                            <label for="state">State</label>
                            <spring:select class="form-control" id="state1" path="state">
                               <spring:option value="delhi">delhi</spring:option>
                               <spring:option value="jammu">jammu</spring:option>

                            </spring:select>
                        </div>
                         <div class="form-group col-sm-3 offset-4">
                            <label for="city">City</label>
                            <spring:select class="form-control" id="city1" path="city">
                               <spring:option value="india">noida</spring:option>
                               <spring:option value="china">bareli</spring:option>


                            </spring:select>
                        </div>
                    </div>

                    <div class="row ">
                       <div class="form-group col-sm-3">
                            <label for="pinCode">Pin Code</label>
                            <spring:input type="number" class="form-control" id="pinCode" path="pinCode"/>
                        </div>
                    </div>


                 <hr>
            <div class="text-center">
                <input class="btn-primary mr-3" type="submit" value="save" name="submit">

            </div>
 </spring:form>
</body>

</html>