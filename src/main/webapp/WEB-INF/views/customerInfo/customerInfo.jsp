<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script>
    //Validations
          function validateCustomer() {

              var str1=document.getElementById('fName').value;
              var str2=document.getElementById('lName').value;
              var str4=document.getElementById('dob').value;

              var str7=document.getElementById('experience').value;
              var str9=document.getElementById('organizationName').value;

              var str6=document.getElementById('houseNo').value;
              var str3=document.getElementById('country1').value;
              var str5=document.getElementById('state1').value;
              var str8=document.getElementById('city1').value;
              var str10=document.getElementById('pinCode').value;

            function getAge(DOB) {
                var today = new Date();
                var birthDate = new Date(DOB);
                var age = today.getFullYear() - birthDate.getFullYear();
                var m = today.getMonth() - birthDate.getMonth();
                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                    age--;
                }
                return age;
            }

              //Field validation
              if(str1.length==0 || str2.length==0 || str9.length==0 || str6.length==0 || str10==0 ||
                    str3=="-" || str5=="-" || str8=="-")
              {
                alert("Please fill the required fields");
                return false;
              }

              else{

              //Name validation
              if(str1.length<3)
              {
                alert("First name must have at least 3 alphabets");
                return false;
              }
              else
              if(str1.length>30)
              {
                alert("First name can have maximum 30 alphabets");
                return false;
              }
              for(let i=0;i<str1.length;i++)
              {
                if((/[a-zA-Z]/).test(str1[i])==false)
                {alert("First name must contain alphabets only");
                 return false;
                 break;}
              }

              if(str2.length<3)
              {
                alert("Last name must have at least 3 alphabets");
                return false;
              }
              else
              if(str1.length>30)
              {
                alert("Last name can have maximum 30 alphabets");
                return false;
               }
              for(let i=0;i<str2.length;i++)
              {
                if((/[a-zA-Z]/).test(str2[i])==false)
                {alert("Last name must contain alphabets only");
                return false;
                 break;}
              }


            if(getAge(str4)<18)
            {
                alert("Age can't be less than 18 years");
                return false;
            }

              //Organization validation
              if(str9.length>30)
              {alert("Organization name can have maximum 30 alphabets");
              return false;
              }

              //PinCode validation
              if(str10<100000 || str10>999999)
              {
                 alert("PinCode must be of 6 digits");
                 return false;
              }
              //House validation
              if(str6.length!=0)
              {for(let i=0;i<str6.length;i++)
              {
                if(isNaN(parseInt(str6[i])))
                {alert("House no should be numeric only");
                return false;
                break;}
              }
              }

              //Experience validation
              if(str7.length!=0)
              {
                if(parseInt(str7)<0)
                {
                alert("Work experience should be minimum 0 years");
                return false;
                }
                else
                {for(let i=0;i<str7.length;i++)
                  {if(str7[i]!='.')
                  {if(isNaN(parseInt(str7[i])))
                  {alert("Work experience must be numeric");
                  return false;
                  break;}
                  } }
                }
              }
            }
          }





