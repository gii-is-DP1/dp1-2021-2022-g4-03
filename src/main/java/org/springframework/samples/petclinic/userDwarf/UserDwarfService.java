package org.springframework.samples.petclinic.userDwarf;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDwarfService {

    @Autowired
	private AuthoritiesService authoritiesService;

    @Autowired
    private UserDwarfRepository userDwarfRepository;
    
    @Transactional
    public int userDwarfCount(){
        return (int) userDwarfRepository.count();
    }
    @Transactional
    public Iterable<UserDwarf> findAll(){
        return userDwarfRepository.findAll();

    }

    @Transactional(readOnly = true)
	public UserDwarf findById(int id) throws DataAccessException {
		return userDwarfRepository.findById(id);
	}

    @Transactional
	public void saveUser(UserDwarf user) throws DataAccessException {
		//creating user
		userDwarfRepository.save(user);		
		//creating authorities
	}

    @Transactional(readOnly = true)
	public Collection<UserDwarf> findUserByUsername(String username) throws DataAccessException {
		return userDwarfRepository.findByUsername(username);
	}
}
