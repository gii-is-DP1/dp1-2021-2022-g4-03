<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="statistics">

    <div class="bodyBackground">

        <table class="table table-striped">
            <caption><c:out value="${statistics.userDwarf.username}"/> statistics:</caption>
            <tr> 
                <th>Time Played</th>
                <td><c:out value="${statistics.getTimeFormatted()}"/></td>
            </tr>
            <tr>
                <th>Games Played</th>
                <td><c:out value="${statistics.gamesPlayed}"/></td>
            </tr>
            <tr>
                <th>Games Won</th>
                <td><c:out value="${statistics.gamesWon}"/></td>
            </tr>
            
            <tr>
                <th>Total Iron</th>
                <td><c:out value="${statistics.totalIron}"/></td>
            </tr>
            <tr>
                <th>Total Gold</th>
                <td><c:out value="${statistics.totalGold}"/></td>
            </tr>
            <tr>
                <th>Total Steel</th>
                <td><c:out value="${statistics.totalSteel}"/></td>
            </tr>
            <tr>
                <th>Total Objects</th>
                <td><c:out value="${statistics.totalObject}"/></td>
            </tr>
            <tr>
                <th>Total Medals</th>
                <td><c:out value="${statistics.totalMedal}"/></td>
            </tr>
        </table>

        <div id="fondo5"></div>
    </div>
	
</petclinic:layout>
