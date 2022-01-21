package org.springframework.samples.petclinic.testGame;

import static org.mockito.BDDMockito.given;

import java.util.Map;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameLogic;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.game.GameStatus;
import org.springframework.samples.petclinic.game.GameStorage;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestMethodOrder(OrderAnnotation.class)
public class testGameService {

    private static final int TEST_UD_ID = 1;
    private static final int TEST_UD_ID2 = 2;

    @Autowired
    private GameService gameService;
    @MockBean
    private UserDwarfService udService;
    @MockBean
    private GameLogic gameLogic;
    
    private UserDwarf ud;
    private UserDwarf ud2;

    @BeforeEach
    void setup(){
        ud = new UserDwarf();
        ud.setActive(true);
        ud.setEmail("email@dwarf.com");
        ud.setId(TEST_UD_ID);
        ud.setPass("contrasena");
        ud.setUsername("nombre");
        given(this.udService.findUserDwarfById(TEST_UD_ID)).willReturn(ud);

        ud2 = new UserDwarf();
        ud2.setActive(true);
        ud2.setEmail("correoelectronico@dwarf.com");
        ud2.setId(TEST_UD_ID2);
        ud2.setPass("palabrasecreta");
        ud2.setUsername("identidad");
        given(this.udService.findUserDwarfById(TEST_UD_ID2)).willReturn(ud2);

        GameStorage.getInstance().getGames().clear();
    }

    @Test
    @Order(1)
    public void shouldNotFindAny(){
        Iterable<Game> map = this.gameService.findAll();
        assertThat(StreamSupport.stream(map.spliterator(), false).count()).isEqualTo(0);
    }

    @Test
    @Order(2)
    public void shouldCreateGame(){
        GameStorage.getInstance().getGames().clear();
        Game g = this.gameService.createGame(ud);
        assertThat(g.getPlayer0()).isEqualTo(ud);
        assertThat(g.getId()).isEqualTo(0);
        assertThat(g.getGameStatus()).isEqualTo(GameStatus.NEW);
        assertThat(g.getNumberOfPlayers()).isEqualTo(1);
        assertThat(g.getOrder()).contains(0, 1, 2);
        assertThat(g.getBoard().getId()).isEqualTo(g.getId());
    }

    @Test
    @Order(3)
    public void shouldConnectToGame(){
        Game g = this.gameService.createGame(ud);
        this.gameService.connectToGame(ud2, g.getId());
        assertThat(g.getPlayer0()).isEqualTo(ud);
        assertThat(g.getPlayer1()).isEqualTo(ud2);
    }

    @Test
    @Order(4)
    public void shouldFindAll(){
        Game g = this.gameService.createGame(ud);
        Iterable<Game> map = this.gameService.findAll();
        assertThat(StreamSupport.stream(map.spliterator(), false).count()).isEqualTo(1);
    }

    @Test
    @Order(5)
    public void shouldGetBoard(){
        Game g = this.gameService.createGame(ud);
        Board b = this.gameService.getBoard(g.getId());
        assertThat(b).isEqualTo(g.getBoard());
    }

    @Test
    @Order(6)
    public void shouldFinishGame(){
        Game gg = this.gameService.createGame(ud);
        gg.setWinner(ud.getUsername());
        gg.setRound(2);
        assertThat(StreamSupport.stream(this.gameService.findAll().spliterator(), false).count()).isEqualTo(1);
        this.gameService.finishGame(gg.getId());
        assertThat(StreamSupport.stream(this.gameService.findAll().spliterator(), false).count()).isEqualTo(0);

    }

    @Test
    @Order(7)
    public void shouldSurrender(){
        
        Game g = this.gameService.createGame(ud);
        this.gameService.connectToGame(ud2, g.getId());
        assertThat(g.getPlayer0()).isEqualTo(ud);
        assertThat(g.getPlayer1()).isEqualTo(ud2);
        this.gameService.surrender(g.getId(), ud);
        assertThat(g.getPlayer0()).isEqualTo(ud2);
    }
    
}
