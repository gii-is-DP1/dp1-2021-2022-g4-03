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

        <div class="board">

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
                <div>
                    <button id="noColocar" class="btn" value="12">No colocar</button>
                </div>
            </div>

            <div></div>

            <div class="game-board">
                <div class="cardCell">
                    <button id="card1" class="card-button" value="0">
                        <img id= "cell0" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cardCells[0].cards[0]}"></button>
                        <img id= "worker0" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>
                <div class="cardCell" >
                    <button id="card2" class="card-button" value="1">
                        <img id= "cell1" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cardCells[1].cards[0]}"></button>
                        <img id= "worker1" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>
                <div class="cardCell">
                    <button id="card3" class="card-button" value="2">
                        <img id= "cell2" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cardCells[2].cards[0]}"></button>
                        <img id= "worker2" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>
                <div class="cardCell">
                    <button id="card4" class="card-button" value="3">
                        <img id= "cell3" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cardCells[3].cards[0]}"></button>
                        <img id= "worker3" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>
                <div class="cardCell">
                    <button id="card5" class="card-button" value="4">
                        <img id= "cell4" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cardCells[4].cards[0]}"></button>
                        <img id= "worker4" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>
                <div class="cardCell">
                    <button id="card6" class="card-button" value="5">
                        <img id= "cell5" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cardCells[5].cards[0]}"></button>
                        <img id= "worker5" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>
                <div class="cardCell">
                    <button id="card7" class="card-button" value="6">
                        <img id= "cell6" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cardCells[6].cards[0]}"></button>
                        <img id= "worker6" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>
                <div class="cardCell">
                    <button id="card8" class="card-button" value="7">
                        <img id= "cell7" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cardCells[7].cards[0]}"></button>
                        <img id= "worker7" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>
                <div class="cardCell">
                    <button id="card9" class="card-button" value="8" >
                        <img id= "cell8" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cardCells[8].cards[0]}"></button>
                        <img id= "worker8" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>

                <div class="cardCell">
                    <button id="card10" class="card-button" value="9">
                        <img id= "cell9" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cartasAccionEspecial_0[0]}"></button>
                        <img id= "worker9" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>
                <div class="cardCell">
                    <button id="card11" class="card-button" value="10">
                        <img id= "cell10" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cartasAccionEspecial_1[0]}"></button>
                        <img id= "worker10" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>
                <div class="cardCell">
                    <button id="card12" class="card-button" value="11">
                        <img id= "cell11" width="123" height="168" src="/resources/cards/cardback.png" name="${game.board.cartasAccionEspecial_2[0]}"></button>
                        <img id= "worker11" class="worker" width="75" height="75" style="display: none;" src="/resources/workers/Dwarf1.png" />
                </div>

            </div> 

        <div id="fondo7"></div>

        <div class="col-sm-offset-2 col-sm-10">
            <a class="btn btn-default"
               href='<spring:url value="/game/${game.id}/surrender" htmlEscape="true"/>'>Rendirse</a>
        </div>

        <input type="hidden" id="gameId" value=${game.id}>
        <input type="hidden" id="currentUser" value=${currentUser}>

        <c:if test="${game.phase == 'ASIGNACION'}">
		<h2><c:out value="Turno para: Jugador ${game.activePlayer+1}"/></h2>
        </c:if>
        <h2><c:out value="Fase de la ronda: ${game.phase}"/></h2>

    </jsp:body>

    

</petclinic:layout>
