<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="profile">

    <div class="bodyBackground">

        <div class="row">
            
            <div class="col-md-3">
                <img src="/resources/images/profilepic.jpg" alt="${wrapper.userDwarf.username}" class="img-circle img-profile" style="height: 180px; width: 180px;">            
            </div>
            <div class = "col-md-6">
                <table class="table table-striped" style="width: 80%;">
                    <caption>Datos de usuario:</caption>
                    <tr> 
                        <th>Username</th>
                        <td><b><c:out value="${wrapper.userDwarf.username}"/></b></td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><c:out value="${wrapper.userDwarf.email}"/></td>
                    </tr>
                    <tr>
                        <th> Password: <input type="checkbox" onclick="myFunction()"> <br><br></th>
                        <td><input type="password" value="${wrapper.userDwarf.pass}" id="myInput" readonly></td>
                    </tr>
                </table>
            </div>
            <div class="col-md-2">
                <spring:url value="/profile/achievements" var="pA">
                                <spring:param name="pA" value="${wrapper.userDwarf.id}"/>
                            </spring:url>
                <a href="${fn:escapeXml(pA)}" class="btn btn-default btn-profile-lg">Logros</a>
                
            </div>

        </div>

        <br>
        <br>
        <table class="table table-striped" >
            <caption>Your Statistics</caption>
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
        <div id="fondo7"></div>

    </div>

    <script>
        function myFunction() {
        var x = document.getElementById("myInput");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
        }
    </script>

    <br/>
    <br/>
    <br/>
</petclinic:layout>

