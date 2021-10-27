package org.springframework.samples.petclinic.player;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.userDwarf.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "players")
public class Player extends User{
    
}
