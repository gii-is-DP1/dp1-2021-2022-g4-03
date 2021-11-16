package org.springframework.samples.petclinic.user;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthoritiesService {

	private AuthoritiesRepository authoritiesRepository;

	private UserDwarfService userDwarfService;

	@Autowired
	public AuthoritiesService(AuthoritiesRepository authoritiesRepository, @Lazy UserDwarfService userDwarfService) {
		this.authoritiesRepository = authoritiesRepository;
		this.userDwarfService = userDwarfService;
	}

	@Transactional
	public void saveAuthorities(Authorities authorities) throws DataAccessException {
		authoritiesRepository.save(authorities);
	}
	
	@Transactional
	public void saveAuthorities(String username, String role) throws DataAccessException {
		Authorities authority = new Authorities();
		Optional<UserDwarf> userDwarf = userDwarfService.findUserDwarfByUsername2(username);
		if(userDwarf.isPresent()) {
			authority.setUserDwarf(userDwarf.get());
			authority.setAuthority(role);
			userDwarf.get().getAuthorities().add(authority);
			authoritiesRepository.save(authority);
		}else
			throw new DataAccessException("UserDwarf '"+username+"' not found!") {};
	}


}
