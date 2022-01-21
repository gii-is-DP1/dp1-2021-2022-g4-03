package org.springframework.samples.petclinic.game;

import lombok.Getter;
import lombok.Setter;

/*
* Clase tipo wrapper que contiene los datos actuales del tablero
*/

@Getter
@Setter
public class ClientData {
    public String currentUser;

    //Format:
    // [00-08] where he puts a worker on the mine
    // [1,2,3][09-11] resource to use iron:1 gold:2 steel:3, 9 to 11 what special deck
    // [4][00-08] turn effect, select worker position
    // [5][01-03] sell effect, resource to get iron:1 gold:2 steel:3
    // [6][00-08] apprentice effect, select worker position
    public Integer playerAction;
}
