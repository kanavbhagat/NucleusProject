<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container-fluid mt-2">
		<div class="row justify-content-center">
            <img src="<c:url value="/images/logo.png" />" alt="logo" />
        </div>
	</div>

	<hr style="width: 98%">

	<div class="container-fluid">
	  <div class="row pt-5 ">
	    <!-- <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div> -->
	    <div class="col-md-12">
	      <div class="login d-flex ">
	        <div class="container">
	          <div class="row">
	            <div class="col-md-5 col-lg-4 mx-auto ">
	            	<div class="d-flex justify-content-center">
	            		<h3 class="login-heading mb-4" >Login</h3>
	            	</div>

	            	<c:url var="loginUrl" value="/login" />
	              	<form action="${loginUrl}" method="post">

	              	    <div class="d-flex justify-content-center">
                            <c:if test="${param.error != null}">
                                <p class="lead mt-4 " style="border:1px; border-style:solid;border-radius: 10px; border-color:rgba(255,138,128 ,1); padding: 5px;background-color:rgba(255,138,128 ,0.1);width: 80%; font-size: 15px">
                                    Invalid username and password.  </p>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <p class="lead mt-4 " style="border:1px; border-style:solid;border-radius: 10px; border-color:rgba(141, 181, 150, 0.8); padding: 5px;background-color:rgba(141, 181, 150, 0.1);width: 80%; font-size: 15px">
                                    You have been logged out successfully.</p>
                            </c:if>
          				</div>


		                <div class="form-label-group">
		                  <input type="text" id="username" name="user" class="form-control" placeholder="Username" required autofocus>
		                  <label for="username">User Name</label>
		                </div>

		                <div class="form-label-group">
		                  <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
		                  <label for="password">Password</label>
		                </div>

                        <%--
		                <div class="custom-control custom-checkbox mb-3">
		                  <input type="checkbox" class="custom-control-input" name="remember" id="customCheck1">
		                  <label class="custom-control-label" for="customCheck1">Remember password</label>
		                </div>
		                --%>

		                <input type="hidden" name="${_csrf.parameterName}"
		                			value="${_csrf.token}" />

		                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">Log in</button>

                        <%--
		                <div class="text-center">
		                  <a class="small" href="#">Forgot password?</a></div>--%>
	              	</form>


	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>

</body>
<style type="text/css">
	:root {
	  --input-padding-x: 1.5rem;
	  --input-padding-y: 0.75rem;
	}

	.login,
	.image {
	  min-height: 100vh;
	}

	.bg-image {
	  background-image: url('https://source.unsplash.com/WEQbe2jBg40/600x1200');
	  background-size: cover;
	  background-position: center;
	}

	.login-heading {
	  font-weight: 350;
	}

	.btn-login {
	  font-size: 0.9rem;
	  letter-spacing: 0.05rem;
	  padding: 0.75rem 1rem;
	  border-radius: 2rem;
	}

	.form-label-group {
	  position: relative;
	  margin-bottom: 1rem;
	}

	.form-label-group>input,
	.form-label-group>label {
	  padding: var(--input-padding-y) var(--input-padding-x);
	  height: auto;
	  border-radius: 2rem;
	}

	.form-label-group>label {
	  position: absolute;
	  top: 0;
	  left: 0;
	  display: block;
	  width: 100%;
	  margin-bottom: 0;
	  /* Override default `<label>` margin */
	  line-height: 1.5;
	  color: #495057;
	  cursor: text;
	  /* Match the input under the label */
	  border: 1px solid transparent;
	  border-radius: .25rem;
	  transition: all .1s ease-in-out;
	}

	.form-label-group input::-webkit-input-placeholder {
	  color: transparent;
	}

	.form-label-group input:-ms-input-placeholder {
	  color: transparent;
	}

	.form-label-group input::-ms-input-placeholder {
	  color: transparent;
	}

	.form-label-group input::-moz-placeholder {
	  color: transparent;
	}

	.form-label-group input::placeholder {
	  color: transparent;
	}

	.form-label-group input:not(:placeholder-shown) {
	  padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
	  padding-bottom: calc(var(--input-padding-y) / 3);
	}

	.form-label-group input:not(:placeholder-shown)~label {
	  padding-top: calc(var(--input-padding-y) / 3);
	  padding-bottom: calc(var(--input-padding-y) / 3);
	  font-size: 12px;
	  color: #777;
	}

	/* Fallback for Edge
	-------------------------------------------------- */

	@supports (-ms-ime-align: auto) {
	  .form-label-group>label {
	    display: none;
	  }
	  .form-label-group input::-ms-input-placeholder {
	    color: #777;
	  }
	}

	/* Fallback for IE
	-------------------------------------------------- */

	@media all and (-ms-high-contrast: none),
	(-ms-high-contrast: active) {
	  .form-label-group>label {
	    display: none;
	  }
	  .form-label-group input:-ms-input-placeholder {
	    color: #777;
	  }
	}

</style>
</html>