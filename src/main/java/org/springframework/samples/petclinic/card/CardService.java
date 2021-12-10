package org.springframework.samples.petclinic.card;

import org.springframework.beans.factory.annotation.Autowired;

public class CardService {
    
    @Autowired
    CardRepository cardRepository;

    public Card2 cardToCard2 (Card card) {
        Card2 res = new Card2();
        res.setCardImage(card.getCardImage());
        res.setCardType(card.getCardType());
        res.setDescription(card.getDescription());
        res.setId(card.getId());
        res.setPosition(card.getId());
        res.setTitle(card.getTitle());
        res.setEffect(CardEffect.getEffects().get(card.getEffectId()));
        return res;
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
