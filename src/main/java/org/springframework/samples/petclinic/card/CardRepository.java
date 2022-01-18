package org.springframework.samples.petclinic.card;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {

    List<Card> findAll();

}
