package org.springframework.samples.petclinic.userDwarf;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.Authorities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usersdwarf")
public class UserDwarf extends BaseEntity implements Serializable{

    @Column(name="username")
    @NotEmpty
    private String username;

    @Column(name="pass")
    @NotEmpty
    private String pass;

    @Column(name="email")
    @NotEmpty
    @Email
    private String email;

    @Column(name="active")
    @NotNull
    boolean active;

    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userDwarf")
	private Set<Authorities> authorities;
}