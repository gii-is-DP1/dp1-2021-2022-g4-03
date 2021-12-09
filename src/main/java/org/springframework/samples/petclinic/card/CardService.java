package org.springframework.samples.petclinic.card;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

public class CardService {
    
    @Autowired
    CardRepository cardRepository;

    private Predicate cardEffectConstructor(String effect) {
        // Takes a String and turns it into a predicate
    }

    public Card2 cardToCard2 (Card card) {
        Card2 res = new Card();
        card.setCardImage(res.getCardImage());
        card.setCardType(res.getCardType());
        card.setDescription(res.getDescription());
        card.setId(res.getId());
        card.setPosition(res.getId());
        card.setTitle(res.getTitle());
        card.setEffect(cardEffectConstructor(res.getEffect()));
    }

    // Saves Card2(effect:predicate) as Card (for the DB)
    @Transactional
	public void saveAsCard(Card2 card2) throws DataAccessException {
        Card card = new Card();
        card.setCardImage(card2.getCardImage());
        card.setCardType(card2.getCardType());
        card.setDescription(card2.getDescription());
        card.setId(card2.getId());
        card.setPosition(card2.getId());
        card.setTitle(card2.getTitle());
        card.setEffect(card2.getEffect().toString());
        cardRepository.save(card);
    }

    public void deleteCard(Card card){
        cardRepository.delete(card);
    }
    
    // Card2 findCard2ById<int id>: findById but returns a Card2

    // List<Card2> findCards2ByType(Card.Type type)

    // List<Card2> findAllCards2: List of cards stored in the DB converted to Card2(effect:predicate)

    // Card findCardById<int id>

    // Iterable<Card> findAll


//    @Transactional
//    public List<Card> getAllCards() {
//        Stream<Card> str = StreamSupport.stream(cardRepository.findAll().spliterator(), false);
//        return str.map(Card::getEffect)
//            .forEach(x->cardEffectConstructor(x))
//            .collect(Collectors.toList());
//    }
    

}
