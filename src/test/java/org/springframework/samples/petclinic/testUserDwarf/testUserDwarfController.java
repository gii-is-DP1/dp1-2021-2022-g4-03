package org.springframework.samples.petclinic.testUserDwarf;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.samples.petclinic.userDwarf.UserDwarfController;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.FilterType;


@WebMvcTest(controllers = UserDwarfController.class,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
             classes = WebSecurityConfigurer.class),excludeAutoConfiguration= SecurityConfiguration.class)

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

    private UserDwarf paco;

    @BeforeEach
    void setup(){
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
    }


    @WithMockUser(value="spring")
    @Test
    void UserDwarfListTest() throws Exception{
        mockMvc.perform(get("/usersDwarf/list")).andExpect(status().isOk()).andExpect(model().attributeExists(
            "usersDwarf"))
            .andExpect(view().name("/usersDwarf/userDwarfList"));
    }

    @WithMockUser(value="spring")
    @Test
    void InitCreationFormRegisterTest() throws Exception {
		mockMvc.perform(get("/usersDwarf/register")).andExpect(status().isOk()).andExpect(model().attributeExists("wrapper"))
                .andExpect(model().attributeExists("registerCheck"))
				.andExpect(view().name("usersDwarf/createOrUpdateUserDwarfForm"));
	}


}
