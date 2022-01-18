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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class GameLogic {

    @Autowired
    private static CardService cardService;

    private static final Class<?> gameClass = Game.class;
    private static final List<Integer> possibleActions = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

    public static void initPlayerStates(Game game) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {

        List<Integer> order = game.getOrder();

        game.getTurnsOrder().addAll(order);
        Collections.reverse(order);
        game.getTurnsOrder().addAll(order);
        Collections.reverse(order);

        game.setActivePlayer(game.getTurnsOrder().remove(0));

    }

    public static void initBoard(Game game, List<Card> allSpecialCards, List<Card> allNormalCards,
            List<Card> allInitialCards) {
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
        List<Integer> mine = board.getCartas();

        Integer cardId;
        for (int index = 0; index < 9; index++) {
            int finalIndex = index;
            cardId = allInitialCards.stream().filter(card -> card.getPosition() == finalIndex).map(BaseEntity::getId)
                    .findFirst().orElse(-1);
            mine.add(cardId);
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
                return "mine position occupied";
            }

            workerList.set(worker, playerAction);
            playerState.setWorkerList(workerList);

            game.setActivePlayer(game.getTurnsOrder().remove(0));

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

    public static Integer checkIfHelpAction(Game game, ClientData clientData)
            throws InvocationTargetException, NoSuchMethodException,
            IllegalAccessException {

        Card actionableCard = cardService.findCardById(game.getBoard().getCartas().get(clientData.getPlayerAction()));

        if (actionableCard.getCardType().isHelp()) {
            return getPlayerIndex(game, clientData);
        }

        return -1;
    }

    public static List<Integer> processHelpTurnOrder(Game game, ClientData data) {
        // Check if there are actionable help cards on the board
        Board board = game.getBoard();
        List<Card> presentHelpCardsList = board.getCartas().stream().map(cardId -> cardService.findCardById(cardId))
                .takeWhile(card -> card.getCardType().isHelp()).collect(Collectors.toList());

        if (presentHelpCardsList.size() == 0) {
            return new ArrayList<>();
        }

        List<Integer> helpTurnsOrder = game.getHelpTurnsOrder();
        List<Integer> turnsOrder = game.getTurnsOrder();
        IntStream.range(0, helpTurnsOrder.size()).forEach(i -> turnsOrder.add(i * 2 + 1, helpTurnsOrder.get(i)));

        if (turnsOrder.get(0).equals(game.getOrder().get(0))) {
            Collections.rotate(game.getOrder(), 1);
        }

        game.setActivePlayer(turnsOrder.remove(0));

        return turnsOrder;
    }

    public static int defense(Game game) {
        Board board = game.getBoard();
        List<PlayerState> allPlayerStates = game.getAllPlayerStates();

        List<Card> presentDefenseCardsList = board.getCartas().stream().map(cardId -> cardService.findCardById(cardId))
                .takeWhile(card -> card.getCardType().isDefense()).collect(Collectors.toList());

        if (presentDefenseCardsList.isEmpty()) {
            return 0;
        }

        List<Integer> defenseCardsPositions = presentDefenseCardsList.stream().map(Card::getPosition)
                .collect(Collectors.toList());
        List<Integer> occupiedDefenseCards = allPlayerStates.stream()
                .flatMap(playerState -> playerState.getWorkerList().stream()).distinct()
                .filter(defenseCardsPositions::contains).collect(Collectors.toList());

        presentDefenseCardsList.removeIf(card -> occupiedDefenseCards.contains(card.getPosition()));

        allPlayerStates.stream()
                .filter(playerState -> playerState.getWorkerList().stream().anyMatch(occupiedDefenseCards::contains))
                .forEach(playerState -> playerState.setMedal(playerState.getMedal() + 1));

        if (presentDefenseCardsList.isEmpty()) {
            return 0;
        }

        for (Card card : presentDefenseCardsList) {
            String effect = card.getEffect();

            if (effect.equals("*")) {
                return 1;
            } else if (effect.equals("?")) {
                allPlayerStates.forEach(playerState -> {
                    playerState.setGold(Math.min(playerState.getGold() - 2, 0));
                    playerState.setIron(playerState.getIron() + 2);
                });
            } else if (effect.length() == 3) {
                switch (effect.charAt(2)) {
                    case 'g':
                        allPlayerStates
                                .forEach(playerState -> playerState.setGold(Math.min(playerState.getGold() - 1, 0)));
                        break;
                    case 'i':
                        allPlayerStates
                                .forEach(playerState -> playerState.setIron(Math.min(playerState.getIron() - 1, 0)));
                        break;
                }
            } else {
                allPlayerStates.forEach(playerState -> playerState.setGold(Math.min(playerState.getGold() - 100, 0)));
            }
        }

        return 0;
    }

    public static int forge(Game game, ClientData data) {

        return 0;
    }

    public static int endGame(Game game, ClientData data) {

        return 0;
    }

    public static int drawCard(Game game) {
        Board board = game.getBoard();
        List<Integer> deck = board.getCartasMontaña();
        int timesDrawn = 0;
        List<Integer> positionsDrawn = new ArrayList<>();
        Integer p;

        while (timesDrawn < 4 && !deck.isEmpty()) {
            timesDrawn++;
            Card card = cardService.findCardById(deck.remove(0));
            p = card.getPosition();

            if (!positionsDrawn.contains(p)) {
                positionsDrawn.add(p);
                board.getCartas().add(p, card.getId());
            }
        }

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
        List<PlayerState> allPlayersStates = game.getAllPlayerStates();

        for (PlayerState playerState : allPlayersStates) {
            for (Integer workerPosition : playerState.getWorkerList()) {

                if (workerPosition.toString().matches("[01245678]")) {

                    List<Integer> cards = game.getBoard().getCartas();

                    for (int j = 0; j <= cards.size(); j++) {

                        Card mineCard = cardService.findCardById(j);
                        Integer mineCardPosition = mineCard.getPosition();

                        if (Objects.equals(mineCardPosition, workerPosition)) {
                            if (mineCard.getCardType().equals(CardType.EXTRACCION_RECURSOS)) {

                                String effect = mineCard.getEffect();
                                String[] field = effect.split(",");

                                for (int z = 0; z <= field.length; z++) {

                                    String campo = field[z];

                                    if (campo.contains("i")) {
                                        int index = campo.indexOf("i");
                                        String resource = campo.substring(0, index);
                                        playerState.setIron(playerState.getIron() + Integer.parseInt(resource));

                                    } else if (campo.contains("g")) {
                                        int index = campo.indexOf("g");
                                        String resource = campo.substring(0, index);
                                        playerState.setGold(playerState.getGold() + Integer.parseInt(resource));
                                    } else {
                                        int index = campo.indexOf("s");
                                        String resource = campo.substring(0, index);
                                        playerState.setSteel(playerState.getSteel() + Integer.parseInt(resource));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void timeToForge(Game game)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
            IllegalStateException {

        List<PlayerState> playerStates = game.getAllPlayerStates();

        for (int i = 0; i < playerStates.size(); i++) {

            PlayerState playerState = playerStates.get(i);
            List<Integer> listWorkers = playerState.getWorkerList();

            for (int w = 0; w < listWorkers.size(); w++) {

                Integer workerPosition = listWorkers.get(w);

                if (workerPosition.toString().matches("[012345678]")) {

                    List<Integer> cards = game.getBoard().getCartas();

                    for (int j = 0; j < cards.size(); j++) {

                        Card card = cardService.findCardById(cards.get(j));
                        Integer cardPosition = card.getPosition();

                        if (cardPosition == workerPosition) {
                            if (card.getCardType().equals(CardType.FORJA)) {

                                String effect = card.getEffect();
                                String[] field = effect.split(",");

                                for (int z = 0; z <= field.length; z++) {

                                    String campo = field[z];

                                    if (campo.contains("i")) {
                                        int index = campo.indexOf("i");
                                        String resource = campo.substring(0, index);
                                        playerState.setIron(playerState.getIron() + Integer.parseInt(resource));

                                    } else if (campo.contains("g")) {
                                        int index = campo.indexOf("g");
                                        String resource = campo.substring(0, index);
                                        playerState.setGold(playerState.getGold() + Integer.parseInt(resource));

                                    } else if (campo.contains("s")) {
                                        int index = campo.indexOf("s");
                                        String resource = campo.substring(0, index);
                                        playerState.setSteel(playerState.getSteel() + Integer.parseInt(resource));

                                    } else {
                                        int index = campo.indexOf("o");
                                        String resource = campo.substring(0, index);
                                        playerState.setObject(playerState.getObject() + Integer.parseInt(resource));
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
