package org.springframework.samples.petclinic.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity{

    @Column(name = "nickname")
    @NotEmpty
    protected String nickname;

    @Column(name= "email")
    @NotEmpty
    @Email
    protected String email;

    @Column(name = "pass")
    @NotEmpty
    protected String pass;

    @Column(name= "active")
    @NotEmpty
    boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;





    
}
