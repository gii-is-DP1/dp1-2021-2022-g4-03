package org.springframework.samples.petclinic.testBoard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.board.BoardController;
import org.springframework.samples.petclinic.board.BoardService;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.game.*;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.samples.petclinic.web.CurrentUser;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.context.annotation.FilterType;

import javax.swing.plaf.ColorUIResource;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.*;

@WebMvcTest(controllers = BoardController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class testBoardController {

	@MockBean
	private BoardService boardService;

    @MockBean
    private StatisticsService statisticsService;

    @MockBean
    private GameService gameService;

    @MockBean
    private GameStorage gameStorage;

    @MockBean
    private CardService cardService;

    @MockBean
    private CurrentUser currentUser;

    @MockBean
    private UserDwarfService userDwarfService;


	@Autowired
	private MockMvc mockMvc;

	private Board mockBoard;

    private Game mockGame;

    private Card mockCard;

    private UserDwarf mockPlayer;

    @BeforeEach
    void setup(){

        mockPlayer = new UserDwarf();
        mockPlayer.setActive(true);
        mockPlayer.setEmail("email@gmail.com");
        mockPlayer.setId(1);
        mockPlayer.setPass("Passpass123");
        mockPlayer.setUsername("mockPlayer");
        given(this.userDwarfService.findUserDwarfById(1)).willReturn(mockPlayer);

        mockBoard = new Board(0);

        mockGame = new Game();
        mockGame.setId(0);
        mockGame.setPlayer0(mockPlayer);
        mockGame.setBoard(mockBoard);
        List<Integer> order = new ArrayList<>(List.of(0, 1, 2));
        Collections.shuffle(order,
            new Random(LocalTime.now().getLong(ChronoField.NANO_OF_DAY)));
        mockGame.setOrder(order);
        mockGame.setPhase(Phase.INICIO);
        mockGame.setGameStatus(GameStatus.NEW);
        mockCard= new Card();
        gameStorage.getInstance().setGame(mockGame);

        given(this.boardService.findById2(0)).willReturn(mockBoard);
        given(this.userDwarfService.findUserDwarfByUsername2("mockPlayer")).willReturn(Optional.ofNullable(mockPlayer));
        given(gameStorage.getGame(0)).willReturn(mockGame);
        given(currentUser.getCurrentUser()).willReturn("mockPlayer");

    }

    @WithMockUser(value = "spring")
    @Test
    void testJoinBoard() throws Exception{


        mockMvc.perform(get("/board/"+mockGame.getId())).andExpect(status().isOk())
        .andExpect(model().attributeExists("game"))
        .andExpect(model().attributeExists("clientData"))
        .andExpect(model().attributeExists("currentUser"))
        .andExpect(model().attributeExists("now"))
        .andExpect(view().name("game/board"));
    }

    @WithMockUser(value = "spring")
    @Test
    void getCardTest() throws Exception{


        mockMvc.perform(get("/api/card/"+mockCard.getId())).andExpect(status().isOk());
    }



}
