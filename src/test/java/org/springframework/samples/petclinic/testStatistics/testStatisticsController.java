package org.springframework.samples.petclinic.testStatistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.statistics.Statistics;
import org.springframework.samples.petclinic.statistics.StatisticsController;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StatisticsController.class,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
             classes = WebSecurityConfigurer.class),excludeAutoConfiguration= SecurityConfiguration.class)
public class testStatisticsController {

    private static final int TEST_UD_ID = 1;
    private static final int TEST_STATISTICS_ID = 1;

    @Autowired
    private MockMvc mockMcv;
    @MockBean
    private UserDwarfService userDwarfService;
    @MockBean
    private StatisticsService statisticsService;
    @MockBean
    private AuthoritiesService authoritiesService;

    private UserDwarf paco;
    private Statistics pacoStatistics;

    @BeforeEach
    void setup(){

        paco = new UserDwarf();
        paco.setActive(true);
        paco.setEmail("email@gmail.com");
        paco.setId(TEST_UD_ID);
        paco.setPass("Passpass123");
        paco.setUsername("paco");
        given(this.userDwarfService.findUserDwarfById(TEST_UD_ID)).willReturn(paco);

        pacoStatistics = new Statistics();
        pacoStatistics.setGamesPlayed(2);
        pacoStatistics.setGamesWon(1);
        pacoStatistics.setId(TEST_STATISTICS_ID);
        Duration d = Duration.ofMinutes(32);
        pacoStatistics.setTimePlayed(d);
        pacoStatistics.setTotalGold(25);
        pacoStatistics.setTotalIron(56);
        pacoStatistics.setTotalMedal(4);
        pacoStatistics.setTotalObject(6);
        pacoStatistics.setTotalSteel(15);
        pacoStatistics.setUserDwarf(paco);
        given(this.statisticsService.findStatisticsByUsername(paco.getUsername())).willReturn(pacoStatistics);


    }

    @WithMockUser(value = "spring")
    @Test
    void testInitStatistics() throws Exception{
        mockMcv.perform(get("/statistics")).andExpect(status().isOk()).andExpect(model().attributeExists("statistics"))
        .andExpect(view().name("statistics/findStatistics"));
    }

    @WithMockUser(value = "spring")
    @Test
    void testProcessStatisticByUsername() throws Exception{

        given(this.statisticsService.findStatisticsByUsername(paco.getUsername())).willReturn(pacoStatistics);

        mockMcv.perform(get("/statistics/player").param("userDwarf", "paco")).andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/statistics/player/" + TEST_STATISTICS_ID));
    }

    @WithMockUser(value = "spring")
    @Test
    void testProcessStatisticEmpty() throws Exception{
        given(this.statisticsService.findStatisticsByUsername("")).willReturn(null);

        mockMcv.perform(get("/statistics/player").param("userDwarf", "")).andExpect(status().is2xxSuccessful()).andExpect(view().name("statistics" +
            "/findStatistics"));
    }

    @WithMockUser(value = "spring")
    @Test
    void testShowStatistics() throws Exception{

        given(this.statisticsService.findStatisticsByID(TEST_STATISTICS_ID)).willReturn(pacoStatistics);

        mockMcv.perform(get("/statistics/player/{statisticId}", TEST_STATISTICS_ID)).andExpect(status().isOk())
        .andExpect(model().attribute("statistics", hasProperty("timePlayed", is(pacoStatistics.getTimePlayed()))))
        .andExpect(model().attribute("statistics", hasProperty("gamesPlayed", is(2))))
        .andExpect(model().attribute("statistics", hasProperty("gamesWon", is(1))))
        .andExpect(model().attribute("statistics", hasProperty("totalIron", is(56))))
        .andExpect(model().attribute("statistics", hasProperty("totalGold", is(25))))
        .andExpect(model().attribute("statistics", hasProperty("totalSteel", is(15))))
        .andExpect(model().attribute("statistics", hasProperty("totalObject", is(6))))
        .andExpect(model().attribute("statistics", hasProperty("totalMedal", is(4))))
        .andExpect(view().name("/statistics/statisticsList"));

    }













}
