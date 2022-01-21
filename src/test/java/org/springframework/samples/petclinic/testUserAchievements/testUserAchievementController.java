package org.springframework.samples.petclinic.testUserAchievements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.achievements.*;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.statistics.Statistics;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.samples.petclinic.web.CurrentUser;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = UserAchievementsController.class,excludeFilters = @ComponentScan.Filter(type =
    FilterType.ASSIGNABLE_TYPE,
             classes = WebSecurityConfigurer.class),excludeAutoConfiguration= SecurityConfiguration.class)

public class testUserAchievementController {

    private static final int TEST_UD_ID = 1;
    private static final int TEST_USERACHIEVEMENTS_ID1 = 1;
    private static final int TEST_USERACHIEVEMENTS_ID2 = 20;
    private static final int TEST_ACHIEVEMENTS_ID1 = 1;
    private static final int TEST_ACHIEVEMENTS_ID2 = 20;
    private static final int TEST_STATISTICS_ID = 1;

    @Autowired
    private MockMvc mockMcv;
    @MockBean
    private UserDwarfService userDwarfService;
    @MockBean
    private AchievementsService achievementsService;
    @MockBean
    private UserAchievementsService userAchievementsService;

    @MockBean
    private StatisticsService statisticsService;
    @MockBean
    private AuthoritiesService authoritiesService;

    @MockBean
    private CurrentUser currentUser;


    private UserDwarf us;
    private Achievements logro1;
    private UserAchievements ulogro1;
    private Statistics pacoStatistics;

    @BeforeEach
    void setup(){

        us = new UserDwarf();
        us.setActive(true);
        us.setEmail("email@gmail.com");
        us.setId(TEST_UD_ID);
        us.setPass("Passpass123");
        us.setUsername("pancho");
        given(this.userDwarfService.findUserDwarfById(TEST_UD_ID)).willReturn(us);

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
        pacoStatistics.setUserDwarf(us);
        given(this.statisticsService.findStatisticsByUsername(us.getUsername())).willReturn(pacoStatistics);

        logro1 = new Achievements();
        logro1.setCondition("totalGold=200");
        logro1.setDescription("Consigue 200 de oro");
        logro1.setPic("foto1");
        logro1.setLastChange(LocalDate.of(2009, 9, 9));
        logro1.setId(TEST_ACHIEVEMENTS_ID1);

        ulogro1 = new UserAchievements();
        ulogro1.setId(TEST_USERACHIEVEMENTS_ID1);
        ulogro1.setUserDwarf(userDwarfService.findUserDwarfById(TEST_UD_ID));
        ulogro1.setAchievements(achievementsService.findAchievementById(TEST_ACHIEVEMENTS_ID1));
        ulogro1.setObtainingDate(LocalDate.of(2015, 8, 8));
        ulogro1.setProgress(0.6);

        given(this.statisticsService.findStatisticsByUsername2(us.getUsername())).willReturn(Optional.ofNullable(pacoStatistics));
        given(this.achievementsService.achievementsCount()).willReturn(1);
        given(this.userAchievementsService.findUserAchievementsById(TEST_USERACHIEVEMENTS_ID1)).willReturn(ulogro1);
        given(this.achievementsService.findAchievementById(TEST_ACHIEVEMENTS_ID1)).willReturn(logro1);
    }

    //Logros de otro jugador
    @WithMockUser(value = "spring")
    @Test
    void testPlayerAchievementsProfile() throws Exception {
        given(this.userDwarfService.findUserDwarfById(us.getId())).willReturn(us);

        mockMcv.perform(get("/profile/playerAchievements/" + us.getId()))
        .andExpect(view().name("achievements/achievementsProfile"));

    }


    //Logros del propio usuario
    @WithMockUser(value = "spring")
    @Test
    void testUserDwarfAchievementsProfile() throws Exception {

        given(this.currentUser.getCurrentUser()).willReturn(us.getUsername());
        given(this.statisticsService.findStatisticsByUsername(currentUser.getCurrentUser())).willReturn(pacoStatistics);
        given(this.achievementsService.achievementsCount()).willReturn(1);
        given(this.userAchievementsService.findUserAchievementsByAchievementsIdAndUserUsername(1, us.getUsername())).willReturn(ulogro1);

        mockMcv.perform(get("/profile/achievements"))
        .andExpect(view().name("achievements/achievementsProfile"));

    }

}
