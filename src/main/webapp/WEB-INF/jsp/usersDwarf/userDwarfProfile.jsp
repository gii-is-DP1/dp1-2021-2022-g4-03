<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="profile">
    <h2>Datos de usuario:</h2>

    <table class="table table-striped" style="width: 50%;">
        <tr> 
            <th>Username</th>
            <td><b><c:out value="${wrapper.userDwarf.username}"/></b></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${wrapper.userDwarf.email}"/></td>
        </tr>
        <tr>
            <th>Roles</th>
            <td><c:out value="${wrapper.roles}"/></td>
        </tr>
    </table>
    <h2>Statistics</h2>


    <table class="table table-striped" >
        <tr>
            <th>Time Played:</th>
            <td><c:out value="${statistic.getTimeFormatted()}"/></td>
        </tr>
        <tr>
            <th>Games played:</th>
            <td><c:out value="${statistic.gamesPlayed}"/></td>
        </tr>
        <tr>
            <th>Games won:</th>
            <td><c:out value="${statistic.gamesWon}"/></td>
        </tr>
        <tr>
            <th>Total iron:</th>
            <td><c:out value="${statistic.totalIron}"/></td>
        </tr>
        
        <tr>
            <th>Total gold:</th>
            <td><c:out value="${statistic.totalGold}"/></td>
        </tr>
        
        <tr>
            <th>Total steel:</th>
            <td><c:out value="${statistic.totalSteel}"/></td>
        </tr>
        
        <tr>
            <th>Total object:</th>
            <td><c:out value="${statistic.totalObject}"/></td>
        </tr>
        
        <tr>
            <th>Total medal:</th>
            <td><c:out value="${statistic.totalMedal}"/></td>
        </tr>


    </table>

    <h2>Global</h2>
    <table class="table table-striped" >
        <tr>
            <th>Global time played:</th>
            <td><c:out value="${timePlayed}"/></td>
        </tr>
        <tr>
            <th>Global games played:</th>
            <td><c:out value="${tgp}"/></td>
        </tr>
        <tr>
            <th>Global games won:</th>
            <td><c:out value="${tgw}"/></td>
        </tr>
        <tr>
            <th>Global iron:</th>
            <td><c:out value="${ti}"/></td>
        </tr>
        <tr>
            <th>Global gold:</th>
            <td><c:out value="${to}"/></td>
        </tr>
        <tr>
            <th>Global steel:</th>
            <td><c:out value="${ts}"/></td>
        </tr>
        <tr>
            <th>Global objects:</th>
            <td><c:out value="${tob}"/></td>
        </tr>
        <tr>
            <th>Global medal:</th>
            <td><c:out value="${tm}"/></td>
        </tr>
    </table>


    <br/>
    <br/>
    <br/>
</petclinic:layout>

