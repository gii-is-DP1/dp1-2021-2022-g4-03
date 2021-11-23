package org.springframework.samples.petclinic.achievements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

public class UserAchievementsService {

    private final UserAchievementsRepository userAchievementsRepository;

    @Autowired
	public UserAchievementsService(final UserAchievementsRepository userAchievementsRepository) {
		this.userAchievementsRepository = userAchievementsRepository;

	}

    @Transactional
	public void delete(final UserAchievements userAchievements) throws DataAccessException {
		this.userAchievementsRepository.delete(userAchievements);
	}

    @Transactional(readOnly = true)
	public UserAchievements findAchievementsById(final int id) throws DataAccessException {
		return this.userAchievementsRepository.findById(id);
	}
    
}
