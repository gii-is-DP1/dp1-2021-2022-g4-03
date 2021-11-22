<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<petclinic:layout pageName="statistics">

    <h2>Statistics</h2>

    <table class="table table-striped">
        <tr> 
            <th>Time Played</th>
        </tr>
        <tr>
            <th>Games Played</th>
        </tr>
        <tr>
            <th>Games Won</th>
        </tr>
        <tr>
            <th></th>
        </tr>
        <tr>
            <th>Total Iron</th>
        </tr>
        <tr>
            <th>Total Gold</th>
        </tr>
        <tr>
            <th>Total Steel</th>
        </tr>
        <tr>
            <th>Total Objects</th>
        </tr>
        <tr>
            <th>Total Medals</th>
        </tr>
    </table>
	
</petclinic:layout>
