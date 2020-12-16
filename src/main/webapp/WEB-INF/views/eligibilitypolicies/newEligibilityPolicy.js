$(document).ready(function() {
var eligibilityParameterCount = 1;
$('#addButton').click(function() {
   $('tbody').append('<tr class="d-flex"><td class="col-7" style="text-align:center;"><form:select path="" class="custom-select"><c:forEach items="${eligibilityParameterList}" var="eligibilityParameter"><form:option value="${eligibilityParameter.parameterName}"/></c:forEach></form:select></td><td class="col-5" style="text-align:center;"><form:textarea path="" class="form-control" disabled="true"/> </td></tr>');
});
});