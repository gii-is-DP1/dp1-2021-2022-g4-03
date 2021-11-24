package org.springframework.samples.petclinic.achievements;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AchievementsRepository extends CrudRepository<Achievements, String>{
    
    void delete(Achievements p) throws DataAccessException;

    Achievements findById(int id) throws DataAccessException;

    @Query("SELECT o FROM Achievements o WHERE o.achievements.condition = :condition")
	public Collection<Achievements> findAchievementByCondition(@Param("condition")String condition);
}
