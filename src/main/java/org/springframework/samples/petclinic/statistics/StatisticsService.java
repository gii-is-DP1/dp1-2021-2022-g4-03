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
        return this.statisticsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Statistics findStatisticsByID(Integer id) throws DataAccessException{
        return this.statisticsRepository.findByID(id);
    }    

    @Transactional(readOnly = true)
    public Statistics findStatisticsByUsername(String username) throws DataAccessException{
        return this.statisticsRepository.findByUsername(username);
    }

    @Transactional
    public void saveStatistics(Statistics statistics) throws DataAccessException{
        statisticsRepository.save(statistics);
    }
    @Transactional
    public Optional<Statistics> findStatisticsByUsername2(String username) throws DataAccessException{
		return this.statisticsRepository.findByUsername2(username);
	}
    public void deleteStatistics(Statistics statistics){
        statisticsRepository.delete(statistics);
    }
    @Transactional(readOnly = true)
    public Long getAllGamesPlayed(){
        return this.statisticsRepository.getAllGamesPlayed();
    }
    @Transactional(readOnly = true)
    public Long getAllGamesWon(){
        return this.statisticsRepository.getAllGamesWon();
    }
    @Transactional(readOnly = true)
    public Long getAllIron(){
        return this.statisticsRepository.getAllIron();
    }
    @Transactional(readOnly = true)
    public Long getAllGold(){
        return this.statisticsRepository.getAllGold();
    }
    @Transactional(readOnly = true)
    public Long getAllSteel(){
        return this.statisticsRepository.getAllSteel();
    }
    @Transactional(readOnly = true)
    public Long getAllObject(){
        return this.statisticsRepository.getAllObject();
    }
    @Transactional(readOnly = true)
    public Long getAllMedal(){
        return this.statisticsRepository.getAllMedal();
    }
    @Transactional(readOnly = true)
    public Long getAllTimePlayed(){
        return this.statisticsRepository.getAllTimePlayed()/1000000000   ;
    }
    @Transactional(readOnly = true)
    public List<String> getRank(){
        return this.statisticsRepository.getRank();
    }

    @Transactional(readOnly = true)
    public List<Integer> getRankGW(){
        return this.statisticsRepository.getRankGW();
    }
    @Transactional(readOnly = true)
    public Long getMediaOro(){
        return this.statisticsRepository.getMediaOro();
    }
    @Transactional(readOnly = true)
    public Long getMediaHierro(){
        return this.statisticsRepository.getMediaHierro();
    }
    
    @Transactional(readOnly = true)
    public Long getMediaAcero(){
        return this.statisticsRepository.getMediaAcero();
    }
    
    @Transactional(readOnly = true)
    public Long getMediaObjetos(){
        return this.statisticsRepository.getMediaObjetos();
    }
    @Transactional(readOnly = true)
    public Long getMediaMedallas(){
        return this.statisticsRepository.getMediaMedallas();
    }
    @Transactional(readOnly = true)
    public Long getMediaPartidas(){
        return this.statisticsRepository.getMediaPartidas();
    }
    @Transactional(readOnly = true)
    public Long getMinGold(){
        return this.statisticsRepository.getMinGold();
    }
    @Transactional(readOnly = true)
    public Long getMinIron(){
        return this.statisticsRepository.getMinIron();
    }
    @Transactional(readOnly = true)
    public Long getMinSteel(){
        return this.statisticsRepository.getMinSteel();
    }
    @Transactional(readOnly = true)
    public Long getMinObject(){
        return this.statisticsRepository.getMinObject();
    }
    @Transactional(readOnly = true)
    public Long getMaxGold(){
        return this.statisticsRepository.getMaxGold();
    }
    @Transactional(readOnly = true)
    public Long getMaxIron(){
        return this.statisticsRepository.getMaxIron();
    }
    
    @Transactional(readOnly = true)
    public Long getMaxSteel(){
        return this.statisticsRepository.getMaxSteel();
    }
    @Transactional(readOnly = true)
    public Long getMaxObject(){
        return this.statisticsRepository.getMaxObject();
    }
    @Transactional(readOnly = true)
    public Long getMaxTimePlayed(){
        return this.statisticsRepository.getMaxTimePlayed()/1000000000 ;
    }
    @Transactional(readOnly = true)
    public Long getUserMinGold(String username){
        return this.statisticsRepository.getUserMinGold(username);
    }
    
    

    


    
}
