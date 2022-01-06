<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">

    <div class="bodyBackground">
    
        <h2>
            <c:if test="${achievements['new']}">New </c:if> Achievement
        </h2>
        <form:form modelAttribute="achievements" class="form-horizontal" id="add-achievement-form">
            <div class="panel form-square">  
                <div class="form-group has-feedback">
                    <input type="hidden" name="pic" value=${achievements.pic} >
                    <petclinic:inputField label="Condition" name="condition"/>
                    <petclinic:inputField label="Description" name="description"/>
                    <petclinic:inputField label="Last Change" name="lastChange"/>
                </div>
            
                <div class="form-group">
                    <div class="col-sm-12 text-center">
                        <c:choose>
                            <c:when test="${achievements['new']}">
                                <button class="btn btn-default" type="submit">Add</button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-default" type="submit">Update</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </form:form>
    </div>

    <div id="fondo6"></div>
</petclinic:layout>
