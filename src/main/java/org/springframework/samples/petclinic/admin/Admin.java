package org.springframework.samples.petclinic.admin;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "admins")
public class Admin extends User{
  
}
