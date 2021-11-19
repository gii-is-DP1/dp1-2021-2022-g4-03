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
@Table(name="board")
public class Board extends BaseEntity {
    
    String background;
    int width;
    int height;

    

    public Board(){
        this.background="resources/images/boardBackground.jpg";
        this.width=800;
        this.height=800;
        this.cartas = new ArrayList<>();
        this.cartasAccionEspecial = new ArrayList<>();
        this.cartasMontaña = new ArrayList<>();
    }

    @ElementCollection
    List<String> cartas;
    @ElementCollection
    List<String> cartasAccionEspecial;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "board",fetch = FetchType.EAGER)
    @ElementCollection
    List<CartasMontaña> cartasMontaña;
    

    

}
