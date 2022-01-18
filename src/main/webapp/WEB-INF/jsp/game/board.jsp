<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="board" tagdir="/WEB-INF/tags" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->


<petclinic:layout pageName="board">
    <jsp:attribute name="customHeader">
        <script src="/resources/js/board.js"></script>
    </jsp:attribute>
    <jsp:body>

        <c:if test="${game.phase == 'ASIGNACION'}">
		<h2><c:out value="Turno para: Jugador ${game.activePlayer}"/></h2>
        </c:if>
        <h2><c:out value="Fase de la ronda: ${game.phase}"/></h2>

        <div class="row">

            <div class="game-board">
                <div class="cardCell">
                    <button id="card1" value="0"><img width="246" height="336" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card2" value="1"><img width="246" height="336" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card3" value="2"><img width="246" height="336" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card4" value="3"><img width="246" height="336" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card5" value="4"><img width="246" height="336" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card6" value="5"><img width="246" height="336" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card7" value="6"><img width="246" height="336" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card8" value="7"><img width="246" height="336" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card9" value="8"><img width="246" height="336" src="/resources/cards/cardback.png"></button>
                </div>
              </div> 

            <div class="col-sm-offset-2 col-sm-10">
                <a class="btn btn-default"
                   href='<spring:url value="/game/${game.id}/surrender" htmlEscape="true"/>'>Rendirse</a>
            </div>
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default" id="startButton">Start Game</button>
            </div>
            <script>
                let gameId= ${game.id};
            </script>

            <form action="/api/game/${game.id}" method="post" id="testForm">
                <input type="hidden" value="board" name="viewName">
                <button class="btn btn-primary" type="submit">Test</button>
            </form>

        </div>

        <div id="fondo7"></div>
    </jsp:body>

    

</petclinic:layout>
