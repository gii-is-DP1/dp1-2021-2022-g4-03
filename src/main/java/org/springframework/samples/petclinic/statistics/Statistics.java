package org.springframework.samples.petclinic.statistics;


import java.time.Duration;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "statistics")
public class Statistics extends BaseEntity{
    
    @Column(name="time_played")
    @NotEmpty(message = "timePlayed may not be null")
    private Duration timePlayed;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="userDwarf_id", referencedColumnName = "username", unique = true)
    })
    private UserDwarf userDwarf;


    public String getTimeFormatted(){
        long seconds = timePlayed.getSeconds();
        String result = String.format("%dh:%02dm:%02ds", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
        return result;
    }
}

