<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="home">

    <div class="bodyBackground">
        <div class="col-sm-12">
            <a class="btn btn-welcome"
                href='<spring:url value="/Aboutus" htmlEscape="true"/>'>About Us</a>
        </div>
        <div id="fondo1"></div>
    </div>
</petclinic:layout>