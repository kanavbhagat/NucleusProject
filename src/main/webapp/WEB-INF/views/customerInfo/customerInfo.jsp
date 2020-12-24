<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script>
var subjectObject = {
  "India": {
    "India state 1": ["india state 1 city 1", "india state 1 city 2", "india state 1 city 3"],
    "India state 2": ["india state 2 city 1", "india state 2 city 2"],
    "India state 3": ["india state 3 city 1", "india state 3 city 2"]
  },
  "Australia": {
    "australia state 1": ["australia state 1 city 1", "australia state 1 city 2"],
    "australia state 2": ["australia state 2 city 1", "australia state 2 city 2"]
  }
}
window.onload = function() {
  var subjectSel = document.getElementById("country1");
  var topicSel = document.getElementById("state1");
  var chapterSel = document.getElementById("city1");
  for (var x in subjectObject) {
    subjectSel.options[subjectSel.options.length] = new Option(x, x);
  }
  subjectSel.onchange = function() {
    //display correct values
    for (var y in subjectObject[this.value]) {
      topicSel.options[topicSel.options.length] = new Option(y, y);
    }
  }
  topicSel.onchange = function() {
    //display correct values
    var z = subjectObject[subjectSel.value][this.value];
    for (var i = 0; i < z.length; i++) {
      chapterSel.options[chapterSel.options.length] = new Option(z[i], z[i]);
    }
  }
}
</script>
   <title>Customer Information</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</head>

<body class="container-fluid">
    <header>
         <jsp:include page="/navbar.jsp" />
    </header>
    <article>
        <spring:form class="font-weight-bold mb-5" modelAttribute="customer" method="post" >
            <section>
                <div class="row">
                    <a class="col-lg-2 col-md-2 col-6">Customer Information</a>
                    <a href="loanInformation.jsp" class="col-lg-2 col-md-2 col-6">Loan Information</a>

                </div>
            </section>

            <hr>
            <!--Personal Information-->
            <section>
                <div>
                    <h2 class="mb-lg-3">Personal Information</h2>

                    <div class="row ">


                        <div class="form-group col-sm-3">
                            <label for="fName">First Name<a class="text-danger">*</a></label>
                            <spring:input type="text" class="form-control" id="fName"  path="firstName"/>
                            <spring:errors path = "firstName" cssClass = "error" style = "color:red"></spring:errors>

                        </div>
                    </div>

                    <div class="row ">
                        <div class="form-group col-sm-3">
                            <label for="lName">Last Name<a class="text-danger">*</a></label>
                            <spring:input type="text" class="form-control" id="lName" path="lastName" />
                            <spring:errors path = "lastName" cssClass = "error" style = "color:red"></spring:errors>
                        </div>

                       <div class="form-group col-sm-3 offset-4">
                            <label for="name">Full Name<a class="text-danger">*</a></label>
                            <input type="text" class="form-control" id="name" path="name"/>
                        </div>
                    </div>

                    <div class="row ">
                         <div class="form-group col-sm-3">
                            <label for="country">Nationality</label>
                            <spring:select class="form-control" id="country" name="nationality" path="nationality">
                                <spring:option value="india">India</spring:option>
                                <spring:option value="china">China</spring:option>

                            </spring:select>
                            <spring:errors path = "nationality" cssClass = "error" style = "color:red"></spring:errors>
                        </div>

                       <div class="form-group col-sm-3 offset-4">
                            <label for="dob">Date of Birth<a class="text-danger">*</a></label>
                            <spring:input class="form-control" type="date" name="dob" placeholder="dd-mm-yyyy" path="dateOfBirth" />
                            <spring:errors path = "dateOfBirth" cssClass = "error" style = "color:red"></spring:errors>
                        </div>
                    </div>
                </div>
            </section>
            <hr>

            <!--Employement Details-->
            <section>
                <h2 class="mb-lg-3">Employement Details</h2>
                <div class="row ">
                    <div class="form-group col-sm-3">
                        <label for="occupation">Occupation Type<a class="text-danger">*</a></label>
                        <spring:select class="form-control" id="occupation" path="occupationType">

                            <spring:option value="selfEmployed">Self Employed</spring:option>
                            <spring:option value="service">Serivce</spring:option>

                        </spring:select>
                        <spring:errors path = "occupationType" cssClass = "error" style = "color:red"></spring:errors>
                    </div>

                   <div class="form-group col-sm-3 offset-4">
                        <label for="experience">Total Work Experience</label>
                        <spring:input type="number" class="form-control" id="experience"  path="totalWorkExperience"/>
                        <spring:errors path = "totalWorkExperience" cssClass = "error" style = "color:red"></spring:errors>
                    </div>
                </div>

                <div class="row ">
                     <div class="form-group col-sm-3">
                        <label for="occupationName">Organisation Name</label>
                        <spring:input type="text" class="form-control" id="occupationName" path="organizationName"/>
                        <spring:errors path = "organizationName" cssClass = "error" style = "color:red"></spring:errors>
                    </div>
                </div>


            </section>
            <hr>
             <h2 class="mb-lg-3">Address Details</h2>
                                <div class="row ">
                                    <div class="form-group col-sm-3">
                                        <label for="houseNo">House No</label>
                                        <spring:input type="number" class="form-control" id="houseNo" path="add.houseNo"/>
                                        <spring:errors path = "add.houseNo" cssClass = "error" style = "color:red"></spring:errors>
                                    </div>

                                    <div class="form-group col-sm-3 offset-4">
                                        <label for="country">Country</label>
                                        <spring:select class="form-control" id="country1" path="add.country">
                                             <spring:option value="india">India</spring:option>
                                             <spring:option value="china">China</spring:option>
                                             <spring:errors path = "add.country" cssClass = "error" style = "color:red"></spring:errors>

                                        </spring:select>
                                    </div>
                                </div>

                                <div class="row ">
                                    <div class="form-group col-sm-3">
                                        <label for="state">State</label>
                                        <spring:select class="form-control" id="state1" path="add.state">
                                           <spring:option value="delhi">delhi</spring:option>
                                           <spring:option value="jammu">jammu</spring:option>

                                        </spring:select>
                                        <spring:errors path = "add.state" cssClass = "error" style = "color:red"></spring:errors>
                                    </div>
                                     <div class="form-group col-sm-3 offset-4">
                                        <label for="city">City</label>
                                        <spring:select class="form-control" id="city1" path="add.city">
                                           <spring:option value="noida">noida</spring:option>
                                           <spring:option value="meerut">bareli</spring:option>
                                        </spring:select>
                                        <spring:errors path = "add.city" cssClass = "error" style = "color:red"></spring:errors>
                                    </div>
                                </div>

                                <div class="row ">
                                   <div class="form-group col-sm-3">
                                        <label for="pinCode">Pin Code</label>
                                        <spring:input type="number" class="form-control" id="pinCode" path="add.pinCode"/>
                                        <spring:errors path = "add.pinCode" cssClass = "error" style = "color:red"></spring:errors>
                                    </div>
                                </div>

            <hr>
            <div class="text-center">
                <input class="btn-primary mr-3" type="submit" value="Next" name="submit">
           </div>
        </spring:form>
    </article>
</body>

</html>