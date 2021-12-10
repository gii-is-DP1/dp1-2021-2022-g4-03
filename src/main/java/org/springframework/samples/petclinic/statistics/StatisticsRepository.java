package org.springframework.samples.petclinic.statistics;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface StatisticsRepository extends CrudRepository<Statistics, Integer>{

    Collection<Statistics> findAll() throws DataAccessException;

    @Query("SELECT statistics FROM Statistics statistics WHERE statistics.id = :id")
    public Statistics findByID(@Param("id") int id) throws DataAccessException;

    @Query("SELECT statistics FROM Statistics statistics WHERE statistics.userDwarf.username LIKE :username")
    public Statistics findByUsername(@Param("username") String username) throws DataAccessException;

    @Query("SELECT DISTINCT statistics FROM Statistics statistics WHERE statistics.userDwarf.username LIKE :username")
	public Optional<Statistics> findByUsername2(@Param("username") String username);
    
}