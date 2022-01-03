<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/errorDwarf.jpg" var="petsImage"/>
    <img src="${petsImage}"/>

    <div>
        <br>
        <h2>Ha habido un error. Cuenta con mi hacha...</h2>
    </div>

    <p>${exception.message}</p>

</petclinic:layout>
