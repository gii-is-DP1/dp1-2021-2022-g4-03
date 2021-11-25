package org.springframework.samples.petclinic.achievements;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface UserAchievementsRepository extends CrudRepository<UserAchievements, String> {
    
    void delete(UserAchievements p) throws DataAccessException;

    UserAchievements findById(int id) throws DataAccessException;

}
