package org.springframework.samples.petclinic.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.samples.petclinic.web.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Controller
public class GameController {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private GameService gameService;

    @Autowired
    private UserDwarfService userDwarfService;

    @Autowired
    private CardService cardService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = "/game/new")
    public String createGame() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        UserDwarf player = userDwarfService.findUserDwarfByUsername2(currentUser.getCurrentUser()).get();
        Game game = gameService.createGame(player);
        //Unable to test this without cards, nullPointerException
        game = mainLoop(game.getId(), null, null);
        return "redirect:/board/" + game.getId();
    }


    @GetMapping(value = "/game/connect/{gameId}")
    public String connectToGame(@PathVariable("gameId") Integer gameId) {
        UserDwarf user = userDwarfService.findUserDwarfByUsername(currentUser.getCurrentUser()).iterator().next();
    	gameService.connectToGame(user, gameId);
    	return "redirect:/board/{gameId}";
    }


    //TODO: Handle in js to call back to mainloop when player input phases have ended so the rest of the logic can continue
    @RequestMapping(value = "/api/game/{gameId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Game mainLoop(@PathVariable("gameId") Integer gameId,
                  @RequestBody(required = false) ClientData data, ModelMap model)
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        GameStorage gameStorage = GameStorage.getInstance();
        Game game = gameStorage.getGame(gameId);

        int defenseResult = 0;

        //Label for breaks, similar to C's goto
        mainLoopStart:
        while (game.getGameStatus() == GameStatus.NEW || game.getGameStatus() == GameStatus.IN_PROGRESS) {
            switch (game.getPhase()) {
                case INICIO:
                    switch (game.getGameStatus()) {
                        case NEW:
                            GameLogic.initPlayerStates(game);
                            GameLogic.initBoard(game, cardService.findAllSpecialCards(), cardService.findAllNormalCards(),
                                cardService.findAllInitialCards());
                            game.setGameStatus(GameStatus.IN_PROGRESS);
                            game.setPhase(Phase.ASIGNACION);
                        case IN_PROGRESS:
                            GameLogic.drawCard(game);
                            break;
                    }

                    return game;

                case ASIGNACION:
                    //Check if still has actions to do
                    if (!game.getTurnsOrder().isEmpty()) {
                        String result = GameLogic.playerTurn(game, data);

                        //TODO: Change this if to a switch statement consisting of the possible return states of a player turn.
                        if (result.equals("player turn finished")) {
                            Integer playerIndex = GameLogic.checkIfHelpAction(game, data);
                            if (playerIndex != -1) game.getHelpTurnsOrder().add(playerIndex);
                        }
                    } else if (!game.getHelpTurnsOrder().isEmpty()) {
                        GameLogic.processHelpTurnOrder(game, data);
                        game.setPhase(Phase.AYUDA);
                    }

                    return game;

                case ESPECIAL:
                    System.out.println("***Game shouldn't come into this function in this state, something has probably gone wrong.***");
                    game.setPhase(Phase.FIN);
                    break mainLoopStart;

                case AYUDA:
                    if (!game.getTurnsOrder().isEmpty()) {
                        String result = GameLogic.playerTurn(game, data);
                    } else {
                        game.setPhase(Phase.DEFENSA);
                    }

                    //TODO: Handle special cases here too.

                    return game;

                case DEFENSA:
                    defenseResult = GameLogic.defense(game);
                    game.setPhase(Phase.MINA);

                case MINA:
                    if (defenseResult != 1) {
                        GameLogic.resourceRound(game);
                    }

                case FORJA:

                case FIN:
                    return game;

                default:
                    break;
            }
        }

        return game;
    }

    @GetMapping(value = "/game/{gameId}/surrender")
    public String surrender(@PathVariable("gameId") Integer gameId) {
        UserDwarf player = userDwarfService.findUserDwarfByUsername2(currentUser.getCurrentUser()).get();
        gameService.surrender(gameId, player);
        return "redirect:/";
    }

    @GetMapping(value = "/game/check")
    public void checkGames() {
        Map<Integer, Game> games = GameStorage.getInstance().getGames();
        // List<String> gameIds = new ArrayList<>();
        // for (Game game : games.values()) gameIds.add(game.getId().toString());
        // System.out.println(gameIds);
        System.out.println(games);
    }

}
