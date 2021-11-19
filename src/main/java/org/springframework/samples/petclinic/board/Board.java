package org.springframework.samples.petclinic.board;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionType;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="board")
public class Board extends BaseEntity {
    
    // private String[][] cartas;
    // private List<String[]> cartasAccionEspecial;
    // private String[] cartasMontaña;
    
    String background;
    int width;
    int height;

    

    public Board(){
        this.background="resources/images/boardBackground.jpg";
        this.width=800;
        this.height=800;
        this.cartas = new String[3][3];
        this.cartasAccionEspecial = new String[3][];
        this.cartasMontaña = new ArrayList<>();
    }

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "board",fetch = FetchType.EAGER)
    String[][] cartas;
    String[][] cartasAccionEspecial;

    @ElementCollection
    List<String> cartasMontaña;

}
