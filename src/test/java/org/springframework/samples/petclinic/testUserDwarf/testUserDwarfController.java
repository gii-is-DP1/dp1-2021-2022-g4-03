package org.springframework.samples.petclinic.testUserDwarf;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.achievements.UserAchievementsService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.samples.petclinic.userDwarf.UserDwarfController;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.samples.petclinic.web.CurrentUser;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.FilterType;

@WebMvcTest(controllers = UserDwarfController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class testUserDwarfController {

    private static final int TEST_USERDWARF_ID = 1;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDwarfService userDwarfService;
    @MockBean
    private AuthoritiesService authoritiesService;
    @MockBean
    private StatisticsService statisticsService;
    @MockBean
    private CurrentUser currentUser;
    @MockBean
    private UserAchievementsService userArchievementService;

    private UserDwarf paco;

    @BeforeEach
    void setup() {
        paco = new UserDwarf();
        paco.setActive(true);
        paco.setEmail("email@gmail.com");
        paco.setId(TEST_USERDWARF_ID);
        paco.setPass("Passpass123");
        paco.setUsername("paco");
        Authorities auth = new Authorities();
        auth.setAuthority("admin");
        Set<Authorities> authority = new HashSet<Authorities>();
        authority.add(auth);
        paco.setAuthorities(authority);
        given(this.userDwarfService.findUserDwarfById(TEST_USERDWARF_ID)).willReturn(paco);
        given(this.currentUser.getCurrentUser()).willReturn("paco");
    }

    @WithMockUser(value = "spring")
    @Test
    void UserDwarfListTest() throws Exception {
        mockMvc.perform(get("/usersDwarf/list")).andExpect(status().isOk())
        .andExpect(model().attributeExists(
                "usersDwarf"))
                .andExpect(view().name("/usersDwarf/userDwarfList"));
    }

    @WithMockUser(value = "spring")
    @Test
    void InitCreationFormRegisterTest() throws Exception {
        mockMvc.perform(get("/usersDwarf/register")).andExpect(status().isOk())
                .andExpect(model().attributeExists("wrapper"))
                .andExpect(model().attributeExists("registerCheck"))
                .andExpect(view().name("usersDwarf/createOrUpdateUserDwarfForm"));
    }

    @WithMockUser(value = "spring")
    @Test
    void InitCreationFormTest() throws Exception {
        mockMvc.perform(get("/usersDwarf/new")).andExpect(status().isOk()).andExpect(model().attributeExists("wrapper"))
                .andExpect(model().attributeExists("boolList"))
                .andExpect(view().name("usersDwarf/createOrUpdateUserDwarfForm"));
    }

    @WithMockUser(value = "spring")
    @Test
    void ProcessCreationFormSuccessTest() throws Exception {
        mockMvc.perform(
                post("/usersDwarf/new").param("userDwarf.username", "Francisco").param("userDwarf.pass", "Passpass456")
                        .with(csrf())
                        .param("userDwarf.email", "email2@gmail.com")
                        .param("userDwarf.active", "true")
                        .param("roles", "player"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(value = "spring")
    @Test
    void ProcessCreationFormHasErrorsTest() throws Exception {
        mockMvc.perform(post("/usersDwarf/new")
                .with(csrf())
                .param("userDwarf.username", "Francisco")
                .param("userDwarf.pass", "Passpass456")
                .param("userDwarf.active", "true"))
                .andExpect(status().is(302));
    }

    @WithMockUser(value = "spring")
    @Test
    void ProcessCreationFormSuccessTest2() throws Exception {
        mockMvc.perform(post("/usersDwarf/register").param("userDwarf.username", "Francisco")
                .param("userDwarf.pass", "Passpass456")
                .with(csrf())
                .param("userDwarf.email", "email2@gmail.com")
                .param("userDwarf.active", "true")
                .param("roles", "player"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(value = "spring")
    @Test
    void ProcessCreationFormHasErrorsTest2() throws Exception {
        mockMvc.perform(post("/usersDwarf/register")
                .with(csrf())
                .param("userDwarf.username", "Francisco")
                .param("userDwarf.pass", "Passpass456")
                .param("userDwarf.active", "true"))
                .andExpect(status().is(302));
    }

    @WithMockUser(value = "spring")
    @Test
    void UserDwarfFindTest() throws Exception {
        mockMvc.perform(get("/usersDwarf/find")).andExpect(status().isOk()).andExpect(model().attributeExists(
                "userDwarf"))
                .andExpect(view().name("usersDwarf/findUsers"));
    }

    @WithMockUser(value = "spring")
    @Test
    void InitFindFormTest() throws Exception {
        assertFalse(paco.getUsername().isEmpty());
        mockMvc.perform(get("/usersDwarf/" + paco.getId())).andExpect(status().isOk());
    }

    @WithMockUser(value = "spring")
    @Test
    void ProcessFindFormTest() throws Exception {
        mockMvc.perform(post("/usersDwarf")
                .with(csrf())
                .param("username", "paco"))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "spring")
    @Test
    void initFindFormPlayers() throws Exception {
        mockMvc.perform(get("/userDwarf/searchPlayers")).andExpect(status().isOk())
        .andExpect(model().attributeExists("userDwarf"))
                .andExpect(view().name("userDwarf/findPlayers"));
    }

    ///
    @WithMockUser(value = "spring")
    @Test
    void ShowUserDwarfTest() throws Exception {
        mockMvc.perform(get("/usersDwarf/" + paco.getId())).andExpect(status().isOk())
                .andExpect(model().attributeExists("wrapper"))
                .andExpect(view().name("usersDwarf/userDetails"));
    }

    //
    @WithMockUser(value = "spring")
    @Test
    void processFindFormPlayerTest1() throws Exception {
        assertTrue(paco != null);
        assertTrue(userDwarfService.findUserDwarfByUsername(paco.getUsername()).isEmpty());
        mockMvc.perform(get("/userDwarf/player")).andExpect(status().isOk())
                .andExpect(view().name("redirect:/usersDwarf/list"));
    }

    //
    @WithMockUser(value = "spring")
    @Test
    void processFindFormPlayerTest2() throws Exception {
        assertTrue(paco != null);
        assertTrue(userDwarfService.findUserDwarfByUsername(paco.getUsername()).isEmpty());
        mockMvc.perform(get("/userDwarf/player")).andExpect(status().isOk())
                .andExpect(view().name("redirect:/profile/" + paco.getId()));
    }

    @WithMockUser(value = "spring")
    @Test
    void userDwarfProfileIdTest() throws Exception {
        mockMvc.perform(get("/profile/" + paco.getId())).andExpect(status().isOk())
                .andExpect(view().name("userDwarf/playerProfile"));
    }

    @WithMockUser(value = "spring")
    @Test
    void userDwarfProfileTest() throws Exception {
        mockMvc.perform(get("/profile")).andExpect(status().isOk())
                .andExpect(view().name("usersDwarf/userDwarfProfile"));
    }

    @WithMockUser(value = "spring")
    @Test
    void showUserDwarfTest() throws Exception {
        mockMvc.perform(get("/usersDwarf/" + paco.getId())).andExpect(status().isOk())
                .andExpect(model().attributeExists("wrapper"))
                .andExpect(view().name("usersDwarf/userDetails"));
    }

    @WithMockUser(value = "spring")
    @Test
    void InformationTest() throws Exception {
        mockMvc.perform(get("/information")).andExpect(status().isOk())
                .andExpect(model().attributeExists("wrapper"))
                .andExpect(view().name("aboutUs"));
    }

    @WithMockUser(value = "spring")
    @Test
    void InitUpdateUserDwarfFormTest() throws Exception {
        mockMvc.perform(get("/usersDwarf/{userDwarfId}/edit", TEST_USERDWARF_ID)).andExpect(status().isOk())
                .andExpect(model().attributeExists("wrapper"))
                .andExpect(model().attribute("wrapper", hasProperty("userDwarf")))
                .andExpect(view().name("usersDwarf/createOrUpdateUserDwarfForm"));
    }

    @WithMockUser(value = "spring")
    @Test
    void ProcessUpdateUserDwarfFormTest() throws Exception {
        mockMvc.perform(post("/usersDwarf/{userDwarfId}/edit", TEST_USERDWARF_ID)
                .param("userDwarf.username", "Francisco").param("userDwarf.pass", "Passpass456")
                .with(csrf())
                .param("userDwarf.email", "email2@gmail.com")
                .param("userDwarf.active", "true")
                .param("roles", "player"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/usersDwarf/{userDwarfId}"));
    }

    @WithMockUser(value="spring")
    @Test
    void deleteUserDwarfTest() throws Exception{
        Integer id=paco.getId();
        assertTrue(paco.isActive());
        mockMvc.perform(get("/usersDwarf/"+paco.getId()+"/delete"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/usersDwarf/list"));
        assertTrue(userArchievementService.findUserAchievementsById(id)==null);
        
    }

}