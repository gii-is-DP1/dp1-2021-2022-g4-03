package org.springframework.samples.petclinic.achievements;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.PastOrPresent;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
@Entity
@Table(name="achievements")
public class Achievements extends NamedEntity{

    @PastOrPresent(message = "La fecha no puede ser en el futuro")
	@Column(name = "last_change")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate lastChange;

	@Column(name = "condition")
	private String condition;

	@Column(name = "description")
	private String description;

}
