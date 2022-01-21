package org.springframework.samples.petclinic.testCard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardRepository;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class testCardsService {

    @Autowired
    protected CardService cardService;

    @Autowired
    protected CardRepository cardRepository;

    @Test
    public void testCountWithInitialData() {
        Integer count = cardService.findAll().size();
        assertEquals(count, 65); 
    }

    @ParameterizedTest
    @CsvSource({"Alloy Steel, 1", "Gold Seam, 2"})
    public void shouldFindCardsWithCorrectId(String tittle, int id){
        Card c = this.cardService.findCardById(id);
        assertThat(c.getTitle().equals(tittle));
        assertThat(!c.getTitle().equals("Alloy metal"));
    }

    @Test
    @Transactional
    public void shouldDeleteCards(){

        Card card = cardService.findCardById(1);

        Integer count1 = cardService.findAll().size();

        cardService.deleteCard(card);

        Integer count2 = cardService.findAll().size();

        assertThat(count1-1).isEqualTo(count2);        
    }


    
    @Test
    public void testFindAllCards(){
        List<Card> fAll = cardService.findAll();
        assertEquals(fAll.size(), cardRepository.count());
        assertThat(fAll.isEmpty()).isFalse();

    }

    @Test
    public void testFindAllEspecialCards(){
        List<Card> fAll = cardService.findAllSpecialCards();
        assertEquals(fAll.size(), 9);
        assertThat(fAll.isEmpty()).isFalse();

    }

    @Test
    public void testFindAllNormalCards(){
        List<Card> fAll = cardService.findAllNormalCards();
        assertEquals(fAll.size(), 47);
        assertThat(fAll.isEmpty()).isFalse();

    }

    @Test
    public void testFindAllInitialCards(){
        List<Card> fAll = cardService.findAllInitialCards();
        assertEquals(fAll.size(), 9);
        assertThat(fAll.isEmpty()).isFalse();

    }
    
}
