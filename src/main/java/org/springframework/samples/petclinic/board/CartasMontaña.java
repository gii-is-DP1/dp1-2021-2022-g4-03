package org.springframework.samples.petclinic.board;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartasMontaña extends BaseEntity{
    String type;
    String color;
    @Range(min=0,max=7)
    int xPosition;
    @Range(min=0,max=7)
    int yPosition;

    @ManyToOne
    Board board;

    public Integer getPositionXinPixels(Integer size){
        return (xPosition)*size;
    }

    public Integer getPositionYinPixels(Integer size){
        return (yPosition)*size;
    }
}
