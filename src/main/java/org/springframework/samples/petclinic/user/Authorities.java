package org.springframework.samples.petclinic.user;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authorities extends BaseEntity {

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "username", referencedColumnName = "username") 
	})
	UserDwarf userDwarf;

	@Size(min = 3, max = 50)
	String authority;

}
