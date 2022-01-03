<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="home">

    <div class="bodyBackground">

        <div class="row" style="height: 50px;">
        </div>
        
        <div class="row">
            <div class="col-sm-4">
                <table class="table table-striped">
                    <caption>Podium</caption>
                    <tr>
                        <th><img src="../resources/images/copa-oro.png" height ="50" width="50"/></th>
                        <td><b><c:out value="${p1}"/></b></td>
                        <td><c:out value="${p1wg} victorias"/></td>
                    </tr>
                    <tr>
                        <th><img src="../resources/images/copa-plata.png" height ="50" width="50"/></th>
                        <td><b><c:out value="${p2}"/></b></td>
                        <td><c:out value="${p2wg} victorias"/></td>
                    </tr>
                    <tr>
                        <th><img src="../resources/images/copa-bronce.png" height ="50" width="50"/></th>
                        <td><b><c:out value="${p3}"/></b></td>
                        <td><c:out value="${p3wg} victorias"/></td>
                    </tr>
                </table>
            </div>
            <div class="col-sm-1">
            </div>
            <div class="col-sm-7">
                <table class="table table-striped">
                    <caption>Global Statistics</caption>
                    <tr>
                        <th>Global time played:</th>
                        <td><c:out value="${ttp}"/></td>
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
                        <th>Global iron mined:</th>
                        <td><c:out value="${ti}"/></td>
                    </tr>
                    <tr>
                        <th>Global gold mined:</th>
                        <td><c:out value="${to}"/></td>
                    </tr>
                    <tr>
                        <th>Global steel forged:</th>
                        <td><c:out value="${ts}"/></td>
                    </tr>
                    <tr>
                        <th>Global objects acquired:</th>
                        <td><c:out value="${tob}"/></td>
                    </tr>
                    <tr>
                        <th>Global medal acquired:</th>
                        <td><c:out value="${tm}"/></td>
                    </tr>
                </table>
            </div>
        </div>  

        <div id="fondo1"></div>
    </div>

</petclinic:layout>