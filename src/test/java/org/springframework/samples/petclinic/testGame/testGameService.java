
package org.springframework.samples.petclinic.testGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameRepository;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.game.GameStorage;
import org.springframework.samples.petclinic.game.Phase;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class testGameService {
    @Autowired
    protected GameService gameService;

    @Autowired
    protected GameRepository gameRepository;


    @ParameterizedTest
    @ValueSource(UserDwarf.findById(1))
    public void testCreateGame(UserDwarf player1){
        
        assertTrue(GameStorage.getInstance().getGame(gameService.createGame(player1).getId()).getId()!=null);
    }

    @ParameterizedTest
    @CsvSource("")
    public void testConnectToGame1(UserDwarf additionalPlayer, Integer gameId){
        assertTrue(GameStorage.getInstance().getGames().containsKey(gameId));
    }

    @Test
    public void testConnectToGame2(UserDwarf additionalPlayer, Integer gameId){
        Game game = GameStorage.getInstance().getGames().get(gameId);
        assertTrue(game.getPlayer3().equals(null));
    }

    @ParameterizedTest
    @CsvSource("1")
    public void testGetBoard(Integer gameId){
        Game game = GameStorage.getInstance().getGame(gameId);
        assertTrue(game.getBoard()!=null);
    }

    @ParameterizedTest
    @CsvSource("1")
    public void testfinishGame(Integer gameId){
        assertEquals(GameStorage.getInstance().getGame(gameId).getPhase(),Phase.FIN);
    }

    @Test
    public void testSurrender1(Integer gameId, UserDwarf player){
        Game game = GameStorage.getInstance().getGame(gameId);
        if(game.getPlayer1().equals(null) && game.getPlayer2().equals(null) && game.getPlayer3().equals(null)){
            assertEquals(game.getPhase(),Phase.FIN);
        }
    }


}
