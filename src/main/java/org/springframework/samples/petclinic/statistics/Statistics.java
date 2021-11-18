package org.springframework.samples.petclinic.statistics;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "statistics")
public class Statistics extends BaseEntity{
    
    @Column(name="time_played")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @NotEmpty(message = "timePlayed may not be null")
    private LocalTime timePlayed;

    @Column(name="games_played")
    @NotEmpty(message = "gamesPlayed may not be null")
    @Min(value=0)
    private Integer gamesPlayed;

    @Column(name="games_won")
    @NotEmpty(message = "gamesWon may not be null")
    @Min(value=0)
    private Integer gamesWon;

    @Column(name="total_iron")
    @NotEmpty(message = "totalIron may not be null")
    @Min(value=0)
    private Integer totalIron;

    @Column(name="total_gold")
    @NotEmpty(message = "totalGold may not be null")
    @Min(value=0)
    private Integer totalGold;

    @Column(name="total_steel")
    @NotEmpty(message = "totalSteel may not be null")
    @Min(value=0)
    private Integer totalSteel;

    @Column(name="total_object")
    @NotEmpty(message = "totalObject may not be null")
    @Min(value=0)
    private Integer totalObject;

    @Column(name="total_medal")
    @NotEmpty(message = "totalMedal may not be null")
    @Min(value=0)
    private Integer totalMedal;

    //No olvidar poner (mappedBy = "userDwarf", cascade = CascadeType.ALL) en UserDwarf
    @OneToOne(optional = false)
    @JoinColumn(name="userDwarf_id")
    private UserDwarf userDwarf;

}

