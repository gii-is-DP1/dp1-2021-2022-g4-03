<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <h2>Achievements</h2>

    <table id="AchievementsTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Description</th>
            <th style="width: 200px;">Condition</th>
            <th style="width: 120px">Last Change</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="achievements">
            <tr>
                <td>
                    <spring:url value="/achievements/{achievementsId}" var="achievementsUrl">
                        <spring:param name="achievementsId" value="${achievements.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(achievementsUrl)}"><c:out value="${achievements.description}"/></a>
                </td>
                <td>
                    <c:out value="${achievements.condition}"/>
                </td>
                <td>
                    <c:out value="${achievements.last_change}"/>
                </td>        
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
