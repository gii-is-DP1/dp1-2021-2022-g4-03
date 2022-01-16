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
    ;

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

    public static void playerTurn(Game game, BoardData data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        int playerIndex = 0;
        PlayerState playerState = null;
        Method getPlayer;
        Method getPlayerState;
        UserDwarf player;

        for (int i = 0; i < 3; i++) {
            getPlayer = gameClass.getMethod("getPlayer" + i);
            player = (UserDwarf) getPlayer.invoke(game);

            if (!(player == null)) {
                if (player.getUsername().equals(data.currentUser)) {
                    playerIndex = i;
                    break;
                }
            }
        }

        getPlayerState = gameClass.getMethod("getPlayerState_" + playerIndex);

        if (playerIndex == game.getActivePlayer()) {
            playerState = (PlayerState) getPlayerState.invoke(game);


        } else {
            //Handle player not being the active player
        }

    }
}
