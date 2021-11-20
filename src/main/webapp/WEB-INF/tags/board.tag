<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="board" required="false" rtexprvalue="true" type="org.springframework.samples.petclinic.board.Board.java"
 description="board to be rendered" %>
 
<canvas id="canvas" width="${board.width}" height="${board.height}"></canvas>
<img id="source" src="${board.background}" style="display:none">
<img id="carta1" src="resources/images/pets.png" style="display:none">
<img id="carta2" src="resources/images/pets.png" style="display:none">
<img id="carta3" src="resources/images/pets.png" style="display:none">
<script>
var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var image = document.getElementById('source');

ctx.drawImage(image, 0, 0, ${board.width}, ${board.height});
</script>