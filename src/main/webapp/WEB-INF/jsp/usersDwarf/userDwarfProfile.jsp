<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="profile">
    <h2>Datos de usuario:</h2>

    <table class="table table-striped">
        <tr> 
            <th>Username</th>
            <td><b><c:out value="${wrapper.userDwarf.username}"/></b></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${wrapper.userDwarf.email}"/></td>
        </tr>
        <tr>
            <th>Active</th>
            <td><c:out value="${wrapper.userDwarf.active}"/></td>
        </tr>
        <tr>
            <th>Roles</th>
            <td><c:out value="${wrapper.roles}"/></td>
        </tr>
    </table>
    <h2>Achievements</h2>


    <table class="table table-striped">
        <tr>
            <th>Description</th>
            <td><c:out value="${achievements.description}"/></td>
        </tr>
    </table>


    <br/>
    <br/>
    <br/>
</petclinic:layout>

