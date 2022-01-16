package org.springframework.samples.petclinic.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.playerState.PlayerState;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameLogic {

    @Autowired
    private static CardService cardService;

    private static final Class<?> gameClass = Game.class;
    private static final Class<?> playerStateClass = PlayerState.class;
    private static final List<Integer> possibleActions = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

    public static void initPlayerStates(Game game) throws NoSuchMethodException, InvocationTargetException,
        IllegalAccessException {

        Integer firstPlayer = game.getOrder().get(0);

        game.setActivePlayer(firstPlayer);

    }

    public static void initBoard(Game game, List<Card> allSpecialCards, List<Card> allNormalCards) {
        Board board = game.getBoard();

        List<Integer> specialIdList = allSpecialCards.stream().map(BaseEntity::getId).collect(Collectors.toList());
        List<Integer> normalIdList = allNormalCards.stream().map(BaseEntity::getId).collect(Collectors.toList());

        //Shuffle all normal cards and add them to the draw deck
        Collections.shuffle(normalIdList);
        board.setCartasMontaña(normalIdList);

        //Shuffle special cards and get them in the special slots
        Collections.shuffle(specialIdList);
        board.setCartasAccionEspecial_0(specialIdList.subList(0, 3));
        board.setCartasAccionEspecial_0(specialIdList.subList(3, 6));
        board.setCartasAccionEspecial_0(specialIdList.subList(6, 9));

        //Draw the nine initial cards and put them in the mine slots
        //TODO: Initial cards are predefined, add attribute to cards
        List<Integer> mine = board.getCartas();
        int index = 0;
        Integer cardId = -1;
        while (index < 9) {
            cardId = board.getCartasMontaña().remove(0);
            mine.add(cardId);
            index++;
        }

    }

    public static void playerTurn(Game game, BoardData data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
        IllegalStateException {

        if (!possibleActions.contains(data.getPlayerAction())) {
            throw new IllegalStateException("Action described is not possible.");
        }

        int playerAction= data.getPlayerAction();
        int playerIndex = 0;
        PlayerState playerState = null;
        Method getPlayer;
        Method getPlayerState;
        Method setWorker;
        UserDwarf player;

        for (int i = 0; i < 3; i++) {
            getPlayer = gameClass.getMethod("getPlayer" + i);
            player = (UserDwarf) getPlayer.invoke(game);

            if (!(player == null)) {
                if (player.getUsername().equals(data.getCurrentUser())) {
                    playerIndex = i;
                    break;
                }
            }
        }

        getPlayerState = gameClass.getMethod("getPlayerState_" + playerIndex);

        if (playerIndex == game.getActivePlayer()) {
            playerState = (PlayerState) getPlayerState.invoke(game);

            List<Integer> workerList = playerState.getWorkerList();
            //Finding out which worker is available
            int worker = workerList.indexOf(12);
            if (worker == -1) {
                System.out.println("\n***No worker available, handle this.***\n");
            }

            if(game.getAllPlayerStates().stream().flatMap(pS->pS.getWorkerList().stream()).anyMatch(w->w==playerAction)){
                System.out.println("Worker already in position");
            }

            workerList.set(worker, playerAction);
            playerState.setWorkerList(workerList);

            game.setActivePlayer(game.getActivePlayer() + 1);

        } else {
            //Handle player not being the active player. Send message to client, maybe.
        }

    }
}
