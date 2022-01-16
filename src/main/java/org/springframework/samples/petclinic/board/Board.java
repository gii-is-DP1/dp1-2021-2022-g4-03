package org.springframework.samples.petclinic.board;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "board")
public class Board extends BaseEntity {

    private String background;
    //TODO: Cambiar width y height a variables adaptables a pantalla.
    private int width;
    private int height;

    public Board() {
        this.background = "/resources/images/boardBackground.png";
        this.width = 1000;
        this.height = 700;
        this.cartas = new ArrayList<>();
        this.cartasMontaña = new ArrayList<>();
        this.cartasAccionEspecial_0= new ArrayList<>();
        this.cartasAccionEspecial_1= new ArrayList<>();
        this.cartasAccionEspecial_2= new ArrayList<>();
    }

    public Board(int id) {
        this.id = id;
        this.background = "resources/images/boardBackground.png";
        //TODO: Change this values to responsive, maybe won't do because of time constraints.
        this.width = 1000;
        this.height = 700;
        this.cartas = new ArrayList<>();
        this.cartasMontaña = new ArrayList<>();
        this.cartasAccionEspecial_0= new ArrayList<>();
        this.cartasAccionEspecial_1= new ArrayList<>();
        this.cartasAccionEspecial_2= new ArrayList<>();
    }

    //Mina, array cartas 0-8
    @ElementCollection
    List<Integer> cartas;

    //Montones de cartas especiales, array cartas 9-11
    @ElementCollection
    List<Integer> cartasAccionEspecial_0;

    @ElementCollection
    List<Integer> cartasAccionEspecial_1;

    @ElementCollection
    List<Integer> cartasAccionEspecial_2;

    //Mazo, cartas sin asignar
    @ElementCollection
    List<Integer> cartasMontaña;

}
