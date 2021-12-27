<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usersDwarf">
    <h2>Usuarios</h2>

    <table id="usersDwarfTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Username</th>
            <th style="width: 200px;">Email</th>
            <th style="width: 120px">Pass</th>
            <th style="width: 120px">Active</th>
            <th style="width: 120px;">Delete</th> 
            <th style="width: 120px;">Edit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usersDwarf}" var="userD">
            <tr>
                <td>
                    
                   <c:out value="${userD.username}"/>
                </td>
                <td>
                    <c:out value="${userD.email}"/>
                </td>
                <td>
                    <c:out value="${userD.pass}"/>
                </td>
                <td>
                    <c:out value="${userD.active}"/>
                </td>    
                <td>
                    <spring:url value="/usersDwarf/{userDwarfId}/delete" var="deleteUrl">
                        <spring:param name="userDwarfId" value="${userD.id}"/>
                    </spring:url>

                    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete</a>
                </td>        
                <td>
                    <spring:url value="{userDwarfId}/edit" var="editUrl">
                        <spring:param name="userDwarfId" value="${userD.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit User</a>
                </td>
               
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
