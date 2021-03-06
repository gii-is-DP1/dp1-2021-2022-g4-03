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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    @Autowired
    private GameLogic gameLogic;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = "/game/list")
	public String initFindForm(ModelMap modelMap) {
		String view = "game/gamesList";
		Iterable<Game> games = gameService.findAll();
		modelMap.addAttribute("games", games);
		return view;
	}

    @GetMapping(value = "/game/new")
    public String createGame() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        UserDwarf player = userDwarfService.findUserDwarfByUsername2(currentUser.getCurrentUser()).get();
        Game game = gameService.createGame(player);
        //Unable to test this without cards, nullPointerException
        game = mainLoop(game.getId(), null);
        return "redirect:/board/" + game.getId();
    }


    @GetMapping(value = "/game/connect/{gameId}")
    public String connectToGame(@PathVariable("gameId") Integer gameId) {
        UserDwarf user = userDwarfService.findUserDwarfByUsername(currentUser.getCurrentUser()).iterator().next();
        if (GameStorage.getInstance().getGame(gameId).getAllPlayersInGame().contains(user)){
            return "redirect:/board/{gameId}";
        }
        gameService.connectToGame(user, gameId);
        return "redirect:/board/{gameId}";
    }


    //TODO: Handle in js to call back to mainloop when player input phases have ended so the rest of the logic can continue
    @RequestMapping(value = "/api/game/{gameId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Game mainLoop(@PathVariable("gameId") Integer gameId,
                  @RequestBody(required = false) ClientData data)
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        GameStorage gameStorage = GameStorage.getInstance();
        Game game = gameStorage.getGame(gameId);
        gameLogic.getInstance(cardService);
        
        if(data!=null) {
            data.setCurrentUser(currentUser.getCurrentUser());
            if (data.getPlayerAction() == -1) return game;
        }
        
        mainLoopStart:
        while (game.getGameStatus() == GameStatus.NEW || game.getGameStatus() == GameStatus.IN_PROGRESS) {
            switch (game.getPhase()) {
                case INICIO:
                    switch (game.getGameStatus()) {
                        case NEW:
                            gameLogic.initPlayerStates(game);
                            gameLogic.initBoard(game, cardService.findAllSpecialCards(), cardService.findAllNormalCards(),
                                cardService.findAllInitialCards());
                            game.setGameStatus(GameStatus.IN_PROGRESS);
                            game.setPhase(Phase.ASIGNACION);
                            break;
                        case IN_PROGRESS:
                            gameLogic.initPlayerStates(game);
                            gameLogic.drawCard(game);
                            game.setPhase(Phase.ASIGNACION);
                            break mainLoopStart;
                    }

                    return game;

                case ASIGNACION:
                    
                    if(game.getNumberOfPlayers()<3) return game;
                    
                    System.out.println(game.getTurnsOrder());
                    System.out.println(game.getActivePlayer());
                    if (!game.getTurnsOrder().isEmpty()) {
                        String result = gameLogic.playerTurn(game, data);
                        System.out.println("********"+result);
                        if (result.equals("player turn finished")) {
                            gameLogic.checkIfHelpAction(game, data);
                        } else if(result.equals("special action")){
                            continue;
                        }
                        return game;
                    }
                    if (!game.getHelpTurnsOrder().isEmpty()) {
                        gameLogic.processHelpTurnOrder(game, data);
                        game.setPhase(Phase.AYUDA);
                        continue;
                    }
    
                    game.setPhase(Phase.DEFENSA);
                    
                    continue;

                case ESPECIAL:
                    if(game.isDoTurnEffect()){
                        data.setPlayerAction(new Random().nextInt(9)+400);
                        game.setPhase(Phase.ASIGNACION);
                    }else if(game.isDoSellEffect()){
                        data.setPlayerAction(new Random().nextInt(4)+500);
                        game.setPhase(Phase.ASIGNACION);
                    }else if (game.isDoApprenticeEffect()){
                        data.setPlayerAction(new Random().nextInt(9)+600);
                        game.setPhase(Phase.ASIGNACION);
                    }
                    
                    gameLogic.specialAction(game, data);
                    game.setActivePlayer(game.getTurnsOrder().remove(0));

                    return game;

                case AYUDA:
                    if (!game.getTurnsOrder().isEmpty()) {
                        String result = gameLogic.playerTurn(game, data);
                    } else {
                        game.setPhase(Phase.DEFENSA);
                    }

                    return game;

                case DEFENSA:
                    if (game.isDoDefend()) {
                        gameLogic.defense(game);
                    }

                    game.setPhase(Phase.MINA);
                    continue;

                case MINA:
                    if (game.isDoMine()) {
                        gameLogic.resourceRound(game);
                    }

                    game.setPhase(Phase.FORJA);
                    continue;
                    
                case FORJA:
                    List<Integer> forgingPlayers = gameLogic.timeToForge(game);

                    if (!forgingPlayers.isEmpty()) {
                        if (forgingPlayers.contains(game.getActivePlayer())) {
                            Collections.rotate(game.getOrder(), 1);
                        }
                    }

                    game.setPhase(Phase.FIN);
                    continue;

                case FIN:
                    boolean endCheck = gameLogic.end(game);
                    
                    if(endCheck) gameService.finishGame(game.getId());
                    
                    break mainLoopStart;

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
