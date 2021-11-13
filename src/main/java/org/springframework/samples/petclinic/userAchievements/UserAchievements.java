package org.springframework.samples.petclinic.userAchievements;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.achievements.Achievements;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

public class UserAchievements extends BaseEntity{

    @Getter
    @Setter
	@JoinColumn(name = "owner_id")
	private User user;

    @Getter
    @Setter
    @JoinColumn(name = "achievements_id")
    private Achievements achievements;


    @Getter
    @Setter
    @PastOrPresent(message = "La fecha no puede ser en el futuro")
	@Column(name = "last_change")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate lastChange;
    
}
