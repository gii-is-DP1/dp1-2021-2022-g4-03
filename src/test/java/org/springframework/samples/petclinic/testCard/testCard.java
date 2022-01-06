package org.springframework.samples.petclinic.testCard;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.validatorFunction;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardType;

public class testCard {
    
    @Test
    public void shouldNotValidateWhenParametersAreNull(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    Card card = new Card();
    card.setTitle(null);
    card.setCardImage(null);
    card.setCardType(null);
    card.setDescription(null);
    card.setEffect(null);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<Card>> constraintViolations =
    validator.validate(card);

    assertThat(constraintViolations.size()).isEqualTo(5);

    assertAll("constrainViolations",
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("cardImage")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("cardType")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");

    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("title")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("description")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("effect")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    }
    );
    }


    @Test
    public void shouldValidateCard(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    Card card = new Card();
    card.setTitle("El hombre del saco");
    card.setCardImage("/resources/cards/o_bicho_papao.png");
    card.setCardType(CardType.DEFENSA);
    card.setDescription("O bicho papao vai chupar seu pau");
    card.setEffect("+5h-3o");

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<Card>> constraintViolations =
    validator.validate(card);

    assertThat(constraintViolations.size()).isEqualTo(0);
    }

}
