package org.springframework.samples.petclinic.achievements;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
@Entity
@Table(name="achievements")
public class Achievements extends BaseEntity{

    @FutureOrPresent(message = "La fecha no puede ser en el pasado")
	@Column(name = "last_change")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public LocalDate lastChange;

	@Column(name = "condition")
	public String condition;

	@Column(name = "description")
	public String description;

}
