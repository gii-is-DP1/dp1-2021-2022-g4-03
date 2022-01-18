package org.springframework.samples.petclinic.card;

import java.util.Comparator;
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
        return stream.dropWhile(card->card.getCardType().equals(CardType.ESPECIAL)||card.isInitial()).collect(Collectors.toList());
    }

    @Transactional
    public List<Card> findAllInitialCards(){
        Stream<Card> stream = StreamSupport.stream(cardRepository.findAll().spliterator(), false);
        return stream.takeWhile(Card::isInitial).sorted(Comparator.comparing(Card::getPosition)).collect(Collectors.toList());
    }

}
