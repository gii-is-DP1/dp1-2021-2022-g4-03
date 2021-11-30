<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">

    <h2>Achievement Information</h2>


    <table class="table table-striped">
        <tr> 
            <th>Condition</th>
            <td><b><c:out value="${achievements.condition}"/></b></td>
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
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Achievement</a>

    <spring:url value="/achievements/{achievementId}/delete" var="deleteUrl">
        <spring:param name = "achievementId" value = "${achievements.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete</a>
    

    <br/>
    <br/>
    <br/>

</petclinic:layout>
