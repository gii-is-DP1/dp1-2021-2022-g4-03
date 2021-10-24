package org.springframework.samples.petclinic.configuration.Admin;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.configuration.Users.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "admins")
public class Admin extends User{
  
}
