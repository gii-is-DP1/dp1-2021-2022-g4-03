package org.springframework.samples.petclinic.card;

import java.util.function.Function;

import javax.persistence.Entity;

import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Card2 extends BaseEntity{
    
    private String cardImage;
    private CardType cardType;
    private Integer position;
    private String title;
    private String description;
    //private Function<Game,Game> effect;

}
