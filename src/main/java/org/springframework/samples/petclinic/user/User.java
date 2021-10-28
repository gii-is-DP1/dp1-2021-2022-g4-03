package org.springframework.samples.petclinic.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "users")
@Entity
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
	   
}
