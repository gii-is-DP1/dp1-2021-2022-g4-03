package org.springframework.samples.petclinic.testUserDwarf;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
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
        int count = userDwarfService.userDwarfCount();
        assertEquals(count, 3);  //Hay dos usuarios en la base de datos.
    }

    @Test
    public void testFindAllUserDwarf(){
        List<UserDwarf> fAll = (List<UserDwarf>) userDwarfService.findAll();
        assertEquals(fAll.size(), userDwarfService.userDwarfCount());
        assertThat(fAll.isEmpty()).isFalse();

    }

    @ParameterizedTest
    @CsvSource({"rafjimfer, 1", "serrivroa, 2"})
    public void shouldFindUserDwarfWithCorrectId(String username, int id){
        UserDwarf uD = this.userDwarfService.findUserDwarfByUsername2(id);
        assertThat(uD.getUsername()).isEqualTo(username);
        assertThat(uD.getUsername()).isNotEqualTo("rafa");
    }

    @ParameterizedTest
    @CsvSource({"rafjimfer, 1", "serrivroa, 2"})
    public void shouldFindUserDwarfWithCorrectId_Optional(String username, int id){
        Optional<UserDwarf> optUserDwarf = this.userDwarfService.findByIdOptional(id);
        assertThat(optUserDwarf.get().getUsername()).isEqualTo(username);
        assertThat(optUserDwarf.get().getUsername()).isNotEqualTo("rafa");
    }

    @Test
    @Transactional
    public void ShouldSaveUserDwarf(){
        Collection<UserDwarf> users = this.userDwarfService.findUserDwarfByUsername("testSubject");
        int found = users.size();

        UserDwarf userDwarf = new UserDwarf();
        userDwarf.setActive(true);
        userDwarf.setEmail("test@test.com");
        userDwarf.setPass("Testtest123");
        userDwarf.setUsername("testSubject");
        userDwarf.setId(4);

        List<String> roles = new ArrayList<>();
        roles.add("admin");
        this.userDwarfService.saveUserDwarf(userDwarf, roles);

        assertThat(userDwarf.getId()).isEqualTo(4);
        assertThat(userDwarf.getUsername()).isEqualTo("testSubject");

        Collection<UserDwarf> u = this.userDwarfService.findUserDwarfByUsername("testSubject");
        assertThat(u.size()).isEqualTo(found+1);
        
    }

    @ParameterizedTest
    @ValueSource(strings = {"rafjimfer","serrivroa"})
    public void ShouldFindUserDwarfByUsername(String username){

        Collection<UserDwarf> uDS = this.userDwarfService.findUserDwarfByUsername(username);
        assertThat(uDS.size()).isEqualTo(1);

        uDS = this.userDwarfService.findUserDwarfByUsername("ErrorUsername");
        assertThat(uDS.size()).isEqualTo(0);

    }

    @ParameterizedTest
    @ValueSource(strings = {"rafjimfer"})
    public void ShouldFindUserDwarfByUsernameOptional(String username){

        Optional<UserDwarf> uDS = this.userDwarfService.findUserDwarfByUsername2(username);
        assertThat(uDS.get().getUsername()).isEqualTo("rafjimfer");
        assertThat(uDS.get().getId()).isEqualTo(1);
        assertThat(uDS.get().getPass()).isEqualTo("rafa");
    }

    @Test
    @Transactional
    public void shouldDeleteUserDwarf() {

        UserDwarf userDwarf = new UserDwarf();
        userDwarf.setActive(true);
        userDwarf.setEmail("test@test.com");
        userDwarf.setPass("Testtest123");
        userDwarf.setUsername("testSubject");
        userDwarf.setId(4);
        List<String> roles = new ArrayList<>();
        roles.add("admin");

        userDwarfService.saveUserDwarf(userDwarf, roles);
        assertThat(userDwarfService.findByIdOptional(3).isPresent());

        userDwarfService.deleteUserDwarf(userDwarfService.findByIdOptional(3).get());
		
        assertThat(userDwarfService.findByIdOptional(3).isEmpty());
	}




}
