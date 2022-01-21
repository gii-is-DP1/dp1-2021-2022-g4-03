package org.springframework.samples.petclinic.statistics;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StatisticsRepository extends CrudRepository<Statistics, Integer>{

    Collection<Statistics> findAll() throws DataAccessException;

    @Query("SELECT statistics FROM Statistics statistics WHERE statistics.id = :id")
    Statistics findByID(@Param("id") int id) throws DataAccessException;

    @Query("SELECT statistics FROM Statistics statistics WHERE statistics.userDwarf.username LIKE :username")
    Statistics findByUsername(@Param("username") String username) throws DataAccessException;

    @Query("SELECT DISTINCT statistics FROM Statistics statistics WHERE statistics.userDwarf.username LIKE :username")
    Optional<Statistics> findByUsername2(@Param("username") String username);



    //Estadisticas globales
    @Query(value = "SELECT SUM(GAMES_PLAYED) FROM Statistics", nativeQuery = true)
    Long getAllGamesPlayed();

    @Query(value = "SELECT SUM(games_won) FROM Statistics", nativeQuery = true)
    Long getAllGamesWon();

    @Query(value = "SELECT SUM(total_iron) FROM Statistics", nativeQuery = true)
    Long getAllIron();

    @Query(value = "SELECT SUM(total_gold) FROM Statistics", nativeQuery = true)
    Long getAllGold();

    @Query(value = "SELECT SUM(total_steel) FROM Statistics", nativeQuery = true)
    Long getAllSteel();

    @Query(value = "SELECT SUM(total_object) FROM Statistics", nativeQuery = true)
    Long getAllObject();

    @Query(value = "SELECT SUM(total_medal) FROM Statistics", nativeQuery = true)
    Long getAllMedal();

    @Query(value = "SELECT SUM(time_played) FROM Statistics", nativeQuery = true)
    Long getAllTimePlayed();

    @Query(value = "SELECT user_dwarf_id FROM Statistics ORDER BY games_won DESC", nativeQuery = true)
    List<String> getRank();

    @Query(value = "SELECT games_won FROM Statistics ORDER BY games_won DESC", nativeQuery = true)
    List<Integer> getRankGW();

    @Query(value = "SELECT SUM(total_gold)/COUNT(*) FROM Statistics", nativeQuery = true)
    Long getMediaOro();

    @Query(value = "SELECT SUM(total_iron)/COUNT(*) FROM Statistics", nativeQuery = true)
    Long getMediaHierro();

    @Query(value = "SELECT SUM(total_steel)/COUNT(*) FROM Statistics", nativeQuery = true)
    Long getMediaAcero();

    @Query(value = "SELECT SUM(total_object)/COUNT(*) FROM Statistics", nativeQuery = true)
    Long getMediaObjetos();

    @Query(value = "SELECT SUM(total_medal)/COUNT(*) FROM Statistics", nativeQuery = true)
    Long getMediaMedallas();

    @Query(value = "SELECT SUM(GAMES_PLAYED)/COUNT(*) FROM Statistics", nativeQuery = true)
    Long getMediaPartidas();

    @Query(value = "SELECT MIN(total_gold) FROM Statistics", nativeQuery = true)
    Long getMinGold();
    @Query(value = "SELECT MIN(total_iron) FROM Statistics", nativeQuery = true)
    Long getMinIron();
    @Query(value = "SELECT MIN(total_steel) FROM Statistics", nativeQuery = true)
    Long getMinSteel();
    @Query(value = "SELECT MIN(total_object) FROM Statistics", nativeQuery = true)
    Long getMinObject();

    @Query(value = "SELECT MAX(total_gold) FROM Statistics", nativeQuery = true)
    Long getMaxGold();
    @Query(value = "SELECT MAX(total_iron) FROM Statistics", nativeQuery = true)
    Long getMaxIron();
    @Query(value = "SELECT MAX(total_steel) FROM Statistics", nativeQuery = true)
    Long getMaxSteel();
    @Query(value = "SELECT MAX(total_object) FROM Statistics", nativeQuery = true)
    Long getMaxObject();
    
    @Query(value = "SELECT MAX(time_played) FROM Statistics", nativeQuery = true)
    Long getMaxTimePlayed();

    
}