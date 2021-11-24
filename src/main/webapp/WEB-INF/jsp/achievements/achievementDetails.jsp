<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<petclinic:layout pageName="achievements">

    <h2><fmt:message key="achievementInformation"/></h2>


    <table class="table bg-warning">
        <tr>
            <th><fmt:message key="description"/></th>
            <td><b><c:out value="${achievements.description}"/></b></td>
        </tr>
        <tr>
            <th><fmt:message key="condition"/></th>
            <td><c:out value="${achievements.condition}"/></td>
        </tr>
        <tr>
            <th><fmt:message key="last_change"/></th>
            <td><c:out value="${achievements.last_change}"/></td>
        </tr>
    </table>

    <spring:url value="{achievementsId}/edit" var="editUrl">
        <spring:param name="achievementsId" value="${achievements.id}"/>
    </spring:url>

    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default"><fmt:message key="editAchievements"/></a>
 
        <spring:url value="delete/{achievementsId}" var="deleteUrl">
        <spring:param name="achievementsId" value="${achievements.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default"><fmt:message key="deleteAchievements"/></a>

</petclinic:layout>
