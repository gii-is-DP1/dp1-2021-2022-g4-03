package org.springframework.samples.petclinic.configuration.Users;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.Authorities;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class User extends BaseEntity{

    @Column(name = "nickname")
    @NotEmpty
    protected String nickname;

    @Column(name = "pass")
    @NotEmpty
    protected String pass;

    boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;





    
}
