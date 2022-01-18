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
            <div class="col-md-12">
                <canvas id="canvas" width="${game.board.width}" height="${game.board.height}">
                    <img id="boardBackground" src="/${game.board.background}" style="display:none">
                </canvas>


                <button class="btn-primary" id="card1" value="0">Carta 1</button>

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
