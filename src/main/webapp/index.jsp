<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>NSBT Project Home</title>
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
    <jsp:include page="/navbar.jsp" />
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