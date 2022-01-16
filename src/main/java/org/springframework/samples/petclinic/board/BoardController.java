package org.springframework.samples.petclinic.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameStorage;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.samples.petclinic.web.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Map;

@Controller
public class BoardController {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private final BoardService boardService;
    private final UserDwarfService userDwarfService;

    @Autowired
    public BoardController(BoardService boardService, UserDwarfService userDwarfService) {
        this.boardService = boardService;
        this.userDwarfService = userDwarfService;
    }

    private GameStorage gameStorage = GameStorage.getInstance();

    @GetMapping(value = "/board/{gameId}")
    public String welcome(@PathVariable("gameId") Integer gameId, Map<String, Object> model) {
        // response.addHeader("Refresh","5");
        model.put("now", new Date());
        String currentUserUsername = currentUser.getCurrentUser();
        model.put("currentUser", userDwarfService.findUserDwarfByUsername2(currentUserUsername).orElse(null));
        Game game = gameStorage.getGame(gameId);
        model.put("game", game);
        return "game/board";
    }

}
