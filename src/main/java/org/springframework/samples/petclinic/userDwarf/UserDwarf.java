package org.springframework.samples.petclinic.userDwarf;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.hibernate.Hibernate;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.Authorities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserDwarf userDwarf = (UserDwarf) o;
        return id != null && Objects.equals(id, userDwarf.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
