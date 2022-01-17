package org.springframework.samples.petclinic.card;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cards")
public class Card extends BaseEntity{

    @Column(name="cardimage")
    @NotNull
    private String cardImage;

    @Column(name="cardtype")
    @NotNull
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name="position")
    private Integer position;

    @Column(name="title")
    @NotNull
    private String title;

    @Column(name="description")
    @NotNull
    private String description;

    @Column(name="effect")
    @NotNull
    private String effect;

    public Integer getPositionXInPixels(Integer size) {
        position = 0;
        if(position.equals(0)||position.equals(3)||position.equals(6)) {
            return(position*size);
        } else if(position.equals(1)||position.equals(4)||position.equals(7)) {
            position = 1;
            return(position*size);
        } else {
            position = 2;
            return(position*size);
        }
    }

    public Integer getPositionYInPixels(Integer size) {
        position = 0;
        if(position.equals(0)||position.equals(1)||position.equals(2)) {
            return(position*size);
        } else if(position.equals(3)||position.equals(4)||position.equals(5)) {
            position = 1;
            return(position*size);
        } else {
            position = 2;
            return(position*size);
        }
    }

}
