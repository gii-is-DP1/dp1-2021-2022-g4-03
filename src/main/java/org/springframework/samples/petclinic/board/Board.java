package org.springframework.samples.petclinic.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.model.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "board")
public class Board extends BaseEntity {
    
    private String background = "resources/images/boardBackground.png";
    //TODO: Cambiar width y height a variables adaptables a pantalla.
    private int width = 1000;
    private int height = 700;
    
    public Board() {
    }
    
    public Board(int id) {
        this.id = id;
    }
    
    //Mina, array cartas 0-8
    @OneToMany(cascade = {javax.persistence.CascadeType.ALL})
    List<Cell> cardCells = new ArrayList<>();
    
    //Montones de cartas especiales, array cartas 9-11
    @ElementCollection
    List<Integer> cartasAccionEspecial_0 = new ArrayList<>();
    
    @ElementCollection
    List<Integer> cartasAccionEspecial_1 = new ArrayList<>();
    
    @ElementCollection
    List<Integer> cartasAccionEspecial_2 = new ArrayList<>();
    
    //Mazo, cartas sin asignar
    @ElementCollection
    List<Integer> deck = new ArrayList<>();
    
    @Transient
    @JsonIgnore
    public List<Integer> getCellContent(int index) {
        return cardCells.get(index).getCards();
    }
    
    @Transient
    @JsonIgnore
    public List<Integer> getCellsTopCard() {
        return cardCells.stream().map(Cell::getCardOnTop).collect(Collectors.toList());
    }
    
    @Transient
    @JsonIgnore
    public Cell getCell(Integer index) {
        return cardCells.get(index);
    }
    
    
}
