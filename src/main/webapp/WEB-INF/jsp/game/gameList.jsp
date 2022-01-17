<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="dwarf" tagdir="/WEB-INF/tags" %>

<dwarf:layout pageName="games">
    <h2>Games</h2>

    <table id="gamesTable" class="table table-striped">
        <thead>
        <tr>
         	<th style="width: 150px;">ID</th>
            <th style="width: 150px;">Player 1</th>
            <th style="width: 150px;">Player 2</th>
            <th style="width: 150px;">Player 3</th>
            <th style="width: 150px;">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
             	<td>
                    <c:out value="${game.id}"/>
                </td>
                <td>
                    <c:out value="${game.player0.username}"/>
                </td>
                <td>
                    <c:out value="${game.player1.username}"/>
                </td>
                <td>
                    <c:out value="${game.player2.username}"/>
                </td>
                 
				<td> 
					
                    <spring:url value="/game/{gameId}/delete" var="gameUrl">
                        <spring:param name="gameId" value="${game.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(gameUrl)}"><span class=" glyphicon glyphicon-trash" aria-hidden="true"></span>
                    <span>Delete</span></a>
                    
				</tr>
               
            </tr>
            
        </c:forEach>
        </tbody>
    </table>

	
</dwarf:layout>