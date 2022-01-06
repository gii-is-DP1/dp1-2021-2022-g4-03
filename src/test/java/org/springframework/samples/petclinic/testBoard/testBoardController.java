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
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.context.annotation.FilterType;

@WebMvcTest(controllers = BoardController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class testBoardController {

	@MockBean
	private BoardService boardService;

    @MockBean
    private StatisticsService statisticsService;

    @MockBean
    private GameService gameService;

    @MockBean
    private CardService cardService;

    @MockBean
    private UserDwarfService userDwarfService;


	@Autowired
	private MockMvc mockMvc;

	private Board mockBoard;

    private Game mockGame;

    @BeforeEach
    void setup(){

        mockBoard = new Board();
        mockGame = new Game();

    }

    @WithMockUser(value = "spring")
    @Test
    void testJoinBoard() throws Exception{

        given(this.boardService.findById2(1)).willReturn(mockBoard);

        mockMvc.perform(get("/board/"+mockGame.getId())).andExpect(status().isOk())
        .andExpect(view().name("game/board"));
    }

}
