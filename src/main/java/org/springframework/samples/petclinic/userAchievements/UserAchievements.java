package org.springframework.samples.petclinic.userAchievements;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.achievements.Achievements;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserAchievements extends BaseEntity{

    
	@JoinColumn(name = "owner_id")
	private User user;

    
    @JoinColumn(name = "achievements_id")
    private Achievements achievements;


    @PastOrPresent(message = "La fecha no puede ser en el futuro")
	@Column(name = "obtaining_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate obtainingDate;
    
}
