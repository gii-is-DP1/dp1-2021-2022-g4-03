package org.springframework.samples.petclinic.card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    //TODO: Change values to corresponding card ids.
    private static final Map<String, Integer> special2normal =
        Map.of("muster", 0, "hold", 0, "sell", 0,
            "past", 0, "special", 0, "turn", 0,
            "apprentice", 0, "collapse", 0, "run", 0);

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

    @Transactional
    public List<Card> findAllSpecialCards(){
        Stream<Card> stream = StreamSupport.stream(cardRepository.findAll().spliterator(), false);
        return stream.filter(card->card.getCardType().equals(CardType.ESPECIAL)).collect(Collectors.toList());
    }

    @Transactional
    public List<Card> findAllNormalCards(){
        Stream<Card> stream = StreamSupport.stream(cardRepository.findAll().spliterator(), false);
        return stream.dropWhile(card->card.getCardType().equals(CardType.ESPECIAL)).collect(Collectors.toList());
    }

    public Integer translateSpecialToNormalId(String specialEffect){
        return special2normal.get(specialEffect);
    }
}
