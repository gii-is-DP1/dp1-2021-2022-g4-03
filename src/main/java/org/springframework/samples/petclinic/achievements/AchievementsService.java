package org.springframework.samples.petclinic.achievements;



import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AchievementsService {

    private final AchievementsRepository achievementsRepository;

    @Autowired
	public AchievementsService(final AchievementsRepository AchievementsRepository) {
		this.achievementsRepository = AchievementsRepository;

	}

	@Transactional
    public int achievementsCount(){
        return (int) achievementsRepository.count();
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

	@Transactional(readOnly = true)
	public Achievements findAchievementsByCondition2(final String condition) throws DataAccessException {
		return this.achievementsRepository.findAchievementByCondition2(condition);
	}

	@Transactional
	public void saveAchievement(final Achievements achievement) throws DataAccessException {
		//creating owner
		this.achievementsRepository.save(achievement);
	}

	@Transactional
	public Iterable<Achievements> findAll(){
		return achievementsRepository.findAll();
	}

	@Transactional
	public Optional<Achievements> findByIdOptional(int id) throws DataAccessException{
		return achievementsRepository.findByIdOptional(id);
	}

    
}
