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
		<h2><c:out value="Turno para: Jugador ${game.activePlayer+1}"/></h2>
        </c:if>
        <h2><c:out value="Fase de la ronda: ${game.phase}"/></h2>
        
        <h2>
            <c:out value="${game.board.cardCells[0].cards[0]}"/>
            <c:out value="${game.board.cardCells[1].cards[0]}"/>
        </h2>

        <div class="board">

            <div class="game-board">
                <div class="cardCell">
                    <button id="card1" value="0">
                        <img id= "imagenCarta1" width="123" height="168" src="/resources/cards/cardback.png">
                        <script>
                            let imagenCarta1 = document.getElementById("imagenCarta1");
                            imagenCarta1.src = getCard(game.board.cardCells[0].cards[0]).cardImage;
                        </script>
                    </button>
                    <img class="worker" width="75" height="75" src="/resources/workers/Dwarf1.png" />
                </div>
                <div class="cardCell">
                    <button id="card2" value="1"><img width="123" height="168" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card3" value="2"><img width="123" height="168" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card4" value="3"><img width="123" height="168" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card5" value="4"><img width="123" height="168" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card6" value="5"><img width="123" height="168" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card7" value="6"><img width="123" height="168" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card8" value="7"><img width="123" height="168" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card9" value="8"><img width="123" height="168" src="/resources/cards/cardback.png"></button>
                </div>

                <div class="cardCell">
                    <button id="card10" value="9"><img width="123" height="168" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card11" value="10"><img width="123" height="168" src="/resources/cards/cardback.png"></button>
                </div>
                <div class="cardCell">
                    <button id="card12" value="11"><img width="123" height="168" src="/resources/cards/cardback.png"></button>
                </div>

            </div> 
                

            <div class="player-grid">
                <div class="player">
                    <img width="75" height="75" src="/resources/workers/Dwarf1.png" />
                    <b>Iron: <c:out value="${game.playerState_0.iron}"/></b>
                    <b>Gold: <c:out value="${game.playerState_0.gold}"/></b>
                    <b>Steel: <c:out value="${game.playerState_0.steel}"/></b>
                    <b>Objects: <c:out value="${game.playerState_0.object}"/></b>
                    <b>Medals: <c:out value="${game.playerState_0.medal}"/></b>
                </div>
                <div class="player">
                    <img width="75" height="75" src="/resources/workers/Dwarf2.png" />
                    <b>Iron: <c:out value="${game.playerState_1.iron}"/></b>
                    <b>Gold: <c:out value="${game.playerState_1.gold}"/></b>
                    <b>Steel: <c:out value="${game.playerState_1.steel}"/></b>
                    <b>Objects: <c:out value="${game.playerState_1.object}"/></b>
                    <b>Medals: <c:out value="${game.playerState_1.medal}"/></b>
                </div>
                <div class="player">
                    <img width="75" height="75" src="/resources/workers/Dwarf3.png" />
                    <b>Iron: <c:out value="${game.playerState_2.iron}"/></b>
                    <b>Gold: <c:out value="${game.playerState_2.gold}"/></b>
                    <b>Steel: <c:out value="${game.playerState_2.steel}"/></b>
                    <b>Objects: <c:out value="${game.playerState_2.object}"/></b>
                    <b>Medals: <c:out value="${game.playerState_2.medal}"/></b>
                </div>
            </div>

        <div id="fondo7"></div>

        <div class="col-sm-offset-2 col-sm-10">
            <a class="btn btn-default"
               href='<spring:url value="/game/${game.id}/surrender" htmlEscape="true"/>'>Rendirse</a>
        </div>

        <input type="hidden" id="gameId" value=${game.id}>
        <input type="hidden" id="currentUser" value=${currentUser}>

    </jsp:body>

    

</petclinic:layout>
