package org.springframework.samples.petclinic.testStatistics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.statistics.Statistics;
import org.springframework.samples.petclinic.statistics.StatisticsRepository;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class testStatisticsService {

    @Autowired
    protected StatisticsService statisticsService;

    @Autowired
    protected StatisticsRepository statisticsRepository;

    @Test
    public void testCountWithInitialData() {
        Long count = statisticsRepository.count();
        assertEquals(count, 3); 
    }

    @Test
    public void testFindAllStatistics(){
        List<Statistics> fAll = (List<Statistics>) statisticsService.findAll();
        assertEquals(fAll.size(), statisticsRepository.count());
        assertThat(fAll.isEmpty()).isFalse();

    }

    @ParameterizedTest
    @CsvSource({"rafjimfer, 1", "serrivroa, 2"})
    public void shouldFindStatisticsWithCorrectId(String username, int id){
        Statistics s = this.statisticsService.findStatisticsByID(id);
        assertThat(s.getUserDwarf().getUsername()).isEqualTo(username);
        assertThat(s.getUserDwarf().getUsername()).isNotEqualTo("rafa");
    }

    @ParameterizedTest
    @CsvSource({"rafjimfer, 1", "serrivroa, 2"})
    public void shouldFindStatisticsWithCorrectUsername(String username, int id){
        Statistics s = this.statisticsService.findStatisticsByUsername(username);
        assertThat(s.getId()).isEqualTo(id);
        assertThat(s.getId()).isNotEqualTo(7);
    }

    @Test
    @Transactional
    public void shouldSaveStatistics(){
        Long count1 = statisticsRepository.count();

        Statistics statistics = new Statistics();
        statistics.setTimePlayed(Duration.ofMinutes(724));
        statistics.setGamesPlayed(18);
        statistics.setGamesWon(12);
        statistics.setTotalIron(67);
        statistics.setTotalGold(51);
        statistics.setTotalSteel(34);
        statistics.setTotalObject(21);
        statistics.setTotalMedal(4);
        statistics.setId(4);

        statisticsService.saveStatistics(statistics);

        Long count2 = statisticsRepository.count();

        assertThat(count1+1).isEqualTo(count2);        
    }
    
    @Test
    @Transactional
    public void shouldDeleteStatistics(){

        Statistics statistics = new Statistics();
        statistics.setTimePlayed(Duration.ofMinutes(724));
        statistics.setGamesPlayed(18);
        statistics.setGamesWon(12);
        statistics.setTotalIron(67);
        statistics.setTotalGold(51);
        statistics.setTotalSteel(34);
        statistics.setTotalObject(21);
        statistics.setTotalMedal(4);
        statistics.setId(3);

        statisticsService.saveStatistics(statistics);

        Long count1 = statisticsRepository.count();

        statisticsService.deleteStatistics(statistics);

        Long count2 = statisticsRepository.count();

        assertThat(count1-1).isEqualTo(count2);        
    } 
}
