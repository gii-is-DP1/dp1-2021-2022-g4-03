package org.springframework.samples.petclinic.testAchievements;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.achievements.Achievements;
import org.springframework.samples.petclinic.achievements.AchievementsController;
import org.springframework.samples.petclinic.achievements.AchievementsService;
import org.springframework.samples.petclinic.achievements.UserAchievements;
import org.springframework.samples.petclinic.achievements.UserAchievementsService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.FilterType;

@WebMvcTest(controllers = AchievementsController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class testAchievementsController {

    private static final int TEST_ACHIEVEMENTS_ID1 = 99;
    private static final int TEST_USERACHIEVEMENTS_ID1 = 99;
    private static final int TEST_UD_ID = 99;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AchievementsService aService;
    @MockBean
    private UserAchievementsService uaService;
    @MockBean
    private UserDwarfService userDwarfService;

    private UserDwarf us;
    private Achievements logro1;
    private UserAchievements ulogro1;

    @BeforeEach
    void setup() {
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

        ulogro1 = new UserAchievements();
        ulogro1.setId(TEST_USERACHIEVEMENTS_ID1);
        ulogro1.setUserDwarf(userDwarfService.findUserDwarfById(TEST_UD_ID));
        ulogro1.setAchievements(aService.findAchievementById(TEST_ACHIEVEMENTS_ID1));
        ulogro1.setObtainingDate(LocalDate.of(2015, 8, 8));
        ulogro1.setProgress(0.6);

        given(this.aService.achievementsCount()).willReturn(1);
        given(this.uaService.findUserAchievementsById(TEST_USERACHIEVEMENTS_ID1)).willReturn(ulogro1);
        given(this.aService.findAchievementById(TEST_ACHIEVEMENTS_ID1)).willReturn(logro1);
    }

    @WithMockUser(value = "spring")
    @Test
    void testInitFindForm() throws Exception {
        mockMvc.perform(get("achievements/findAchievements")).andExpect(status().isOk())
                .andExpect(model().attributeExists("achievements"))
                .andExpect(view().name("achievements/findAchievements"));
    }

    @WithMockUser(value = "spring")
    @Test
    void testDeleteAchievements() throws Exception {
        mockMvc.perform(get("/achievements/" + TEST_ACHIEVEMENTS_ID1 + "/delete")).andExpect(status().isOk())
                .andExpect(model().attributeExists("message"))
                .andExpect(view().name("redirect:/achievements/list"));
    }

    @WithMockUser(value = "spring")
    @Test
    void testAchievementsList() throws Exception {
        mockMvc.perform(get("/achievements/list")).andExpect(status().isOk())
                .andExpect(model().attributeExists("achievements"))
                .andExpect(view().name("achievements/achievementsList"));
    }

    @WithMockUser(value = "spring")
    @Test
    void testProcessFindForm() throws Exception {
        mockMvc.perform(get("/achievements").param("condition", "")).andExpect(status().isOk())
                .andExpect(view().name("redirect:/achievements/list"));
    }

}
