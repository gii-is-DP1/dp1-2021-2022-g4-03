package org.springframework.samples.petclinic.testStatistics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import org.assertj.core.util.Lists;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.statistics.StatisticsController;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import org.springframework.samples.petclinic.statistics.Statistics;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.FilterType;

@WebMvcTest(controllers = StatisticsController.class,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
             classes = WebSecurityConfigurer.class),excludeAutoConfiguration= SecurityConfiguration.class)
public class testStatisticsController {

    private static final int TEST_UD_ID = 1;
    private static final int TEST_STATISTICS_ID = 1;

    @Autowired
    private MockMvc mockMcv;
    @Autowired
    private UserDwarf paco;
    @Autowired
    private Statistics pacoStatistics;
    @MockBean
    private UserDwarfService userDwarfService;
    @MockBean
    private StatisticsService statisticsService;
    @MockBean
    private AuthoritiesService authoritiesService; 

    @BeforeEach
    void setup(){

        UserDwarf paco = new UserDwarf();
        paco.setActive(true);
        paco.setEmail("email@gmail.com");
        paco.setId(TEST_UD_ID);
        paco.setPass("Passpass123");
        paco.setUsername("paco");         
        given(this.userDwarfService.findUserDwarfById(TEST_UD_ID)).willReturn(paco);
       
        Statistics pacoStatistics = new Statistics();
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

        mockMcv.perform(get("/statistics/player").param("username", "paco")).andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/statistics/player/" + TEST_STATISTICS_ID));
    }

    @WithMockUser(value = "spring")
    @Test
    void testProcessStatisticEmpty() throws Exception{
        given(this.statisticsService.findStatisticsByUsername("")).willReturn(pacoStatistics, new Statistics());

        mockMcv.perform(get("/statistics/player")).andExpect(status().isOk()).andExpect(view().name("statistics/findStatistics"));
    }

    @WithMockUser(value = "spring")
    @Test
    void testShowStatistics() throws Exception{


        mockMcv.perform(get("/statistics/player/{statisticId}", TEST_STATISTICS_ID)).andExpect(status().isOk())
        .andExpect(model().attribute("statistics", hasProperty("timePlayed", is(eq(pacoStatistics.getTimePlayed())))))
        .andExpect(model().attribute("statistics", hasProperty("gamesPlayed", is(eq(2)))))
        .andExpect(model().attribute("statistics", hasProperty("gamesWon", is(eq(1)))))
        .andExpect(model().attribute("statistics", hasProperty("totalIron", is(eq(56)))))
        .andExpect(model().attribute("statistics", hasProperty("totalGold", is(eq(25)))))
        .andExpect(model().attribute("statistics", hasProperty("totalSteel", is(eq(6)))))
        .andExpect(model().attribute("statistics", hasProperty("totalObject", is(eq(15)))))
        .andExpect(model().attribute("statistics", hasProperty("totalMedal", is(eq(4)))))
        .andExpect(view().name("/statistics/statisticsList"));

    }

    


    




    


    
}
