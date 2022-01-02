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

    private Card2 cardToCard2 (Card card) {
        Card2 res = new Card2();
        res.setCardImage(card.getCardImage());
        res.setCardType(card.getCardType());
        res.setDescription(card.getDescription());
        res.setId(card.getId());
        res.setPosition(card.getId());
        res.setTitle(card.getTitle());
       // res.setEffect(CardEffect.getEffects().get(card.getEffectId()));
        return res;
    }

    public void deleteCard(Card card){
        cardRepository.delete(card);
    }
    
    public Card2 findCard2ById(int id) {
        return cardToCard2(cardRepository.findById(id).get());
    }

    // List<Card2> findCards2ByType(Card.Type type)

    @Transactional
    public List<Card2> findAllCards2() {
        Stream<Card> stream = StreamSupport.stream(cardRepository.findAll().spliterator(), false);
        return stream.map(x->cardToCard2(x))
            .collect(Collectors.toList());
    }

    public Card findCardById(int id) {
        return cardRepository.findById(id).get();
    }

    // Iterable<Card> findAll
    @Transactional
    public List<Card> findAllCards() {
        Stream<Card> stream = StreamSupport.stream(cardRepository.findAll().spliterator(), false);
        return stream.collect(Collectors.toList());
    }

}
