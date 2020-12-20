<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<--temporary file -- testing purpose-->


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <form:form method="post" modelAttribute="customer">
        enter customer code: <form:input type="text" path="customerCode"/><br><br>
        enter first name:<form:input type="text" path="firstName"/><br><br>
        enter last name: <form:input type="text" path="lastName"/><br><br>
        enter dob :  <form:input type="text" path="dateOfBirth"/><br><br>
        enter nationality: <form:input type="text" path="nationality"/><br><br>
        enter experience: <form:input type="number" path="totalWorkExperience"/><br><br>
        enter occupation type: <form:input type="text" path="occupationType"/><br><br>
        enter organisation name : <form:input type="text" path="organizationName"/><br><br>

        <input type="submit">
    </form:form>
</body>
</html>