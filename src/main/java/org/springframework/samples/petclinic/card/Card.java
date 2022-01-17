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
    public String cardImage;

    @Column(name="cardtype")
    @NotNull
    @Enumerated(EnumType.STRING)
    public CardType cardType;

    @Column(name="position")
    public Integer position;

    @Column(name="title")
    @NotNull
    public String title;

    @Column(name="description")
    @NotNull
    public String description;

    @Column(name="effect")
    @NotNull
    public String effect;

    @Column(name = "initial")
    @NotNull
    boolean initial;

}
