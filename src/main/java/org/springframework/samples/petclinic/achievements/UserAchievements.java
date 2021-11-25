package org.springframework.samples.petclinic.achievements;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_achievements")
public class UserAchievements extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userDwarf_id", referencedColumnName = "id")
	private UserDwarf userDwarf;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "achievements_id", referencedColumnName = "id")
    private Set<Achievements> achievements;

    @PastOrPresent(message = "La fecha no puede ser en el futuro")
	@Column(name = "obtaining_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate obtainingDate;
    
}
