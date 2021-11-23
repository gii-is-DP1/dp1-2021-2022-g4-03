<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="board" tagdir="/WEB-INF/tags" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="board">

    <h2><fmt:message key="titulo"/></h2>
    
    <p>	
    <h2><c:out value="${now}"/></h2>

    <div class="row">
        <div class="col-md-12">
            <canvas id="canvas" width="${board.width}" height="${board.height}">
                <img id="source" src="${board.background}" style="display:none">
                <img id="carta1" src="resources/images/pets.png" style="display:none">
                <img id="carta2" src="resources/images/pets.png" style="display:none">
                <img id="carta3" src="resources/images/pets.png" style="display:none">
                <script>
                    var canvas = document.getElementById("canvas");
                    var ctx = canvas.getContext("2d");
                    var image = document.getElementById('source');

                    ctx.drawImage(image, 0, 0, "${board.width}", "${board.height}");
                </script>
            </canvas>
        </div>
    </div>
</petclinic:layout>