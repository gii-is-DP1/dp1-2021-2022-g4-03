package org.springframework.samples.petclinic.card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cards")
public class Card extends BaseEntity{
    
    @Column(name="card_image")
    private String cardImage;
    @Column(name="card_type")
    private CardType cardType;
    @Column(name="position")
    private Integer position;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="effect")
    private Integer effectId;

}
