package org.springframework.samples.petclinic.board;

import java.util.List;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Board extends BaseEntity{
    
    private String[][] cartas;
    private List<String[]> cartasAccionEspecial;
    private String[] cartasMontaña;

}
