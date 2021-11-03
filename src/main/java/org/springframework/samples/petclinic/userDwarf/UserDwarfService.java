package org.springframework.samples.petclinic.userDwarf;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDwarfService {

    
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

}
