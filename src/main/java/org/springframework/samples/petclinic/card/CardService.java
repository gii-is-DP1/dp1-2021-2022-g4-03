package org.springframework.samples.petclinic.card;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

public class CardService {
    
    @Autowired
    CardRepository cardRepository;

    public void deleteCard(Card card){
        cardRepository.delete(card);
    }

    public Card findCardById(int id) {
        return cardRepository.findById(id).get();
    }

    @Transactional
    public List<Card> findAll() {
        Stream<Card> stream = StreamSupport.stream(cardRepository.findAll().spliterator(), false);
        return stream.collect(Collectors.toList());
    }

}
