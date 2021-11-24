package org.springframework.samples.petclinic.userDwarf;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.security.core.context.SecurityContextHolder;
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

    //Antonio was here

    @Transactional(readOnly = true)
	public Optional<UserDwarf> findUserByUsernameOpt(String username) throws DataAccessException {
		return userDwarfRepository.findByUsernameOpt(username);
	}

    public UserDwarf getUserSession() {
		UserDwarf usuario = new UserDwarf();
		final Optional<UserDwarf> user = this.findUserByUsernameOpt(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.isPresent()) {
			usuario = user.get();
		}
		return usuario;
	}

    @Transactional
	public boolean userHaveRol(final String username, final String rol) {
		boolean res = false;
		final Optional<UserDwarf> user = this.findUserByUsernameOpt(username);
		if (user.isPresent()) {
			final Set<Authorities> roles = user.get().getAuthorities();
			for (final Authorities a : roles) {
				if (a.getAuthority().equals(rol)) {
					res = true;
					break;
				}
			}
		}
		return res;
	}
}
