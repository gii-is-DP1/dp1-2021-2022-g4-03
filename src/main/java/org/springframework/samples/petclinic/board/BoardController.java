package org.springframework.samples.petclinic.board;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping(value = "/board")
    public String welcome(Map<String, Object> model, HttpServletResponse response) {
        // response.addHeader("Refresh","5");
        model.put("now", new Date());

        // Crear una partida nueva con el user actual (de momento un user cualquiera)

        // Meter en el modelo el tablero para poder mostrarlo por pantalla

        // model.put("board", boardService.findById(1).get());
        // model.put("board", boardService.createBoard());
        // model.put("game", gameService.createGame(userDwarfService.findById(4)));
        // model.put("board", gameService.getBoard(1));
        return "game/board";
    }

}
