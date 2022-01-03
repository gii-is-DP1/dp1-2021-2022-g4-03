package org.springframework.samples.petclinic.game;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private static GameStorage gameStorage;

    @Autowired
    private static GameRepository gameRepository;

    public Game createGame(UserDwarf player1) {
        Game game = new Game();
        game.setId(1);
        game.setPlayer1(player1);
        game.setBoard(new Board(game.getId()));
        game.setOrder(List.of(1,2,3));
        game.setPhase(Phase.INICIO);
        game.setGameStatus(GameStatus.NEW);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToGame(UserDwarf additionalPlayer, Integer gameId) {
        if(!GameStorage.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParameterException("Game with provided id does not exist");
        }
        Game game = GameStorage.getInstance().getGames().get(gameId);
        if(game.getPlayer3()!=null) {
            throw new InvalidParameterException("Game already full");
        }else if(game.getPlayer2()==null) {
            game.setPlayer2(additionalPlayer);
            game.setGameStatus(GameStatus.IN_PROGRESS);
            GameStorage.getInstance().setGame(game);
        }else if(game.getPlayer3()==null) {
            game.setPlayer3(additionalPlayer);
            game.setGameStatus(GameStatus.IN_PROGRESS);
            GameStorage.getInstance().setGame(game);
        }
        return game;
    }

    public Board getBoard(Integer gameId) {
        Game game = GameStorage.getInstance().getGame(gameId);
        return game.getBoard();
    }

    // Guarda la partida en la bbdd y la elimina de la memoria de java
    public void finishGame(Integer gameId) {
        gameRepository.save(gameStorage.getGame(gameId));
        gameStorage.getGames().remove(gameId);
    }

    // Método que saca al user de la partida (se activa con un botón) y si no quedan jugadores cierra la partida
    public void surrender(Integer gameId, UserDwarf player) {
        Game game = GameStorage.getInstance().getGame(gameId);
        if (player.equals(game.getPlayer1())) {
            game.setPlayer1(null);
        } else if(player.equals(game.getPlayer2())) {
            game.setPlayer2(null);
        } else if(player.equals(game.getPlayer3())) {
            game.setPlayer3(null);
        }
        if (game.getPlayer1().equals(null) && game.getPlayer2().equals(null) && game.getPlayer3().equals(null)) {
            finishGame(gameId);
        }
    }

}
