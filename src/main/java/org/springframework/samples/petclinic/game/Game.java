package org.springframework.samples.petclinic.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.playerState.PlayerState;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Entity
public class Game extends BaseEntity {

    @Min(value = 0)
    @Max(value = 3)
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

    @OneToOne(cascade = CascadeType.REMOVE)
    @Transient
    private PlayerState playerState_0 = new PlayerState();

    @OneToOne(cascade = CascadeType.REMOVE)
    @Transient
    private PlayerState playerState_1 = new PlayerState();

    @OneToOne(cascade = CascadeType.REMOVE)
    @Transient
    private PlayerState playerState_2 = new PlayerState();


    @ElementCollection
    @Size(min = 3, max = 3)
    @Transient
    private List<Integer> order;
    @NotNull
    private Phase phase;
    @NotNull
    private GameStatus gameStatus;
    @Transient
    private Integer activePlayer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boardId", referencedColumnName = "id")
    private Board board;


    //Order in which players will take their turn, effectively a stack of ints.
    @Transient
    @JsonIgnore
    private List<Integer> turnsOrder = new ArrayList<>();

    //Order in which players took help actions, and must take new turns assigning workers.
    @Transient
    @JsonIgnore
    private List<Integer> helpTurnsOrder = new ArrayList<>();

    @JsonIgnore
    public List<PlayerState> getAllPlayerStates() {
        return new ArrayList<>(List.of(playerState_0, playerState_1, playerState_2));
    }

    @JsonIgnore
    public List<UserDwarf> getAllPlayersInGame(){
        List<UserDwarf> auxList= new ArrayList<>(List.of(player0, player1,player2));

        auxList.removeIf(Objects::isNull);

        return auxList;
    }

}
