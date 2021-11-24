package org.springframework.samples.petclinic.achievements;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

public class AchievementsService {

    private final AchievementsRepository achievementsRepository;

    @Autowired
	public AchievementsService(final AchievementsRepository AchievementsRepository) {
		this.achievementsRepository = AchievementsRepository;

	}

    @Transactional
	public void delete(final Achievements Achievements) throws DataAccessException {
		this.achievementsRepository.delete(Achievements);
	}

    @Transactional(readOnly = true)
	public Achievements findAchievementById(final int id) throws DataAccessException {
		return this.achievementsRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Achievements> findAchievementsByCondition(final String condition) throws DataAccessException {
		return this.achievementsRepository.findAchievementByCondition(condition);
	}

	@Transactional
	public void saveAchievement(final Achievements achievement) throws DataAccessException {
		//creating owner
		this.achievementsRepository.save(achievement);
	}
    
}
