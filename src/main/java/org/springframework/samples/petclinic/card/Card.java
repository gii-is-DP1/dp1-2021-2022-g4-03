package org.springframework.samples.petclinic.card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cards")
public class Card extends BaseEntity{
    
    @Column(name="cardImage")
    @NotNull
    private String cardImage;

    @Column(name="cardType")
    @NotNull
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

}
