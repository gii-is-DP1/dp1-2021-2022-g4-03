package org.springframework.samples.petclinic.testUserDwarf;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.userDwarf.UserDwarfRepository;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class testUserDwarfService {
    
    @Autowired
    protected UserDwarfService userDwarfService;

    @Autowired
    protected UserDwarfRepository userDwarfRepository;

    @Test
    public void testCountWithInitialData() {

        // List<UserDwarf> userDwarf = (List<UserDwarf>) this.userDwarfService.findAll();
        // assertThat(userDwarf.size()).isEqualTo(this.userDwarfService.userDwarfCount());

        int count = userDwarfService.userDwarfCount();
        assertEquals(count,2);

    }


}
