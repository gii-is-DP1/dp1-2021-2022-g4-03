package org.springframework.samples.petclinic.game;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.playerState.PlayerState;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Game extends BaseEntity {

    @Min(value=0)
    @Max(value=3)
    private Integer numberOfPlayers = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name = "userId0", referencedColumnName = "username")
    })
    private UserDwarf player0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name = "userId1", referencedColumnName = "username")
    })
    private UserDwarf player1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name = "userId2", referencedColumnName = "username")
    })
    private UserDwarf player2;

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "playerStateId0", referencedColumnName = "id")
    })
    private PlayerState playerState_0 = new PlayerState();

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "playerStateId1", referencedColumnName = "id")
    })
    private PlayerState playerState_1 = new PlayerState();

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "playerStateId2", referencedColumnName = "id")
    })
    private PlayerState playerState_2 = new PlayerState();


    @ElementCollection
    @Size(min = 3, max = 3)
    private List<Integer> order;
    @NotNull
    private Phase phase;
    @NotNull
    private GameStatus gameStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boardId", referencedColumnName = "id")
    private Board board;

}
