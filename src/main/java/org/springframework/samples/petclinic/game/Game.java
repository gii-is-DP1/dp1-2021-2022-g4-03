package org.springframework.samples.petclinic.game;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Game extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="userId1", referencedColumnName = "username")
    })
    private UserDwarf player1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="userId2", referencedColumnName = "username")
    })
    private UserDwarf player2;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="userId3", referencedColumnName = "username")
    })
    private UserDwarf player3;

    @ElementCollection
    @Size(min=3,max=3)
    private List<Integer> order;
    @NotNull
    private Phase phase;
    @NotNull
    private GameStatus gameStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="boardId", referencedColumnName = "id")
    private Board board;

}