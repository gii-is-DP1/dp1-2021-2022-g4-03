<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="home">

    <div class="bodyBackground">
        <div class="col-sm-offset-2 col-sm-10">
            <a class="btn btn-default"
                href='<spring:url value="/Aboutus" htmlEscape="true"/>'>About us</a>
        </div>
        <div id="fondo1"></div>
    </div>
</petclinic:layout>