package org.springframework.samples.petclinic.userDwarf;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface UserDwarfRepository extends CrudRepository<UserDwarf, Integer>{    

    Collection<UserDwarf> findAll() throws DataAccessException;

    @Query("SELECT DISTINCT userDwarf FROM UserDwarf userDwarf WHERE userDwarf.username LIKE :username")
    Collection<UserDwarf> findByUsername(@Param("username") String username);

    @Query("SELECT DISTINCT userDwarf FROM UserDwarf userDwarf WHERE userDwarf.username LIKE :username")
    Optional<UserDwarf> findByUsername2(@Param("username") String username);

    @Query("SELECT userDwarf FROM UserDwarf userDwarf WHERE userDwarf.id =:id")
    UserDwarf findById(@Param("id") int id);

    @Query("SELECT userDwarf FROM UserDwarf userDwarf WHERE userDwarf.id =:id")
    Optional<UserDwarf> findByIdOptional(@Param("id") int id);
}