<%@ attribute name="padding" required="true" rtexprvalue="true" 
 description="Padding for cards cells" %>
<%@ attribute name="card" required="true" rtexprvalue="true" type="org.springframework.dwarf.card.Card"
description="Card to be rendered" %>

<script>
	var canvas = document.getElementById("canvas");
	var ctx = canvas.getContext("2d");
	var image = "resources/cards/Alloy Steel 1-1.png";
    var xsize = 200;
    var ysize = 270;
	ctx.drawImage(image,{card.getPositionXInPixels(xsize)},${card.getPositionYInPixels(ysize)},${xsize},${ysize});
</script>
