<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<petclinic:layout pageName="usersDwarf">

    
    <div class="bodyBackground">
        <h2>Find Users</h2>
    
        
        <form:form modelAttribute="userDwarf" action="/usersDwarf/player" method="get" class="form-horizontal"
                   id="search-user-form">
            <div class="form-group">
                <div class="col-sm-1">
                    <button type="submit" class="btn btn-default">Search</button>
                </div>
                <div class="control-group" id="username">
                    <div class="col-sm-10">
                        <form:input class="form-control" placeholder="Username" path="username" size="30" maxlength="80"/>
                        <span class="help-inline"><form:errors path="*"/></span>
                    </div>
                </div>
                
            </div>
            <div id="fondo2"></div>
    
        </form:form>

    </div>

    <br/> 
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/usersDwarf/new" htmlEscape="true"/>'>Add User</a>
	</sec:authorize>
	
</petclinic:layout>
