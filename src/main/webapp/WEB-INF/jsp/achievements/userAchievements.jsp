<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="userAchievements">

    <div class="bodyBackground">
    
        <h2>Perosnal Achievements</h2>
    
        <table id="UserAchievementsTable" class="table table-striped">
            <thead>
            <tr>
                <th style="width: 150px;">Description</th>
                <th style="width: 120px">Progress</th>
                <th style="width: 200px;">Obtaining Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${selections}" var="achievements">
                <tr>
                    <td>
                        <spring:url value="/achievements/{achievementsId}" var="achievementsUrl">
                            <spring:param name="achievementsId" value="${achievements.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(achievementsUrl)}"><c:out value="${userAchievements.achievements.description}"/></a>
                    </td>
                    <td>
                        <c:out value="${achievements.condition}"/>
                    </td>
                    <td>
                        <c:out value="${userAchievements.obtainingDate}"/>
                    </td>        
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="fondo7"></div>
</petclinic:layout>