package org.springframework.samples.petclinic.achievements;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AchievementsRepository extends CrudRepository<Achievements, String>{
    
    void delete(Achievements p) throws DataAccessException;

    Achievements findById(int id) throws DataAccessException;

    @Query("SELECT DISTINCT ach FROM Achievements ach WHERE ach.condition LIKE :condition")
	public Collection<Achievements> findAchievementByCondition(@Param("condition")String condition);

    Collection<Achievements> findAll() throws DataAccessException;

    @Query("SELECT achievements from Achievements achievements WHERE achievements.id =:id")
    public Optional<Achievements> findByIdOptional(@Param("id") int id);
}
