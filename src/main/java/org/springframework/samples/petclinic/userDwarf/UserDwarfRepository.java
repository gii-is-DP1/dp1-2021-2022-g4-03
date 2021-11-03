package org.springframework.samples.petclinic.userDwarf;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface UserDwarfRepository extends CrudRepository<UserDwarf, Integer>{    

    Collection<UserDwarf> findAll() throws DataAccessException;
}