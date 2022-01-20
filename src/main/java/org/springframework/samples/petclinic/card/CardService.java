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
        Stream<Card> stream = cardRepository.findAll().stream();
        return stream.collect(Collectors.toList());
    }

    @Transactional
    public List<Card> findAllSpecialCards(){
        return cardRepository.findAll().stream().filter(card -> card.getCardType().isSpecial()).sorted(Comparator.comparing(Card::getId)).collect(Collectors.toList());
    }

    @Transactional
    public List<Card> findAllNormalCards(){
        return cardRepository.findAll().stream().filter(card -> !(card.getCardType().isSpecial()||card.isInitial())).sorted(Comparator.comparing(Card::getId)).collect(Collectors.toList());
    }

    @Transactional
    public List<Card> findAllInitialCards(){
        return cardRepository.findAll().stream().filter(card -> card.initial).sorted(Comparator.comparing(Card::getPosition)).collect(Collectors.toList());
    }

}
