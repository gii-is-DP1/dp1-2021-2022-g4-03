package org.springframework.samples.petclinic.testUserDwarf;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfController;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.context.annotation.FilterType;


@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes=UserDwarfController.class)

@WebMvcTest(value=UserDwarfController.class, excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class testUserDwarfController {
    private static final Integer TEST_USERDWARF_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
	private UserDwarfService userDwarfService;

    @Autowired
	private UserDwarfController userDwarfController;

	@MockBean
	private AuthoritiesService authoritiesService;

	@Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup(){
        UserDwarf userDwarf= new UserDwarf();
        userDwarf.setId(TEST_USERDWARF_ID);
        userDwarf.setUsername("Tauserk");
        userDwarf.setPass("Taurus98");
        userDwarf.setEmail("tauserk@gmail.com");
        userDwarf.setActive(true);

        Authorities auth = new Authorities();
        auth.setAuthority("player");
        Set<Authorities> authority = new HashSet<Authorities>();
        authority.add(auth);
        userDwarf.setAuthorities(authority);

        Mockito.when(this.userDwarfService.findByIdOptional(TEST_USERDWARF_ID)).thenReturn(Optional.of(userDwarf));
    }

    
    @WithMockUser(value="spring")
    @Test
    void userDwarfDetailsTest() throws Exception{
        mockMvc.perform(get("/userDwarf/list")).andExpect(status().isOk()).andExpect(model().attributeExists("userDwarf"))
            .andExpect(view().name("userDwarf/usersDwarfList"));
    }


}
