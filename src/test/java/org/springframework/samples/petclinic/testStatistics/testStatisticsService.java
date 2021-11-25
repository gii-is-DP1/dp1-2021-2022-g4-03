package org.springframework.samples.petclinic.testStatistics;

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
import org.springframework.samples.petclinic.statistics.Statistics;
import org.springframework.samples.petclinic.statistics.StatisticsRepository;
import org.springframework.samples.petclinic.statistics.StatisticsService;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class testStatisticsService {

    @Autowired
    protected StatisticsService statisticsService;

    @Autowired
    protected StatisticsRepository statisticsRepository;

    @Test
    public void testCountWithInitialData() {
        Long count = statisticsRepository.count();
        assertEquals(count, 2);  //Hay dos estadísticas en la base de datos.
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
    
}