window.onload = function() {

  var stateObject = {
    "India": { "Punjab": ["Amritsar",
                          "Chandigarh" ,
                          "Pathankot"],

               "Delhi": ["New Delhi",
                         "Delhi NCR" ,
                         "Old Delhi"],

               "Goa":   ["Madgao",
                         "Ponda",
                         "Panjim"],
              },

    "Canada": {
                "Alberta": ["Acadia",
                            "Bighorn"],
                "Columbia": ["Washington"]
              },
    "America":{"New York": [
                  "New York",
                  "Buffalo"
                ],
                "California": [
                  "Los Angeles",
                  "San Diego",
                  "San Jose",
                  "San Francisco"
                ],
                "Pennsylvania": [
                  "Philadelphia",
                  "Pittsburgh",
                  "Allentown"
                ],
                "Arizona": [
                  "Phoenix",
                  "Tucson",
                  "Mesa"
                ],
                "Florida": [
                  "Jacksonville",
                  "Miami",
                  "Tampa"
                ],
                "Indiana": [
                  "Indianapolis",
                  "Fort Wayne"
                ]}
    }



      var countySel = document.getElementById("country1"),
      stateSel = document.getElementById("state1"),
      districtSel = document.getElementById("city1");
      for (var country in stateObject) {
      countySel.options[countySel.options.length] = new Option(country, country);
      }
      countySel.onchange = function () {
      stateSel.length = 1;
      districtSel.length = 1;
      if (this.selectedIndex < 1) return;
      for (var state in stateObject[this.value]) {
      stateSel.options[stateSel.options.length] = new Option(state, state);
       }
     }
      countySel.onchange();
      stateSel.onchange = function () {
      districtSel.length = 1;
      if (this.selectedIndex < 1) return;
      var district = stateObject[countySel.value][this.value];
      for (var i = 0; i < district.length; i++) {
      districtSel.options[districtSel.options.length] = new Option(district[i], district[i]);
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
        <spring:form class="font-weight-bold mb-5" modelAttribute="customer" method="post">
            <section>

            </section>

            <hr>
                                    <!--Personal Information-->
                                    <section>
                                        <div>
                                            <h2 class="mb-lg-3">Personal Information</h2>

                                                <div class="row ">
                                                   <div class="form-group col-sm-3">
                                                        <label for="customerCode">Enter Customer Code<a class="text-danger">*</a></label>
                                                        <spring:input type="text" class="form-control" id="customerCode" required="required"
                                                        placeholder="eg. L101"
                                                        pattern="[A-Za-z].{3,10}" title="Only Alphanumeric characters allowed. Length should be from 3 to 10."
                                                         path="customerCode"/>
                                                    </div>
                                                </div>
                                                <div class="row ">


                                                <div class="form-group col-sm-3">
                                                    <label for="fName">First Name<a class="text-danger">*</a></label>
                                                    <spring:input type="text" class="form-control" id="fName"  path="firstName"/>
                                                    <spring:errors path = "firstName" cssClass = "error" style = "color:red"></spring:errors>
                                                    <p style = "color : red">${exception}</p>
                                                </div>

                                                <div class="form-group col-sm-3 offset-4">
                                                    <label for="lName">Last Name<a class="text-danger">*</a></label>
                                                    <spring:input type="text" class="form-control" id="lName" path="lastName" />
                                                    <spring:errors path = "lastName" cssClass = "error" style = "color:red"></spring:errors>
                                                </div>
                                            </div>

                                            <div class="row ">
                                                 <div class="form-group col-sm-3">
                                                    <label for="country">Nationality<a class="text-danger">*</a></label>
                                                    <spring:select class="form-control" id="country" name="nationality" path="nationality">
                                                        <spring:option value="India">India</spring:option>
                                                        <spring:option value="America">America</spring:option>
                                                        <spring:option value="Canada">Canada</spring:option>
                                                    </spring:select>
                                                    <spring:errors path = "nationality" cssClass = "error" style = "color:red"></spring:errors>
                                                </div>

                                               <div class="form-group col-sm-3 offset-4">
                                                    <label for="dob">Date of Birth<a class="text-danger">*</a></label>
                                                    <spring:input class="form-control" type="date" id="dob" name="dob" placeholder="dd-mm-yyyy" path="dateOfBirth" />
                                                    <spring:errors path = "dateOfBirth" cssClass = "error" style = "color:red"><p style="color:red">Invalid Date</p></spring:errors>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                    <hr>

                                    <!--Employement Details-->
                                    <section>
                                        <h2 class="mb-lg-3">Employment Details</h2>
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
                                                <label for="experience">Total Work Experience<a class="text-danger">*</a></label>
                                                <spring:input type="number" class="form-control" id="experience"  path="totalWorkExperience"/>
                                                <spring:errors path = "totalWorkExperience" cssClass = "error" style = "color:red"></spring:errors>
                                            </div>
                                        </div>

                                        <div class="row ">
                                             <div class="form-group col-sm-3">
                                                <label for="occupationName">Organisation Name<a class="text-danger">*</a></label>
                                                <spring:input type="text" class="form-control" id="organizationName" path="organizationName"/>
                                                <spring:errors path = "organizationName" cssClass = "error" style = "color:red"></spring:errors>
                                            </div>
                                        </div>



                        </section>
                        <hr>
             <h2 class="mb-lg-3">Address Details</h2>
                                <div class="row ">
                                    <div class="form-group col-sm-3">
                                        <label for="houseNo">House No<a class="text-danger">*</a></label>
                                        <spring:input type="number" class="form-control" id="houseNo" path="add.houseNo"/>
                                           <spring:errors path = "add.houseNo" cssClass = "error" style = "color:red"></spring:errors>
                                    </div>

                                    <div class="form-group col-sm-3 offset-4">
                                        <label for="country">Country<a class="text-danger">*</a></label>
                                        <spring:select class="form-control option" id="country1" path="add.country">
                                        <spring:option value="-"  disabled="${'true'}" selected="true" label="Select one Country"/>
                                        </spring:select>
                                    </div>
                                </div>

                                <div class="row ">
                                    <div class="form-group col-sm-3">
                                        <label for="state">State<a class="text-danger">*</a></label>
                                        <spring:select class="form-control" id="state1" path="add.state">
                                        <spring:option value="-"  disabled="${'true'}" selected="true" label="Select Country First"/>

                                        </spring:select>
                                    </div>
                                     <div class="form-group col-sm-3 offset-4">
                                        <label for="city">City<a class="text-danger">*</a></label>
                                        <spring:select class="form-control" id="city1" path="add.city">
                                        <spring:option value="-"  disabled="${'true'}" selected="true" label="Select State First"/>
                                        </spring:select>
                                    </div>
                                </div>

                                <div class="row ">
                                   <div class="form-group col-sm-3">
                                        <label for="pinCode">Pin Code<a class="text-danger">*</a></label>
                                        <spring:input type="number" class="form-control" id="pinCode" path="add.pinCode"/>
                                        <spring:errors path = "add.pinCode" cssClass = "error" style = "color:red"></spring:errors>
                                    </div>
                                </div>

            <hr>
            <div class="text-center">
                <input class="btn-primary mr-3" type="submit" onclick="return validateCustomer()" value="Next" name="submit" >
           </div>
        </spring:form>
    </article>
</body>

</html>