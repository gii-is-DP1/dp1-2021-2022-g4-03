package org.springframework.samples.petclinic.achievements;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAchievementsService {

    private final UserAchievementsRepository userAchievementsRepository;

    @Autowired
	public UserAchievementsService(final UserAchievementsRepository userAchievementsRepository) {
		this.userAchievementsRepository = userAchievementsRepository;

	}

	@Transactional
	public void save(final UserAchievements userAchievements) throws DataAccessException {
		this.userAchievementsRepository.save(userAchievements);
	}

    @Transactional
	public void delete(final UserAchievements userAchievements) throws DataAccessException {
		this.userAchievementsRepository.delete(userAchievements);
	}

    @Transactional(readOnly = true)
	public UserAchievements findUserAchievementsById(final int id) throws DataAccessException {
		return this.userAchievementsRepository.findById(id);
	}


	@Transactional(readOnly = true)
	public Optional<UserAchievements> findUserAchievementsById2(int id) throws DataAccessException{
		return userAchievementsRepository.findUserAchievementsById2(id);
	}
    
	@Transactional(readOnly = true)
	public Collection<UserAchievements> findUserAchievementsById3(int id) throws DataAccessException {
		return userAchievementsRepository.findById3(id);
	}

	@Transactional(readOnly = true)
	public Collection<UserAchievements> findByUser(String user) throws DataAccessException {
		return userAchievementsRepository.findByUser(user);
	}


    public List<UserAchievements> findUserAchievementsByUsername(String username){
        return this.userAchievementsRepository.findUserAchievementsByUsername(username);
    }

    public List<UserAchievements> findByAchievementId(Integer id){
        return this.userAchievementsRepository.findUserAchievementsByAchievementId(id);
    }




}
