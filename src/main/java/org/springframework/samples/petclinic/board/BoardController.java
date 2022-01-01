package org.springframework.samples.petclinic.board;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.samples.petclinic.web.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private final BoardService boardService;
    private final GameService gameService;
    private final UserDwarfService userDwarfService;

    @Autowired
    public BoardController(BoardService boardService, GameService gameService, UserDwarfService userDwarfService) {
        this.boardService = boardService;
        this.gameService = gameService;
        this.userDwarfService = userDwarfService;
    }

    @GetMapping(value = "/board/{gameId}")
    public String welcome(@PathVariable("gameId") Integer gameId, Map<String, Object> model){
        // response.addHeader("Refresh","5");
        model.put("now", new Date());
        String currentUserUsername= CurrentUser.getCurrentUser();
        model.put("currentUser", userDwarfService.findUserDwarfByUsername2(currentUserUsername).get());

        // Crear una partida nueva con el user actual (de momento un user cualquiera)

        // Meter en el modelo el tablero para poder mostrarlo por pantalla

        // model.put("board", boardService.findById(1).get());
        // model.put("board", boardService.createBoard());
        // model.put("game", gameService.createGame(userDwarfService.findById(4)));
        model.put("board", gameService.getBoard(gameId));
        return "game/board";
    }

}
