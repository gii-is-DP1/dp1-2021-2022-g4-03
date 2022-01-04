package org.springframework.samples.petclinic.board;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "board")
public class Board extends BaseEntity {

    String background;
    int width;
    int height;

    public Board() {
        this.background = "resources/images/boardBackground.png";
        this.width = 1000;
        this.height = 700;
        this.cartas = new ArrayList<>();
        this.cartasAccionEspecial = new ArrayList<>();
    }
    public Board(int id) {
        this.id=id;
        this.background = "resources/images/boardBackground.png";
        this.width = 1000;
        this.height = 700;
        this.cartas = new ArrayList<>();
        this.cartasAccionEspecial = new ArrayList<>();
    }

    //Esta es la montaña de cartas normales, enemigos-forja-mina
    @ElementCollection
    List<String> cartas;

    //Montaña de cartas de acción especial
    @ElementCollection
    List<String> cartasAccionEspecial;

}
