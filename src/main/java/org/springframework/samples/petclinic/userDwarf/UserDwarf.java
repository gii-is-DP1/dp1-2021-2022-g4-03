package org.springframework.samples.petclinic.userDwarf;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    public String username;

    @Column(name="pass")
    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z[0-9]]{8,}$" , message = "Debe contener 8 caractéres, uno mínimo en mayúsculas y otro en número")
    private String pass;

    @Column(name="email")
    @NotEmpty
    @Email(message = "Tiene que seguir el formato email")
    private String email;

    @Column(name="active")
    @NotNull
    boolean active;

    @JsonIgnore
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
