<meta charset="ISO-8859-1">
<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

                        <petclinic:layout pageName="Aboutus">
                            <div class="bodyBackground">
                                <h2>SOBRE NOSOTROS</h2>
                                <div class="panel form-square">
                                    Somos el grupo L4-3 formado por los miembros: <br>
                                    <br>
                                    Botello Romero, Francisco <br>
                                    Jim&eacute;nez Fern&aacute;ndez, Rafael &Aacute;ngel <br>
                                    Quind&oacute;s de la Riva, Pablo <br>
                                    Rivas Roa, Sergio <br>
                                    Su&aacute;rez Garc&iacute;a, Antonio Jos&eacute; <br>
                                    V&aacute;zquez Rodr&iacute;guez, Fausto
                                    <br>
                                    A contiuaci&oacute;n vamos a explicar las reglas de DWARF, el juego que hemos elegido para realizar el trabajo.


                                </div>
                                <br>
                                    <h2>REGLAS DEL JUEGO</h2>
                                <div class="panel form-square">
                                    <h3>Preparaci&oacute;n del juego</h3>
                                    DWARF se juega a lo largo de seis rondas. Cada ronda se divide en tres fases
(Extraer mineral, Seleccion de acciones y Resoluci&oacute;n de acciones). El juego continua
hasta que un jugador crea 4 objetos o bien hasta que un jugador necesita robar
cartas de La Monta&ntilde;a y no puede. La partida termina cuando se completa la ronda
actual.
Comienza entregando el token de Jugador inicial al jugador que mas
recientemente haya realizado un acto desinteresado. (O escogelo al azar.)
<br>
<h3>Fase de extracci&oacute;n de minerales</h3>
Roba dos cartas de La Monta&ntilde;a y col&oacute;calas, de una en una, en la localizaci&oacute;n de la
Mina indicada por la zona naranja marcada en la rejilla impresa en la carta. Las
cartas nuevas cubren a las cartas anteriores que ocupasen el mismo espacio. Si las
dos cartas robadas en la fase actual deben ocupar el mismo espacio, roba una
tercera carta. Si la tercera carta pide colocarla en la misma ubicaci&oacute;n, no robes una
cuarta carta. Hay seis cartas de cada ubicaci&oacute;n en el mazo (incluyendo las cartas
iniciales). Las cartas que hay en cada ubicaci&oacute;n son conocidas por todos, y
cualquier jugador puede mirar el mazo de cualquier localizaci&oacute;n de la Mina en
cualquier momento, pero sin alterar el orden de las cartas.
Cuando hay que robar una carta de La Monta&ntilde;a y no quedan cartas que robar, el
juego acaba de forma inmediata.
<br>
<h3>Fase de selecci&oacute;n de acciones</h3>
Empezando por el jugador inicial y avanzando en el sentido de las agujas del reloj,
cada jugador coloca un trabajador en una localizaci&oacute;n disponible de la Mina o escoge una
carta de Acci&oacute;n Especial.
<br>
1. Colocar un Trabajador - Puede colocarse un trabajador en cualquiera de las nueve
localizaciones que no est&eacute; ocupada por otro trabajador. Colocar un trabajador en la Mina activa
una Acci&oacute;n en la Fase de Resoluci&oacute;n de Acciones.
<br>
2. Acci&oacute;n Especial - Para escoger una Acci&oacute;n Especial no se coloca ning&uacute;n trabajador en la
Mina. En su lugar, el jugador coloca sus dos trabajadores normales, o gasta 4 medallas y coloca un trabajador sobre uno de los mazos de cartas de Acci&oacute;n Especial y roba la carta superior de ese
mazo. El mazo no resulta bloqueado como s&iacute; ocurre con las localizaciones de la Mina,
y otros jugadores pueden usar el mismo mazo de Cartas de Acci&oacute;n Especial en este
turno. El lado visible de las cartas se resuelve inmediatamente, se voltea la carta y
se coloca como se indica en la Fase de Extracci&oacute;n de Minerales. Si ya hubiera un trabajador en
esta ubicaci&oacute;n, el jugador propietario de ese trabajador ejecuta ahora la acci&oacute;n de la carta de
Mina original antes de que la nueva carta sea colocada. El trabajador se mantiene en este
lugar para bloquear la nueva localizaci&oacute;n durante este turno. Este trabajador no se ejecuta de
nuevo en esta ronda.
Cuando todos los jugadores han realizado una acci&oacute;n, todo el que tenga un trabajador disponible puede colocarlo ahora, limitado a las ubicaciones disponibles. Esta
segunda colocaci&oacute;n se realiza en orden inverso, desde el &uacute;ltimo jugador hasta el
primero en sentido antihorario. Todo jugador que no tenga trabajadores disponibles es ignorado.
Una vez colocados todos los trabajadores, se resuelven las Acciones en el siguiente orden:
<br>
Recibir ayuda - Todos los trabajadores obtenidos de una localizaci&oacute;n Obtener
ayuda, se colocan ahora. Los trabajadores adicionales no pueden colocarse sobre
una Acci&oacute;n Especial.
Si dos o m&aacute;s jugadores realizan la acci&oacute;n Recibir ayuda, los trabajadores se
colocan en el orden en el que se ocup&oacute; la Acci&oacute;n, colocando de forma
alterna el tercer y cuarto trabajador. Si no hay localizaciones disponibles en la
Mina en el momento de colocar un trabajador, ese se pierde. Las Acciones
correspondientes a cualquier trabajador se Resuelven despu&eacute;s de los trabajadores normales.
Si el actual Jugador inicial realiza recibir ayuda , el marcador pasa al
siguiente jugador en el sentido de las agujas del reloj.
<br>
Defender - Los monstruos en una localizaci&oacute;n de tipo defender que est&eacute; ocupada por
un trabajador son derrotados y su efecto se ignora. Los Jugadores ganan una medalla por cada propio en Defender. Los monstruos en localizaciones de tipo defender sin trabajadores 
atacar&aacute;n y su efecto afecta a todos los jugadores, como se describe en
cada carta. Las localizaciones de tipo defender se resuelven, desde la localizaci&oacute;n
superior izquierda, de izquierda a derecha y de arriba hacia abajo.
<br>
Extraer mineral - Todos los trabajadores en localizaciones de extraer mineral se resuelven ahora,
siguiendo las instrucciones de la carta. Las localizaciones de extraer mineral pueden
resolverse en cualquier orden.
<br>
Forjar - Los trabajadores en localizaciones de forjar se resuelven ahora, seg&uacute;n lo indicado
en cada carta. Si el jugador inicial realiz&oacute; una acci&oacute;n de forjar, el marcador de jugador inicial pasa al
siguiente jugador en el sentido de las agujas del reloj.
<br>
<h3>Final de ronda</h3>
Recupera los trabajadores del &aacute;rea de juego. Devuelve cualquier trabajador adicional al suministro general.
<br>
<h3>Puntuaci&oacute;n final</h3>
Gana quien tenga la mayor&iacute;a de m&aacute;s recursos de oro, acero y objetos. Por ejemplo si un jugador es el que m&aacute;s oro y acero tiene
gana la partida, pues tiene la mayor&iacute;a de oro y acero (2 de los 3 recursos). Si dos jugadores empatan teniendo la mayor&iacute;a de 1 de los 3 recursos
gana quien m&aacute;s medallas tenga. Si se vuelve a empatar a medallas gana quien m&aacute;s hierro tenga. Finalmente si empatan en hierro se mirar&iacute;a qui&eacute;n de los dos ha 
forjado m&aacute;s objetos. 
                                        

                                </div>
                                <div id="fondo1"></div>
                            </div>
                        </petclinic:layout>