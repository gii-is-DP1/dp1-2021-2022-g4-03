	package org.springframework.samples.petclinic.user;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.Lazy;
	import org.springframework.dao.DataAccessException;
	import org.springframework.samples.petclinic.userDwarf.UserDwarf;
	import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;
	
	import java.util.Collection;
	import java.util.List;
	import java.util.Optional;
	import java.util.stream.Collectors;

@Service
public class AuthoritiesService {

	private final AuthoritiesRepository authoritiesRepository;

	private final UserDwarfService userDwarfService;

	@Autowired
	public AuthoritiesService(AuthoritiesRepository authoritiesRepository, @Lazy UserDwarfService userDwarfService) {
		this.authoritiesRepository = authoritiesRepository;
		this.userDwarfService = userDwarfService;
	}

	@Transactional
	public void saveAuthorities(Authorities authorities) throws DataAccessException {
		authoritiesRepository.save(authorities);
	}

	public Collection<Authorities> findAuthoritiesByUsername(String username) throws DataAccessException {
		return authoritiesRepository.findByUsername(username);
	}

	public List<String> getRolesUserByUsername(String username) throws DataAccessException {
		return findAuthoritiesByUsername(username).stream().map(auth -> auth.getAuthority())
				.collect(Collectors.toList());
	}

    @Transactional
    public void deleteAuthorities(String username, String role){
        Authorities authority = new Authorities();
        Optional<UserDwarf> userDwarf = userDwarfService.findUserDwarfByUsername2(username);
        if (userDwarf.isPresent()) {
            Boolean authCheck = false;
            authority.setUserDwarf(userDwarf.get());
            authority.setAuthority(role);
            Collection<Authorities> authIt = authoritiesRepository.findByUsername(username);
            for (Authorities auth : authIt) {
                if (auth.authority.equals(role))
                    authCheck = true;
                break;
            }
            if (authCheck)
                authoritiesRepository.delete(authority);
        } else
            throw new DataAccessException("UserDwarf '" + username + "' not found!") {
            };
    }

	@Transactional
	public void saveAuthorities(String username, String role) throws DataAccessException {
		Authorities authority = new Authorities();
		Optional<UserDwarf> userDwarf = userDwarfService.findUserDwarfByUsername2(username);
		if (userDwarf.isPresent()) {
			Boolean authCheck = true;
			authority.setUserDwarf(userDwarf.get());
			authority.setAuthority(role);
			Collection<Authorities> authIt = authoritiesRepository.findByUsername(username);
			for (Authorities auth : authIt) {
				if (auth.authority.equals(role))
					authCheck = false;
				break;
			}
			if (authCheck)
				authoritiesRepository.save(authority);
		} else
			throw new DataAccessException("UserDwarf '" + username + "' not found!") {
			};
	}

}
