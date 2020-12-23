<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Edit Eligibility Policy</title>
<style>
    .table {
            width:80%;
        }
    .required:after {
        content:" *";
        color: red;
    }
    .error-messages {
        color:red;
    }
</style>

</head>
<body>
<jsp:include page="/navbar.jsp" />
    <div class="container-fluid">
	<div class="row pt-3 pl-3">
		<h2 class="display-3" style="font-size: 30px">
			<b>Create Eligibility Policies</b>
		</h2>
	</div>
	<hr width="" color="#b3b3b3">
    </div>

    <!-- Placeholders  -->
    	<div class="container-fluid">
    		<form:form method = "post" action="addEdited" modelAttribute = "eligibilityPolicy">
    			<!-- Section 1 -->
    		    <div class="form-group row pt-2 pl-3">
    			      <div class="col-md-3">
    			        <label class="required" for="policyName" >Eligibility Policy Code</label><br>
    					<form:input path="policyCode" type="text" class="form-control" id="policyCode" name="policyCode" required="required" readonly="true"/>
    			        <form:errors path="policyCode" class="error-messages"/>
    			      </div>

    			      <div class="col-md-3">
    			      </div>

    			      <div class="col-md-3">
    			        <label class="required" for="policyName">Eligibility Policy Name</label><br>
    					<form:input path="policyName" type="text" id="policyName" name="policyName" class="form-control" required="required"/>
    			        <form:errors path="policyName" class="error-messages"/>
    			      </div>
    		    </div>

    		    <div class="row pt-2 pl-3">
    				<div class="col-md-3">
    					<label for="policyDescription">Eligibility Policy Description</label><br>
    					<form:textarea path="policyDescription" class="form-control"  id="policyDescription" name="policyDescription"/>
    				    <form:errors path="policyDescription" class="error-messages"/>
    				</div>
    			</div>
    			<br><br>

    			<!-- Section 2 -->
                <hr width="" color="#b3b3b3">
                <div class="row pt-3 px-3 d-flex justify-content-end">
                    <button type="button" id="addButton" class="btn btn-primary">Add Eligibility Parameter</button>
   				</div>
    			<div class="row pt-3 pl-3">
    				<p class="display-2" style="font-size: 22px;">
    					<b> Eligibility Parameters </b>
    				</p>
    			</div>

    			<div class="row pt-3 px-3">
    				<table class="table table-bordered">
    				    <thead>

    				      <tr class="d-flex">
    				        <th class="col-7" style="text-align:left;">Eligibility Parameter Name</th>
    				        <th class="col-5" style="text-align:left;">Description</th>
    				      </tr>
    				    </thead>
    				    <tbody id = "tableBody">

                          <tr class="d-flex" id = "tableRow0">
    				        <td class="col-7"  style="text-align:center;">
    				            <form:select path="eligibilityParameterCodes[0]" class="custom-select selectTags" multiple="false">
    				            <form:option label="${existingParameterList[0].parameterName}" value="${existingParameterList[0].parameterCode}" selected="true"/>
    				            <form:options items="${allEligibilityParameterList}" itemLabel="parameterName" itemValue="parameterCode"/>
                                </form:select>
                            </td>
    				        <td class="col-5" style="text-align:center;">
    				            <textarea id = "textarea0" class="form-control" disabled>${existingParameterList[0].parameterDescription}</textarea>
    				        </td>
    				      </tr>


                        <c:forEach items="${existingParameterList}" begin="1" var="existingParameter" varStatus="tagStatus">
    				      <tr class="d-flex" id = "tableRow${tagStatus.index}">
    				        <td class="col-7"  style="text-align:center;">
    				            <form:select path="eligibilityParameterCodes[${tagStatus.index}]" class="custom-select selectTags" multiple="false">
    				            <form:option value="-" label="Select One Option"/>
    				            <form:option label="${existingParameter.parameterName}" value="${existingParameter.parameterCode}" selected="true"/>
    				            <form:options items="${allEligibilityParameterList}" itemLabel="parameterName" itemValue="parameterCode"/>
                                </form:select>
                            </td>
    				        <td class="col-5" style="text-align:center;">
    				            <textarea id = "textarea${tagStatus.index}" class="form-control" disabled>${existingParameter.parameterDescription}</textarea>
    				        </td>
    				      </tr>
    				    </c:forEach>
    				    <c:forEach items="${allEligibilityParameterList}" begin="${existingParameterList.size()}" varStatus="tagStatus">
    				      <tr class="d-none" id = "tableRow${tagStatus.index}" style="display:none;">
    				        <td class="col-7"  style="text-align:center;">
    				            <form:select path="eligibilityParameterCodes[${tagStatus.index}]" class="custom-select selectTags" multiple="false">
    				            <form:option value="-" label="Select One Option" selected="true"/>
    				            <form:options items="${allEligibilityParameterList}" itemLabel="parameterName" itemValue="parameterCode"/>
                                </form:select>
                            </td>
    				        <td class="col-5" style="text-align:center;">
    				            <textarea id = "textarea${tagStatus.index}" class="form-control" disabled></textarea>
    				        </td>
    				      </tr>
    				    </c:forEach>
    				    </tbody>
    				  </table>

    			</div>
                <input type="hidden" id="eligibilityParametersCountInput" name="count"/>

                <c:forEach items="${allEligibilityParameterList}" var="eligibilityParam" varStatus="tagStatus">
                                <input type="hidden" class="allParameterCodes" value="${eligibilityParam.parameterCode}"/>
                                <input type="hidden" class="allParameterDescriptions" value="${eligibilityParam.parameterDescription}"/>
                </c:forEach>
                <hr width="" color="#b3b3b3">
    			<div class="row pt-3 px-3 d-flex justify-content-end">
                	<div class="px-2">
          				<input type="submit" class="btn btn-primary" name ="action" value="Save">
       				</div>
                	<div>
           				<input type="submit" class="btn btn-primary" name ="action" value="Save & Request Approval"/>
       				</div>
                </div>

        </form:form>
    </div>
</body>
<script>
$(document).ready(function() {

    var arrParamCodes = $(".allParameterCodes").map(function() {
        return $(this).val();
    });
    var arrParamDescriptions = $(".allParameterDescriptions").map(function() {
            return $(this).val();
    });


    var eligibilityParametersCount = ${existingParameterList.size()};
    if(eligibilityParametersCount == ${allEligibilityParameterList.size()}) {
        $('#addButton').css('display', 'none');
    }
    $('#eligibilityParametersCountInput').val(eligibilityParametersCount);

    $('#addButton').click(function() {
        $('#tableRow'+eligibilityParametersCount).css('display', 'block');
        $('#tableRow'+eligibilityParametersCount).addClass("d-flex").removeClass("d-none");
        eligibilityParametersCount = eligibilityParametersCount + 1;
        if(eligibilityParametersCount == ${allEligibilityParameterList.size()}) {
             $('#addButton').css('display', 'none');
        }
        $('#eligibilityParametersCountInput').val(eligibilityParametersCount);
    });

    $('.selectTags').change(function(){
        var desc = " ";
        for(var i=0; i<arrParamCodes.length; i++) {
            if(arrParamCodes[i] === $(this).val()) {
                desc = arrParamDescriptions[i];
            }
        }
        $(this).parent().siblings().children('textarea').val(desc);
    });
});
</script>
</html>