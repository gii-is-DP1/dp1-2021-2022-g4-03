package org.springframework.samples.petclinic.testUserAchievements;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.achievements.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class testUserAchievementsService {

    @Autowired
    protected UserAchievementsService userAchievementsService;

    @Autowired
    protected UserAchievementsRepository userAchievementsRepository;

    @Autowired
    protected AchievementsRepository achievementsRepository;

    @Autowired
    protected AchievementsService achievementsService;

    @ParameterizedTest
    @ValueSource(ints = {1})
    public void shouldFindById(int id){

        UserAchievements uAS = this.userAchievementsService.findUserAchievementsById(id);
        assertThat(uAS.getUserDwarf().getUsername()).isEqualTo("rafjimfer");
        assertThat(uAS.getId()).isEqualTo(1);
        assertThat(uAS.getObtainingDate()).isEqualTo(LocalDate.of(2022, 01, 30));
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    public void shouldFindById2(int id){

        Optional<UserAchievements> uAS = this.userAchievementsService.findUserAchievementsById2(id);
        assertThat(uAS.get().getUserDwarf().getUsername()).isEqualTo("rafjimfer");
        assertThat(uAS.get().getId()).isEqualTo(1);
        assertThat(uAS.get().getObtainingDate()).isEqualTo(LocalDate.of(2022, 01, 30));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldFindById3(int id){

        Collection<UserAchievements> uAS = this.userAchievementsService.findUserAchievementsById3(id);
        assertThat(uAS.size()).isEqualTo(1);

        uAS = this.userAchievementsService.findUserAchievementsById3(244156);
        assertThat(uAS.size()).isEqualTo(0);

    }

   /* @Test
    @Transactional
    public void ShouldSaveUserAchievements(){

        UserDwarf userDwarfTest = new UserDwarf();
        userDwarfTest.setEmail("test@test.com");
        userDwarfTest.setUsername("test");
        userDwarfTest.setPass("testT3st");
        userDwarfTest.setActive(true);

        Achievements achievementTest = new Achievements();
        achievementTest.setCondition("total_games=10");
        achievementTest.setDescription("holi uwu");
        achievementTest.setLastChange(LocalDate.of(2023, 06, 15));
        achievementTest.setPic("fotito");
        achievementTest.setId(62);

        UserAchievements userAchievements = new UserAchievements();
        userAchievements.setProgress(0.7);
        userAchievements.setObtainingDate(LocalDate.of(2019, 06, 06));
        userAchievements.setUserDwarf(userDwarfTest);
        userAchievements.setAchievements(achievementTest);
        userAchievements.setId(29);

        this.achievementsService.saveAchievement(achievementTest);
        this.userAchievementsService.save(userAchievements);

        assertThat(userAchievements.getId()).isEqualTo(29);
        assertThat(userAchievements.getUserDwarf().getUsername()).isEqualTo("test");

        assertThat(userAchievementsService.findUserAchievementsById2(29).isPresent());
    }

    @Test
    @Transactional
    public void shouldDeleteUserAchievements() {

        UserDwarf userDwarfTest = new UserDwarf();
        userDwarfTest.setEmail("test@test.com");
        userDwarfTest.setUsername("test");
        userDwarfTest.setPass("testT3st");
        userDwarfTest.setActive(true);

        Achievements achievementTest = new Achievements();
        achievementTest.setCondition("total_games=10");
        achievementTest.setDescription("holi uwu");
        achievementTest.setLastChange(LocalDate.of(2023, 06, 15));
        achievementTest.setPic("fotito");
        achievementTest.setId(62);

        UserAchievements userAchievements = new UserAchievements();
        userAchievements.setProgress(0.7);
        userAchievements.setObtainingDate(LocalDate.of(2019, 06, 06));
        userAchievements.setUserDwarf(userDwarfTest);
        userAchievements.setAchievements(achievementTest);
        userAchievements.setId(29);

        achievementsService.saveAchievement(achievementTest);

        userAchievementsService.save(userAchievements);
        assertThat(userAchievementsService.findUserAchievementsById2(29).isPresent());

        userAchievementsService.delete(userAchievements);

        assertThat(userAchievementsService.findUserAchievementsById2(29).isEmpty());
	}*/

}
