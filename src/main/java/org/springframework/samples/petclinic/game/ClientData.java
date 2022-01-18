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

    //Equates to where does the player put the worker
    //TODO: Make it possible to use resources instead of workers to invoke special actions
    public Integer playerAction;
}
