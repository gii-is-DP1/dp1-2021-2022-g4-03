package org.springframework.samples.petclinic.statistics;


import java.time.Duration;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
    @NotNull
    public Duration timePlayed;

    @Column(name="games_played")
    @NotNull
    @PositiveOrZero
    public Integer gamesPlayed;

    @Column(name="games_won")
    @NotNull
    @PositiveOrZero
    public Integer gamesWon;

    @Column(name="total_iron")
    @NotNull
    @PositiveOrZero
    public Integer totalIron;

    @Column(name="total_gold")
    @NotNull
    @PositiveOrZero
    public Integer totalGold;

    @Column(name="total_steel")
    @NotNull
    @PositiveOrZero
    public Integer totalSteel;

    @Column(name="total_object")
    @NotNull
    @PositiveOrZero
    public Integer totalObject;

    @Column(name="total_medal")
    @NotNull
    @PositiveOrZero
    public Integer totalMedal;

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

