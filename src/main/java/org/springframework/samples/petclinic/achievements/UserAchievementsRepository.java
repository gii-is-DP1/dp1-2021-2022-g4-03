package org.springframework.samples.petclinic.achievements;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface UserAchievementsRepository extends CrudRepository<UserAchievements, String> {

    void delete(UserAchievements p) throws DataAccessException;

    UserAchievements findById(int id) throws DataAccessException;


    @Query("SELECT DISTINCT userAchievements FROM UserAchievements userAchievements WHERE userAchievements.id LIKE :id")
	public Optional<UserAchievements> findUserAchievementsById2(@Param("id") int id);

    @Query("SELECT DISTINCT userAchievements FROM UserAchievements userAchievements WHERE userAchievements.id LIKE :id")
    public Collection<UserAchievements> findById3(@Param("id") int id);
    
    @Query("SELECT DISTINCT userAchievements FROM UserAchievements userAchievements WHERE userAchievements.userDwarf.username LIKE :username")
    public Collection<UserAchievements> findByUser(@Param("username") String username);

    @Query("SELECT userAchievements FROM UserAchievements userAchievements WHERE userAchievements.userDwarf" +
        ".username LIKE :username")
    public List<UserAchievements> findUserAchievementsByUsername(@Param("username")String username);

    @Query("SELECT userAchievements FROM UserAchievements userAchievements WHERE userAchievements.achievements" +
        ".id =:id")
    public List<UserAchievements> findUserAchievementsByAchievementId(@Param("id")Integer id);

    @Query("SELECT ua FROM UserAchievements ua WHERE ua.achievements.id =:id AND ua.userDwarf.username LIKE :username")
    public UserAchievements findUserAchievementsByAchievementsIdAndUsername(@Param("id") Integer id ,@Param("username") String username);

}
