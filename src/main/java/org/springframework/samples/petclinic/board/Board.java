package org.springframework.samples.petclinic.board;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Positive;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Board extends BaseEntity{
    
    // private String[][] cartas;
    // private List<String[]> cartasAccionEspecial;
    // private String[] cartasMontaña;
    
    String background;
    @Positive
    int width;
    @Positive
    int height;

    public Board(){
        this.background="resources/images/boardBackground.jpg";
        this.width=800;
        this.height=800;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "board",fetch = FetchType.EAGER)
    String[][] cartas;
    List<String[]> cartasAccionEspecial;
    String[] cartasMontaña;

}
