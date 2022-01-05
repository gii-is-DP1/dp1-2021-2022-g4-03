package org.springframework.samples.petclinic.game;

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.playerState.PlayerState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameLogic {

    public static void initPlayerStates(Game game) throws NoSuchMethodException, InvocationTargetException,
        IllegalAccessException {

        Class<?> gameClass = game.getClass();

        Integer firstPlayer = game.getOrder().get(0);

        Method mGet = gameClass.getMethod("getPlayerState_" + firstPlayer);
        PlayerState state = (PlayerState) mGet.invoke(game);
        state.setActive(true);
    }

    public static void initBoard(Game game, List<Card> allSpecialCards, List<Card> allNormalCards) {
        Board board = game.getBoard();

        List<Integer> specialIdList = allSpecialCards.stream().map(card -> card.getId()).collect(Collectors.toList());
        List<Integer> normalIdList = allNormalCards.stream().map(card -> card.getId()).collect(Collectors.toList());

        //Shuffle all normal cards and add them to the draw deck
        Collections.shuffle(normalIdList);
        board.setCartasMontaña(normalIdList);

        //Shuffle special cards and get them in the special slots
        Collections.shuffle(specialIdList);
        board.setCartasAccionEspecial_0(specialIdList.subList(0,3));
        board.setCartasAccionEspecial_0(specialIdList.subList(3,6));
        board.setCartasAccionEspecial_0(specialIdList.subList(6,9));

        //Draw the nine initial cards and put them in the mine slots
        //TODO: Initial cards are predefined, add attribute to cards
        List<Integer> mine= board.getCartas();
        Integer index = 0;
        Integer cardId = -1;
        while (index < 9) {
            cardId = board.getCartasMontaña().remove(0);
            mine.add(cardId);
        }

    }

    public static void playerTurn(Game game, BoardData data){



    }
}
