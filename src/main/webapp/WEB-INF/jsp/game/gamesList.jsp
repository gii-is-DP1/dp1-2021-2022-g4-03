<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usersDwarf">
    <div class="bodyBackground">

        <div class="row">
            <div class="col-sm-12">
    
                <h2>Active Games</h2>
    
                <table id="gamesTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th style="width: 50px;">Game</th>
                            <th style="width: 100px;">Number of Players</th>
                            <th style="width: 100px;">Player 1</th>
                            <th style="width: 100px;">Player 2</th>
                            <th style="width: 100px;">Player 3</th>
                            <th style="width: 100px;">Ronda</th>                         
                            <th style="width: 100px">Join</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${games}" var="game">
                            <tr>
                                <td>
                                    <c:out value="${game.id}"/>
                                </td>
                                <td>
                                    <c:out value="${game.numberOfPlayers}"/>/3
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
                                    <c:out value="${game.round}"/>
                                </td>
                                <td>
                                    <spring:url value="/game/connect/{gameId}" var="joinUrl">
                                        <spring:param name="gameId" value="${game.id}"/>
                                    </spring:url>
                                    <a href="${fn:escapeXml(joinUrl)}" class="btn btn-default">
                                        <span class="glyphicon glyphicon-play-circle" aria-hidden="true"></span>
                                        &nbsp;Join
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-10">
                <a class="btn btn-default" href='<spring:url value="/game/new" htmlEscape="true"/>'>
                    &nbsp;New Game and Play
                </a>
            </div>
        </div>
        <div id="fondo3"></div>
    </div>
    
</petclinic:layout>