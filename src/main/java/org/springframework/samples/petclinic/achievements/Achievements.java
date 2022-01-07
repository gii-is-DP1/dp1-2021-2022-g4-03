package org.springframework.samples.petclinic.achievements;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

import org.springframework.samples.petclinic.model.BaseEntity;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity
@Table(name="achievements")
public class Achievements extends BaseEntity{

    @FutureOrPresent(message = "La fecha no puede ser en el futuro")
	@Column(name = "last_change")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	public LocalDate lastChange = LocalDate.now();

	@Column(name = "condition")
	@NotEmpty
	@Size(min=3, max=50)
	public String condition;

	@Column(name = "description")
	@NotEmpty
	@Size(min=3, max=50)
	public String description;

	@NotEmpty
	@Column(name = "pic")
	public String pic;

    @OneToMany(cascade= CascadeType.REMOVE)
	private List<UserAchievements> userAchievements;

}
