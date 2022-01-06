package org.springframework.samples.petclinic.achievements;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_achievements")
public class UserAchievements extends BaseEntity{

    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    @Column(name = "progress")
    public Double progress;

    @PastOrPresent(message = "La fecha no puede ser en el futuro")
	@Column(name = "obtaining_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public LocalDate obtainingDate;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumns({
        @JoinColumn(name="userDwarf_id", referencedColumnName = "username")
    })
    private UserDwarf userDwarf;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumns({
        @JoinColumn(name="achievements_id", referencedColumnName = "id")
    })
    private Achievements achievements;
}
