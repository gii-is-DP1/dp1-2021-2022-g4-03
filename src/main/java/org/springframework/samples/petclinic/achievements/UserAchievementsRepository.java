package org.springframework.samples.petclinic.achievements;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAchievementsRepository extends CrudRepository<UserAchievements, String> {

    void delete(UserAchievements p) throws DataAccessException;

    UserAchievements findById(int id) throws DataAccessException;

    @Query("SELECT userAchievements FROM UserAchievements userAchievements WHERE userAchievements.userDwarf" +
        ".username LIKE :username")
    public List<UserAchievements> findUserAchievementsByUsername(@Param("username")String username);

    @Query("SELECT userAchievements FROM UserAchievements userAchievements WHERE userAchievements.achievements" +
        ".id =:id")
    public List<UserAchievements> findUserAchievementsByAchievementId(@Param("id")Integer id);

}
