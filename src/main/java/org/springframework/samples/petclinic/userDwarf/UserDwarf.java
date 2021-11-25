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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.Authorities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "usersdwarf")
public class UserDwarf extends BaseEntity implements Serializable{

    @Column(name="username")
    @NotEmpty
    @Size(min = 4, max = 20)
    private String username;

    @Column(name="pass")
    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z[0-9]]{8,}$" , message = "must contain 8 characters, one uppercase and numbers")
    private String pass;

    @Column(name="email")
    @NotEmpty
    @Email
    private String email;

    @Column(name="active")
    @NotNull
    boolean active;

    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userDwarf")
	private Set<Authorities> authorities;
}