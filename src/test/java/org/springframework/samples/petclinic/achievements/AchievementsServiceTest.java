package org.springframework.samples.petclinic.achievements;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AchievementsServiceTest {

    @Autowired
    protected AchievementsService achievementsService;

    @Autowired
    protected AchievementsRepository achievementsRepository;


    @Test
    public void testFindAllAchievements(){
        List<Achievements> fAll = (List<Achievements>) achievementsService.findAll();
        assertThat(fAll.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource({"total_gold=100, 1", "total_iron=300, 2"})
    public void shouldFindAchievementsWithCorrectId(String x, int id){
        Achievements uD = this.achievementsService.findAchievementById(id);
        assertThat(uD.getCondition()).isEqualTo(x);
        assertThat(uD.getCondition()).isNotEqualTo("gwhraewg");
    }

    @ParameterizedTest
    @CsvSource({"total_gold=100, 1", "total_iron=300, 2"})
    public void shouldFindAchievementsWithCorrectId_Optional(String x, int id){
        Optional<Achievements> optAchievements = this.achievementsService.findByIdOptional(id);
        assertThat(optAchievements.get().getCondition()).isEqualTo(x);
        assertThat(optAchievements.get().getCondition()).isNotEqualTo("rafa");
    }

    @ParameterizedTest
    @ValueSource(strings = {"total_games=1","total_iron=300"})
    public void ShouldFindAchievementByCondition(String condition){

        Collection<Achievements> uDS = this.achievementsService.findAchievementsByCondition(condition);
        assertThat(uDS.size()).isEqualTo(1);

        uDS = this.achievementsService.findAchievementsByCondition("bdntrhe");
        assertThat(uDS.size()).isEqualTo(0);

    }

    @Test
    @Transactional
    public void ShouldSaveAchievements(){
        Collection<Achievements> achievements = this.achievementsService.findAchievementsByCondition("total_games=10");
        int found = achievements.size();

        Achievements achievement = new Achievements();
        achievement.setCondition("total_games=10");
        achievement.setDescription("holi uwu");
        achievement.setLastChange(LocalDate.of(2023, 06, 15));
        achievement.setId(4);

        this.achievementsService.saveAchievement(achievement);
        assertThat(achievement.getId()).isEqualTo(4);
        assertThat(achievement.getCondition()).isEqualTo("total_games=10");

        Collection<Achievements> u = this.achievementsService.findAchievementsByCondition("total_games=10");
        assertThat(u.size()).isEqualTo(found+1);
        
    }

    @Test
    @Transactional
    public void shouldDeleteAchievements() {

        Achievements achievement = new Achievements();
        achievement.setCondition("total_games=10");
        achievement.setDescription("holi uwu");
        achievement.setLastChange(LocalDate.of(2023, 06, 15));
        achievement.setId(4);



        achievementsService.saveAchievement(achievement);
        assertThat(achievementsService.findByIdOptional(3).isPresent());

        achievementsService.delete(achievementsService.findByIdOptional(3).get());
		
        assertThat(achievementsService.findByIdOptional(3).isEmpty());
	}
    
}
