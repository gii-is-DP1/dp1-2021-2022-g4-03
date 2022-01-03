<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<petclinic:layout pageName="achievements">

    <div class="bodyBackground">

        <h2>Find Achievements</h2>
    
        <form:form modelAttribute="achievements" action="/achievements" method="get" class="form-horizontal"
                   id="search-achievements-form">
    
            <div class="form-group">
                <div class="col-sm-1">
                    <button type="submit" class="btn btn-default">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        &nbsp;&nbsp;Search</span>
                    </button>
                </div>
                <div class="control-group" id="userDwarf">
                    <div class="col-sm-10">
                        <form:input class="form-control" placeholder="condition" path="condition" size="30" maxlength="80"/>
                        <span class="help-inline"><form:errors path="*"/></span>
                        <br>
                        <div class="panel text-square">
                            <div class="panel-body">Si se hace una busqueda en blanco o incorrecta se muestra una lista completa de los logros</div>
                        </div>
                    </div>
                </div>            
            </div>
    
            <div id="fondo6"></div>
    
        </form:form>
    
        <br/> 
        <sec:authorize access="hasAuthority('admin')">
            <a class="btn btn-default" href='<spring:url value="/achievements/new" htmlEscape="true"/>'>
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                &nbsp;Add Achievement
            </a>
        </sec:authorize>
    </div>
	
</petclinic:layout>