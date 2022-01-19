<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<petclinic:layout pageName="players">

    
    <div class="bodyBackground">
        <h2>Find Players</h2>
    
        
        <form:form modelAttribute="userDwarf" action="/userDwarf/player" method="get" class="form-horizontal"
                   id="search-user-form">
            <div class="form-group">
                <div class="col-sm-1">
                    <button type="submit" class="btn btn-default">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        &nbsp;&nbsp;Search</button>
                </div>
                <div class="control-group" id="username">
                    <div class="col-sm-10">
                        <form:input class="form-control" placeholder="Username" path="username" size="30" maxlength="80"/>
                        <span class="help-inline"><form:errors path="*"/></span>
                    </div>
                </div>
                
            </div>
            <div id="fondo4"></div>
    
        </form:form>

    </div>

    <br/> 
	
</petclinic:layout>
