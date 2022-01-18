package org.springframework.samples.petclinic.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.model.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cell")
public class Cell extends BaseEntity {

    @ElementCollection
    private List<Integer> cards = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private Board board;

    @Transient
    @JsonIgnore
    public Integer getCardOnTop(){
        return cards.get(0);
    }

    @Transient
    @JsonIgnore
    public Integer removeCardOnTop(){
        return cards.remove(0);
    }

    @Transient
    @JsonIgnore
    public void addToTop(Integer id){
        cards.add(0, id);
    }

}
