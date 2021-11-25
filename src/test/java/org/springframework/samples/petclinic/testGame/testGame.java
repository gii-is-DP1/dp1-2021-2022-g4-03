package org.springframework.samples.petclinic.testGame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.validatorFunction;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameStatus;
import org.springframework.samples.petclinic.game.Phase;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;

public class testGame {
    
    UserDwarfService userDwarfService;

    @Test
    public void shouldNotValidateWhenParametersAreEmpty() {

        LocaleContextHolder.setLocale(Locale.ENGLISH);
        Game game = new Game();
        game.setOrder(List.of());
    
        Validator validator = validatorFunction.createValidator();
        Set<ConstraintViolation<Game>> constraintViolations = 
        validator.validate(game);
    
        assertThat(constraintViolations.size()).isEqualTo(1);

        assertAll("constrainViolations", 
            () -> {
                List<String> violationsList = constraintViolations.stream()
                        .filter(c -> c.getPropertyPath().toString().equals("order")).map(v -> v.getMessage())
                        .collect(Collectors.toList());
                assertThat(violationsList).containsExactlyInAnyOrder("must contain the order of the three players");
            }
        );

    }

    @Test
    public void shouldValidate(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    Game game = new Game();
        game.setId(2);
        game.setOrder(List.of(1,2,3));
        game.setPhase(Phase.INICIO);
        game.setGameStatus(GameStatus.NEW);
        //game.setPlayer1(userDwarfService.findById(1));

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<Game>> constraintViolations =
    validator.validate(game);

    assertThat(constraintViolations.size()).isEqualTo(0);
    
    }

}