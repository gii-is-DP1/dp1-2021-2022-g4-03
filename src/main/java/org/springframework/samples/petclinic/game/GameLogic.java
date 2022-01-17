package org.springframework.samples.petclinic.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.card.CardType;
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

        // Shuffle all normal cards and add them to the draw deck
        Collections.shuffle(normalIdList);
        board.setCartasMontaña(normalIdList);

        // Shuffle special cards and get them in the special slots
        Collections.shuffle(specialIdList);
        board.setCartasAccionEspecial_0(specialIdList.subList(0, 3));
        board.setCartasAccionEspecial_0(specialIdList.subList(3, 6));
        board.setCartasAccionEspecial_0(specialIdList.subList(6, 9));

        // Draw the nine initial cards and put them in the mine slots
        // TODO: Initial cards are predefined, add attribute to cards
        List<Integer> mine = board.getCartas();
        int index = 0;
        Integer cardId = -1;
        while (index < 9) {
            cardId = board.getCartasMontaña().remove(0);
            mine.add(cardId);
            index++;
        }

    }

    public static String playerTurn(Game game, ClientData data)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
            IllegalStateException {

        // TODO: Write an ordinal enum for possible return states and change return to
        // an int;

        if (!possibleActions.contains(data.getPlayerAction())) {
            throw new IllegalStateException("Action described is not possible.");
        }

        int playerAction = data.getPlayerAction();
        int playerIndex = getPlayerIndex(game, data);
        PlayerState playerState;

        if (playerIndex == game.getActivePlayer()) {
            playerState = getIndexedPlayerState(game, playerIndex);
            List<Integer> workerList = playerState.getWorkerList();

            // Finding out which worker is available
            int worker = workerList.indexOf(12);
            if (worker == -1) {
                return "no worker available";
            }

            if (List.of(9, 10, 11).contains(playerAction)) {
                // Check if available worker count is enough and if they aren't special workers
                if ((workerList.stream().takeWhile(w -> w == 12).count() == 2) && !(worker == 2 || worker == 3)) {
                    // Temp: specialAction_result
                    int sa_r = specialAction(game, data);
                    return "special action done";
                } else {
                    return "special action not possible";
                }
            }

            if (game.getAllPlayerStates().stream().flatMap(pS -> pS.getWorkerList().stream())
                    .anyMatch(w -> w == playerAction)) {
                System.out.println("Worker already in position");
            }

            workerList.set(worker, playerAction);
            playerState.setWorkerList(workerList);

            game.setActivePlayer(game.getActivePlayer() + 1);

        } else {
            return "player isn't the active player";
        }

        return "player turn finished";
    }

    public static int specialAction(Game game, ClientData data) {

        /*
         * Overview of how it works:
         * - Change worker states.
         * - Invoke card effect.
         * - Get normal card.
         * - Check if someone in position of normal card; if true, give player resources
         * of original card. Mark position of card as blocked
         * for more workers (somehow).
         * - Done.
         */

        return 0;
    }

    public static int getHelp(Game game, ClientData data)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Board board = game.getBoard();

        List<Integer> presentHelpCardIds = board.getCartas().stream().map(cardId -> cardService.findCardById(cardId))
                .takeWhile(card -> card.getCardType().equals(CardType.ESPECIAL)).map(Card::getId)
                .collect(Collectors.toList());

        if (presentHelpCardIds.size() == 0) {
            return 0;
        }

        int playerIndex = getPlayerIndex(game, data);
        PlayerState playerState = getIndexedPlayerState(game, playerIndex);

        return 0;
    }

    public static int defend(Game game, ClientData data) {

        return 0;
    }

    public static int forge(Game game, ClientData data) {

        return 0;
    }

    public static int endGame(Game game, ClientData data) {

        return 0;
    }

    public static int drawCard() {

        return 0;
    }

    private static int getPlayerIndex(Game game, ClientData data) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        for (int i = 0; i < 3; i++) {
            Method getPlayer = gameClass.getMethod("getPlayer" + i);
            UserDwarf player = (UserDwarf) getPlayer.invoke(game);

            if (!(player == null)) {
                if (player.getUsername().equals(data.getCurrentUser())) {
                    return i;
                }
            }
        }

        return 0;
    }

    private static PlayerState getIndexedPlayerState(Game game, int playerIndex)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        return (PlayerState) gameClass.getMethod("getPlayerState_" + playerIndex).invoke(game);
    }

    public static void resourceRound(Game game)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
            IllegalStateException {

        PlayerState playerState;
        final int NUMWORKERS = 4;

        List<PlayerState> allPlayersStates = game.getAllPlayerStates();

        for (int ps = 0; ps < allPlayersStates.size(); ps++) {

            playerState = allPlayersStates.get(ps);

            for (int i = 0; i < NUMWORKERS; i++) {

                List<Integer> workerList = playerState.getWorkerList();
                // Mirar la carta que hay en la posición del trabajador y comprobar si es de
                // tipo EXTRACCION_RECURSOS.
                // En caso afirmativo incluir los efectos al playerState del jugador.

                Integer workerPosition = workerList.get(i);

                if (workerPosition.toString().matches("[01245678]")) {

                    List<Integer> mountainCards = game.getBoard().getCartas();

                    for (int j = 0; j <= mountainCards.size(); j++) {

                        Card mineCard = cardService.findCardById(j);
                        Integer mineCardPosition = mineCard.getPosition();

                        if (mineCardPosition == workerPosition) {
                            if (mineCard.getCardType().equals(CardType.EXTRACCION_RECURSOS)) {

                                String efect = mineCard.getEffect();
                                String field[] = efect.split(",");

                                for (int z = 0; z <= field.length; z++) {

                                    String campo = field[z];

                                    if (campo.contains("i")) {
                                        Integer index = campo.indexOf("i");
                                        String resource = campo.substring(0, index);
                                        playerState.setIron(playerState.getIron() + Integer.valueOf(resource));

                                    } else if (campo.contains("g")) {
                                        Integer index = campo.indexOf("g");
                                        String resource = campo.substring(0, index);
                                        playerState.setGold(playerState.getGold() + Integer.valueOf(resource));
                                    } else {
                                        Integer index = campo.indexOf("s");
                                        String resource = campo.substring(0, index);
                                        playerState.setSteel(playerState.getSteel() + Integer.valueOf(resource));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
