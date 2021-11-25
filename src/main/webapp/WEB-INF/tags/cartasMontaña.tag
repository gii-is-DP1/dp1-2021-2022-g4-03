<%@ attribute name="size" required="true" rtexprvalue="true" 
 description="Size of the piece to show" %>
 <%@ attribute name="cartaMontaña" required="true" rtexprvalue="true" type="org.springframework.samples.petclinic.board.CartasMontaña"
 description="Piece to be rendered" %>
 <script>
 var canvas = document.getElementById("canvas");
 var ctx = canvas.getContext("2d");
 var image = document.getElementById('${cartasMontaña.type}-${cartasMontaña.color}');
 ctx.drawImage(image,${cartasMontaña.getPositionXInPixels(size)},${cartasMontaña.getPositionYInPixels(size)},${size},${size});
 </script>