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


    // @GetMapping(value = "/game/connect/{gameId}")
    // public String connectToGame(@PathVariable("gameId") Integer gameId) {
    // 	// Hasta que no tengamos currentUser conectamos a un user random
    // 	UserDwarf player= userDwarfService.findUserDwarfByUsername2(1);
    // 	gameService.connectToGame(player, gameId);
    // 	return "redirect:/board/{gameId}";
    // }

    @RequestMapping(value = "/api/game/{gameId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Game mainLoop(@PathVariable("gameId") Integer gameId,
                  @RequestBody(required = false) ClientData data, ModelMap model)
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        GameStorage gameStorage = GameStorage.getInstance();
        Game currentGame = gameStorage.getGame(gameId);


        while (currentGame.getGameStatus() == GameStatus.NEW || currentGame.getGameStatus() == GameStatus.IN_PROGRESS) {
            switch (currentGame.getPhase()) {
                case INICIO:
                    switch (currentGame.getGameStatus()) {
                        case NEW:
                            GameLogic.initPlayerStates(currentGame);
                            //TODO: Can't test this without cards, nullPointerException
                        /*GameLogic.initBoard(currentGame, cardService.findAllSpecialCards(),
                            cardService.findAllNormalCards());*/
                            currentGame.setGameStatus(GameStatus.IN_PROGRESS);
                            currentGame.setPhase(Phase.ASIGNACION);
                        case IN_PROGRESS:
                            GameLogic.drawCard();
                            break;
                    }

                    return currentGame;

                case ASIGNACION:
                    currentGame.setTurnsTaken(currentGame.getTurnsTaken() + 1);

                    //Check if it has gone One round
                    if (!(currentGame.getTurnsTaken() % (currentGame.getNumberOfPlayers() * 2 + 1) == 0)) {
                        String result = GameLogic.playerTurn(currentGame, data);
                    }

                    currentGame.setPhase(Phase.AYUDA);

                    return currentGame;

                case ESPECIAL:

                    System.out.println("***Game shouldn't come into this function in this state, something has probably gone wrong.***");

                    return currentGame;

                case AYUDA:

                    break;

                case DEFENSA:
                    break;

                case MINA:
                    break;

                case FORJA:
                    return currentGame;

                case FIN:
                    return currentGame;

                default:
                    break;
            }
        }

        return currentGame;
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
