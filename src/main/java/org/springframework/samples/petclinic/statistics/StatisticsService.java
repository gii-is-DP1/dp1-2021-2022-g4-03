package org.springframework.samples.petclinic.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
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
    public Statistics findStatisticsByID(Integer id) throws DataAccessException{
        return statisticsRepository.findByID(id);
    }    

    @Transactional(readOnly = true)
    public Statistics findStatisticsByUsername(String username) throws DataAccessException{
        return statisticsRepository.findByUsername(username);
    }

    @Transactional
    public void saveStatistics(Statistics statistics) throws DataAccessException{
        statisticsRepository.save(statistics);
    }

    public void deleteStatistics(Statistics statistics){
        statisticsRepository.delete(statistics);
    }
    
}
