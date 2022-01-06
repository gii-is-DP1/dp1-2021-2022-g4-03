package org.springframework.samples.petclinic.testUserAchievements;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.samples.petclinic.achievements.Achievements;
import org.springframework.samples.petclinic.achievements.AchievementsService;
import org.springframework.samples.petclinic.achievements.UserAchievements;
import org.springframework.samples.petclinic.achievements.UserAchievementsService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.statistics.StatisticsController;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import org.springframework.samples.petclinic.statistics.Statistics;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.samples.petclinic.web.CurrentUser;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.FilterType;

@WebMvcTest(controllers = StatisticsController.class,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
             classes = WebSecurityConfigurer.class),excludeAutoConfiguration= SecurityConfiguration.class)

public class testUserAchievementController {

    private static final int TEST_UD_ID = 1;
    private static final int TEST_USERACHIEVEMENTS_ID1 = 1;
    private static final int TEST_USERACHIEVEMENTS_ID2 = 2;
    private static final int TEST_ACHIEVEMENTS_ID1 = 1;
    private static final int TEST_ACHIEVEMENTS_ID2 = 2;

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
    

   

    private UserDwarf us;
    private Achievements logro1;
    private Achievements logro2;
    private UserAchievements ulogro1;
    private UserAchievements ulogro2;
    private Collection<UserAchievements> ulogros;

    @BeforeEach
    void setup(){

        ulogros = new ArrayList<UserAchievements>();

        us = new UserDwarf();
        us.setActive(true);
        us.setEmail("email@gmail.com");
        us.setId(TEST_UD_ID);
        us.setPass("Passpass123");
        us.setUsername("pancho");
        given(this.userDwarfService.findUserDwarfById(TEST_UD_ID)).willReturn(us);

        logro1 = new Achievements();
        logro1.setCondition("total_gold=200");
        logro1.setDescription("Consigue 200 de oro");
        logro1.setPic("foto1");
        logro1.setLastChange(LocalDate.of(2009, 9, 9));
        logro1.setId(TEST_ACHIEVEMENTS_ID1);

        logro2 = new Achievements();
        logro2.setCondition("total_iron=200");
        logro2.setDescription("Consigue 200 de hierro");
        logro2.setPic("foto");
        logro2.setLastChange(LocalDate.of(2009, 9, 9));
        logro2.setId(TEST_ACHIEVEMENTS_ID1);

        ulogro1 = new UserAchievements();
        ulogro1.setId(TEST_USERACHIEVEMENTS_ID1);
        ulogro1.setUserDwarf(userDwarfService.findUserDwarfById(TEST_UD_ID));
        ulogro1.setAchievements(achievementsService.findAchievementById(TEST_ACHIEVEMENTS_ID1));
        ulogro1.setObtainingDate(LocalDate.of(2015, 8, 8));
        ulogro1.setProgress(0.6);

        ulogro2 = new UserAchievements();
        ulogro2.setId(TEST_USERACHIEVEMENTS_ID2);
        ulogro2.setUserDwarf(userDwarfService.findUserDwarfById(TEST_UD_ID));
        ulogro2.setAchievements(achievementsService.findAchievementById(TEST_ACHIEVEMENTS_ID2));
        ulogro2.setObtainingDate(LocalDate.of(2019, 8, 8));
        ulogro2.setProgress(0.2);

        ulogros.add(ulogro1);
        ulogros.add(ulogro2);

        given(this.userAchievementsService.findByUser(us.getUsername())).willReturn(ulogros);

    }

    //Logros de otro jugador
    @WithMockUser(value = "spring")
    @Test
    void testPlayerAchievementsProfile() throws Exception {
        //http://localhost:8080/profile/playerAchievements/1?pA=1
        given(this.userAchievementsService.findByUser(us.getUsername())).willReturn(ulogros);

        mockMcv.perform(get("/profile/playerAchievements/" + us.getUsername()))
        .andExpect(view().name("profile/playerAchievements/1?pA=" + TEST_UD_ID));

    }
    

    //Logros del propio usuario
    @WithMockUser(value = "spring")
    @Test
    void testUserDwarfAchievementsProfile() throws Exception {

        mockMcv.perform(get("/profile/achievements"))
        .andExpect(view().name("achievements/achievementsProfile"));

    }




    
}
