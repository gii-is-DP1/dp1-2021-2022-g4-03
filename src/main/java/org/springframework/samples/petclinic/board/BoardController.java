package org.springframework.samples.petclinic.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.game.ClientData;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameStorage;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.samples.petclinic.web.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
public class BoardController {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private final BoardService boardService;
    private final UserDwarfService userDwarfService;

    private final CardService cardService;

    @Autowired
    public BoardController(BoardService boardService, UserDwarfService userDwarfService, CardService cardService) {
        this.boardService = boardService;
        this.userDwarfService = userDwarfService;
        this.cardService = cardService;
    }

    private final GameStorage gameStorage = GameStorage.getInstance();

    @GetMapping(value = "/board/{gameId}")
    public String welcome(@PathVariable("gameId") Integer gameId, Map<String, Object> model) {
        // response.addHeader("Refresh","5");
        model.put("now", new Date());
        String currentUserUsername = currentUser.getCurrentUser();
        model.put("currentUser", userDwarfService.findUserDwarfByUsername2(currentUserUsername).orElse(null));
        Game game = gameStorage.getGame(gameId);
        model.put("game", game);
        model.put("clientData", new ClientData());
        return "game/board";
    }

    @GetMapping(value = "/api/card/{cardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Card getCard(@PathVariable("cardId") Integer cardId) {
        return cardService.findCardById(cardId);
    }

}
