<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usersDwarf">

    <div class="bodyBackground">

        <table class="table table-striped">
            <caption>User Information</caption>
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
    
        <spring:url value="{userDwarfId}/edit" var="editUrl">
            <spring:param name="userDwarfId" value="${wrapper.userDwarf.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            &nbsp;Edit
        </a>
    
        
        <spring:url value="{userDwarfId}/delete" var="deleteUrl">
            <spring:param name="userDwarfId" value="${wrapper.userDwarf.id}"/>
        </spring:url>
    
        <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">
            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
            &nbsp;Delete
        </a>
    
        <br/>
        <br/>
        <br/>
        <div id="fondo2"></div>
    </div>

</petclinic:layout>
