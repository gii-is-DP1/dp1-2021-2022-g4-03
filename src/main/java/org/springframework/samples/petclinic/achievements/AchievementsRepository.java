package org.springframework.samples.petclinic.achievements;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface AchievementsRepository extends CrudRepository<Achievements, String>{
    
    void delete(Achievements p) throws DataAccessException;

    Achievements findById(int id) throws DataAccessException;
}
