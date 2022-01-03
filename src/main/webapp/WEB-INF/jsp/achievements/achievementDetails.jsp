<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">

    <div class="bodyBackground">
    
        <table class="table table-striped">
            <caption>Achievement Information</caption>
            <tr> 
                <th>Condition</th>
                <td><c:out value="${achievements.condition}"/></td>
            </tr>
            <tr>
                <th>Last Change</th>
                <td><c:out value="${achievements.lastChange}"/></td>
            </tr>
            <tr>
                <th>Description</th>
                <td><c:out value="${achievements.description}"/></td>
            </tr>
        </table>
    
        <spring:url value="{achievementId}/edit" var="editUrl">
            <spring:param name="achievementId" value="${achievements.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            &nbsp;Edit
        </a>
    
        <spring:url value="/achievements/{achievementId}/delete" var="deleteUrl">
            <spring:param name = "achievementId" value = "${achievements.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">
            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
            &nbsp;Delete
        </a>
    
        <spring:url value="/achievements/list" var="AchievementListUrl">
            <spring:param name = "achievementId" value = "${achievements.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(AchievementListUrl)}" class="btn btn-default">
            <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
            &nbsp;List
        </a>
    </div>
    
    <div id="fondo6"></div>

    <br/>
    <br/>
    <br/>

</petclinic:layout>
