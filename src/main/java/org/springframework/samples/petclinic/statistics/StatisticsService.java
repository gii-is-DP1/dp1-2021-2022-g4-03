package org.springframework.samples.petclinic.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;

public class StatisticsService {

    private StatisticsRepository statisticsRepository;

    @Autowired
    StatisticsService(StatisticsRepository statisticsRepository){
        this.statisticsRepository = statisticsRepository;
    }


    @Transactional
    public Iterable<Statistics> findAll(){
        return statisticsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Statistics findStatisticsByUsername(String username) throws DataAccessException{
        return statisticsRepository.findByUsername(username);
    }

    @Transactional
    public void saveStatistics(Statistics statistics) throws DataAccessException{
        statisticsRepository.save(statistics);
    }
    
}
