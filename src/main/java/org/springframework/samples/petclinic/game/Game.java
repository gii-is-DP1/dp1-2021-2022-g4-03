package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game extends BaseEntity{
    
    private UserDwarf player1;
    private UserDwarf player2;
    private UserDwarf player3;
    private List<Integer> order;
    private Phase phase;
    private GameStatus gameStatus;
    private Board board;

}
