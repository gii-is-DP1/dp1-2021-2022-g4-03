<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">

    <h2>Información de Usuarios</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre de usuario</th>
            <td><b><c:out value="${users.nickname}"/></b></td>
        </tr>
        <tr>
            <th>Contraseña</th>
            <td><c:out value="${users.pass}"/></td>
        </tr>
    </table>

</petclinic:layout>