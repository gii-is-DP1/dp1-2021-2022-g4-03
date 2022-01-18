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

        <c:if test="${phase == 'ASIGNACION'}">
		<h2><c:out value="Turno para: Jugador ${game.activeUser}"/></h2>
        </c:if>
        <h2><c:out value="Fase de la ronda: ${game.phase}"/></h2>

        <div class="row">
            <div class="col-md-12">
                <canvas id="canvas" width="${game.board.width}" height="${game.board.height}">
                    <img id="boardBackground" src="/${game.board.background}" style="display:none">
                </canvas>
                <form:form modelAttribute="clientData" action="api/game/${game.id}" method="post" accept-charset="UTF-8">
                    <input type="checkbox" id="card1" name="playerAction" value="0">
                    <label for="card1">Carta Montaña 1</label><br>
                    <input type="checkbox" id="card2" name="playerAction" value="1">
                    <label for="card2">Carta Montaña 2</label><br>
                    <input type="checkbox" id="card3" name="playerAction" value="2">
                    <label for="card3">Carta Montaña 3</label><br>
                    <input type="checkbox" id="card4" name="playerAction" value="3">
                    <label for="card4">Carta Montaña 4</label><br>
                    <input type="checkbox" id="card5" name="playerAction" value="4">
                    <label for="card5">Carta Montaña 5</label><br>
                    <input type="checkbox" id="card6" name="playerAction" value="5">
                    <label for="card6">Carta Montaña 6</label><br>
                    <input type="checkbox" id="card7" name="playerAction" value="6">
                    <label for="card7">Carta Montaña 7</label><br>
                    <input type="checkbox" id="card8" name="playerAction" value="7">
                    <label for="card8">Carta Montaña 8</label><br>
                    <input type="checkbox" id="card9" name="playerAction" value="8">
                    <label for="card9">Carta Montaña 9</label><br>
                    <input type="checkbox" id="card10" name="playerAction" value="9">
                    <label for="card10">Carta Accion Especial 1</label><br>
                    <input type="checkbox" id="card11" name="playerAction" value="10">
                    <label for="card11">Carta Accion Especial 2</label><br>
                    <input type="checkbox" id="card12" name="playerAction" value="11">
                    <label for="card12">Carta Accion Especial 3</label><br>
                    <input type="submit" value="Submit">
                  </form:form>
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
    </jsp:body>

</petclinic:layout>
