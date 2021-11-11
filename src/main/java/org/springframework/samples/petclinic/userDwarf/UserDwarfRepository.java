package org.springframework.samples.petclinic.userDwarf;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDwarfRepository extends CrudRepository<UserDwarf, Integer>{    

    Collection<UserDwarf> findAll() throws DataAccessException;

    @Query("SELECT DISTINCT userDwarf FROM UserDwarf userDwarf WHERE userDwarf.username LIKE :username")
	public Collection<UserDwarf> findByUsername(@Param("username") String username);

    @Query("SELECT userDwarf FROM UserDwarf userDwarf WHERE userDwarf.id =:id")
	public UserDwarf findById(@Param("id") int id);
}