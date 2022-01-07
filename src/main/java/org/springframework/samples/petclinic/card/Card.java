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

}
