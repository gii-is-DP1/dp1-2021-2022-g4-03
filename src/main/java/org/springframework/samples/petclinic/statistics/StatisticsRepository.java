package org.springframework.samples.petclinic.statistics;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatisticsRepository extends CrudRepository<Statistics, Integer>{

    Collection<Statistics> findAll() throws DataAccessException;

    @Query("SELECT statistics FROM Statistics statistics WHERE statistics.id = :id")
    public Statistics findByID(@Param("id") int id) throws DataAccessException;

    @Query("SELECT statistics FROM Statistics statistics WHERE statistics.userDwarf.username LIKE :username")
    public Statistics findByUsername(@Param("username") String username) throws DataAccessException;

    @Query("SELECT DISTINCT statistics FROM Statistics statistics WHERE statistics.userDwarf.username LIKE :username")
	public Optional<Statistics> findByUsername2(@Param("username") String username);



    //Estadisticas globales
    @Query(value = "SELECT SUM(GAMES_PLAYED) FROM Statistics", nativeQuery = true)
    public Long getAllGamesPlayed();

    @Query(value = "SELECT SUM(games_won) FROM Statistics", nativeQuery = true)
    public Long getAllGamesWon();

    @Query(value = "SELECT SUM(total_iron) FROM Statistics", nativeQuery = true)
    public Long getAllIron();

    @Query(value = "SELECT SUM(total_gold) FROM Statistics", nativeQuery = true)
    public Long getAllGold();

    @Query(value = "SELECT SUM(total_steel) FROM Statistics", nativeQuery = true)
    public Long getAllSteel();

    @Query(value = "SELECT SUM(total_object) FROM Statistics", nativeQuery = true)
    public Long getAllObject();

    @Query(value = "SELECT SUM(total_medal) FROM Statistics", nativeQuery = true)
    public Long getAllMedal();

    @Query(value = "SELECT SUM(time_played) FROM Statistics", nativeQuery = true)
    public Long getAllTimePlayed();

    @Query(value = "SELECT user_dwarf_id FROM Statistics ORDER BY games_won DESC", nativeQuery = true)
    public List<String> getRank();

    @Query(value = "SELECT games_won FROM Statistics ORDER BY games_won DESC", nativeQuery = true)
    public List<Integer> getRankGW();

    @Query(value = "SELECT SUM(total_gold)/COUNT(*) FROM Statistics", nativeQuery = true)
    public Long getMediaOro();

    @Query(value = "SELECT SUM(total_iron)/COUNT(*) FROM Statistics", nativeQuery = true)
    public Long getMediaHierro();

    @Query(value = "SELECT SUM(total_steel)/COUNT(*) FROM Statistics", nativeQuery = true)
    public Long getMediaAcero();

    @Query(value = "SELECT SUM(total_object)/COUNT(*) FROM Statistics", nativeQuery = true)
    public Long getMediaObjetos();

    @Query(value = "SELECT SUM(total_medal)/COUNT(*) FROM Statistics", nativeQuery = true)
    public Long getMediaMedallas();

    @Query(value = "SELECT SUM(GAMES_PLAYED)/COUNT(*) FROM Statistics", nativeQuery = true)
    public Long getMediaPartidas();

    @Query(value = "SELECT MIN(total_gold) FROM Statistics", nativeQuery = true)
    public Long getMinGold();
    @Query(value = "SELECT MIN(total_iron) FROM Statistics", nativeQuery = true)
    public Long getMinIron();
    @Query(value = "SELECT MIN(total_steel) FROM Statistics", nativeQuery = true)
    public Long getMinSteel();
    @Query(value = "SELECT MIN(total_object) FROM Statistics", nativeQuery = true)
    public Long getMinObject();

    @Query(value = "SELECT MAX(total_gold) FROM Statistics", nativeQuery = true)
    public Long getMaxGold();
    @Query(value = "SELECT MAX(total_iron) FROM Statistics", nativeQuery = true)
    public Long getMaxIron();
    @Query(value = "SELECT MAX(total_steel) FROM Statistics", nativeQuery = true)
    public Long getMaxSteel();
    @Query(value = "SELECT MAX(total_object) FROM Statistics", nativeQuery = true)
    public Long getMaxObject();
    
    @Query(value = "SELECT MAX(time_played) FROM Statistics", nativeQuery = true)
    public Long getMaxTimePlayed();

    @Query(value = "SELECT s MIN(total_gold) FROM Statistics WHERE s.username =:username", nativeQuery = true)
    public Long getMinGold(@Param("username") String username);
    @Query(value = "SELECT MAX(total_gold) FROM Statistics", nativeQuery = true)
    public Long getMaxGold(@Param("username") String username);
    @Query(value = "SELECT SUM(total_gold)/COUNT(*) FROM Statistics", nativeQuery = true)
    public Long getMediaOro(@Param("username") String username);
}