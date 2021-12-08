package org.springframework.samples.petclinic.card;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CardService {
    
    @Autowired
    CardRepository cardRepository;

    private void cardEffectConstructor (String effect) {
        // Card(String effect) -> Card(Predicate effect)
    }

//    @Transactional
//    public List<Card> getAllCards() {
//        Stream<Card> str = StreamSupport.stream(cardRepository.findAll().spliterator(), false);
//        return str.map(Card::getEffect)
//            .forEach(x->cardEffectConstructor(x))
//            .collect(Collectors.toList());
//    }
    

}
