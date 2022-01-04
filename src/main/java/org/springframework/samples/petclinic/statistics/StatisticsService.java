package org.springframework.samples.petclinic.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

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
    @Transactional
    public Optional<Statistics> findStatisticsByUsername2(String username) throws DataAccessException{
		return statisticsRepository.findByUsername2(username);
	}
    public void deleteStatistics(Statistics statistics){
        statisticsRepository.delete(statistics);
    }
    @Transactional(readOnly = true)
    public Long getAllGamesPlayed(){
        return statisticsRepository.getAllGamesPlayed();
    }
    @Transactional(readOnly = true)
    public Long getAllGamesWon(){
        return statisticsRepository.getAllGamesWon();
    }
    @Transactional(readOnly = true)
    public Long getAllIron(){
        return statisticsRepository.getAllIron();
    }
    @Transactional(readOnly = true)
    public Long getAllGold(){
        return statisticsRepository.getAllGold();
    }
    @Transactional(readOnly = true)
    public Long getAllSteel(){
        return statisticsRepository.getAllSteel();
    }
    @Transactional(readOnly = true)
    public Long getAllObject(){
        return statisticsRepository.getAllObject();
    }
    @Transactional(readOnly = true)
    public Long getAllMedal(){
        return statisticsRepository.getAllMedal();
    }
    @Transactional(readOnly = true)
    public Long getAllTimePlayed(){
        return statisticsRepository.getAllTimePlayed()/1000000000   ;
    }
    @Transactional(readOnly = true)
    public List<String> getRank(){
        return statisticsRepository.getRank();
    }

    @Transactional(readOnly = true)
    public List<Integer> getRankGW(){
        return statisticsRepository.getRankGW();
    }

    
}
