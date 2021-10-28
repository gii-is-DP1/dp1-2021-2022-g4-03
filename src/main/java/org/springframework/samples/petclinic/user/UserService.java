
package org.springframework.samples.petclinic.user;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException {
		user.setEnabled(true);
		userRepository.save(user);
	}
	
	public Optional<User> findUser(String username) {
		return userRepository.findById(username);
	}

	@Transactional
	public void deleteUser(final User user) throws DataAccessException {
		this.userRepository.delete(user);
	}

	public User getUserSession() {
		User usuario = new User();
		final Optional<User> user = this.findUser(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user.isPresent()) {
			usuario = user.get();
		}
		return usuario;
	}
//comentario

}
