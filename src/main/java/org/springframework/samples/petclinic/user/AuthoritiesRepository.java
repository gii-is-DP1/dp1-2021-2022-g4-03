package org.springframework.samples.petclinic.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	@Query("SELECT DISTINCT authority FROM Authorities authority WHERE authority.userDwarf.username LIKE :username")
    Collection<Authorities> findByUsername(@Param("username") String username);
}
