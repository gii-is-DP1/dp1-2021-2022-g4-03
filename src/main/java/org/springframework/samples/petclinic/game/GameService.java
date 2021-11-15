package org.springframework.samples.petclinic.game;

import java.security.InvalidParameterException;
import java.util.UUID;

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(UserDwarf player1) {
        Game game = new Game();
        // game.setBoard(new Board());; //vacia hasta que exista board
        game.setId(Integer.parseInt(UUID.randomUUID().toString()));
        game.setPlayer1(player1);
        game.setGameStatus(GameStatus.NEW);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToGame(UserDwarf additionalPlayer, Integer gameId) {
        if(GameStorage.getInstance().getGames().containsKey(gameId)) {
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

}
