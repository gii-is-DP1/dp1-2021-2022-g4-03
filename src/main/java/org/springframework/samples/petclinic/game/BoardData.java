package org.springframework.samples.petclinic.game;

import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.playerState.PlayerState;

/*
* Clase tipo wrapper que contiene los datos actuales del tablero
*/

@Getter
@Setter
public class BoardData{
    private String currentUser;

    //Equates to where does the player put the worker
    private Integer playerAction;
}
