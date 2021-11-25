package org.springframework.samples.petclinic.testUserDwarf;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfController;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.FilterType;


@WebMvcTest(controllers = UserDwarfController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class testUserDwarfController {
    private static final int TEST_USERDWARF_ID = 1;

	@Autowired
	private UserDwarfController userDwarfController;

	@MockBean
	private UserDwarfService userDwarfService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@Autowired
	private MockMvc mockMvc;

	private UserDwarf tauserk;

    @BeforeEach
    void setup(){
        tauserk= new UserDwarf();
        tauserk.setId(TEST_USERDWARF_ID);
        tauserk.setUsername("Tauserk");
        tauserk.setPass("Taurus98");
        tauserk.setEmail("tauserk@gmail.com");
        tauserk.setActive(true);
        given(this.userDwarfService.findById(TEST_USERDWARF_ID)).willReturn(tauserk);
    }


}
