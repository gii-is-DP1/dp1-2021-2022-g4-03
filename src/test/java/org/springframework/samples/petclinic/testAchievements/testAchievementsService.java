package org.springframework.samples.petclinic.testAchievements;


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
import org.springframework.samples.petclinic.achievements.Achievements;
import org.springframework.samples.petclinic.achievements.AchievementsRepository;
import org.springframework.samples.petclinic.achievements.AchievementsService;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class testAchievementsService {

    @Autowired
    protected AchievementsService achievementsService;

    @Autowired
    protected AchievementsRepository achievementsRepository;


    @Test
    public void testFindAllAchievements(){
        List<Achievements> fAll = (List<Achievements>) achievementsService.findAll();
        assertThat(fAll.size()).isEqualTo(4);
    }

    @ParameterizedTest
    @CsvSource({"totalGold=100, 1", "totalIron=300, 2"})
    public void shouldFindAchievementsWithCorrectId(String x, int id){
        Achievements uD = this.achievementsService.findAchievementById(id);
        assertThat(uD.getCondition()).isEqualTo(x);
        assertThat(uD.getCondition()).isNotEqualTo("gwhraewg");
    }

    @ParameterizedTest
    @CsvSource({"totalGold=100, 1", "totalIron=300, 2"})
    public void shouldFindAchievementsWithCorrectId_Optional(String x, int id){
        Optional<Achievements> optAchievements = this.achievementsService.findByIdOptional(id);
        assertThat(optAchievements.get().getCondition()).isEqualTo(x);
        assertThat(optAchievements.get().getCondition()).isNotEqualTo("rafa");
    }

    @ParameterizedTest
    @ValueSource(strings = {"gamesPlayed=1","totalIron=300"})
    public void ShouldFindAchievementByCondition(String condition){

        Collection<Achievements> uDS = this.achievementsService.findAchievementsByCondition(condition);
        assertThat(uDS.size()).isEqualTo(1);

        uDS = this.achievementsService.findAchievementsByCondition("bdntrhe");
        assertThat(uDS.size()).isEqualTo(0);

    }

    @Test
    @Transactional
    public void ShouldSaveAchievements(){
        Collection<Achievements> achievements = this.achievementsService.findAchievementsByCondition("gamesPlayed=10");
        int found = achievements.size();

        Achievements achievement = new Achievements();
        achievement.setCondition("gamesPlayed=10");
        achievement.setDescription("holi uwu");
        achievement.setLastChange(LocalDate.of(2023, 06, 15));
        achievement.setId(4);
        achievement.setPic("Juega tu primera partida','/resources/images/picaxe.png");

        this.achievementsService.saveAchievement(achievement);
        assertThat(achievement.getId()).isEqualTo(4);
        assertThat(achievement.getCondition()).isEqualTo("gamesPlayed=10");

        Collection<Achievements> u = this.achievementsService.findAchievementsByCondition("gamesPlayed=10");
        assertThat(u.size()).isEqualTo(found+1);

    }

    @Test
    @Transactional
    public void shouldDeleteAchievements() {

        Achievements achievement = new Achievements();
        achievement.setCondition("gamesPlayed=10");
        achievement.setDescription("holi uwu");
        achievement.setLastChange(LocalDate.of(2023, 06, 15));
        achievement.setId(8);
        achievement.setPic("/resources/images/picaxe.png");


        achievementsService.saveAchievement(achievement);
        assertThat(achievementsService.findByIdOptional(8).isPresent());

        achievementsService.delete(achievementsService.findAchievementById(5));

        assertThat(achievementsService.findByIdOptional(8).isEmpty());
	}

}
