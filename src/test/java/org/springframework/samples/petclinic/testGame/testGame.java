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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.validatorFunction;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameStatus;
import org.springframework.samples.petclinic.game.Phase;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;

public class testGame {
    @Autowired
    private UserDwarfService userDwarfService;

    @Test
    public void shouldNotValidateWhenParametersAreEmpty() {

        LocaleContextHolder.setLocale(Locale.ENGLISH);
        Game game = new Game();
        game.setOrder(List.of());
        game.setPhase(null);
        game.setGameStatus(null);
        game.setRound(null);
        game.setWinner(null);

        Validator validator = validatorFunction.createValidator();
        Set<ConstraintViolation<Game>> constraintViolations =
        validator.validate(game);

        assertThat(constraintViolations.size()).isEqualTo(5);

        assertAll("constrainViolations",
            () -> {
                List<String> violationsList = constraintViolations.stream()
                        .filter(c -> c.getPropertyPath().toString().equals("order")).map(v -> v.getMessage())
                        .collect(Collectors.toList());
                assertThat(violationsList).containsExactlyInAnyOrder("size must be between 3 and 3");
            },
            () -> {
                List<String> violationsList = constraintViolations.stream()
                        .filter(c -> c.getPropertyPath().toString().equals("phase")).map(v -> v.getMessage())
                        .collect(Collectors.toList());
                assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
            },
            () -> {
                List<String> violationsList = constraintViolations.stream()
                        .filter(c -> c.getPropertyPath().toString().equals("gameStatus")).map(v -> v.getMessage())
                        .collect(Collectors.toList());
                assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
            },
            () -> {
                List<String> violationsList = constraintViolations.stream()
                        .filter(c -> c.getPropertyPath().toString().equals("round")).map(v -> v.getMessage())
                        .collect(Collectors.toList());
                assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
            },
            () -> {
                List<String> violationsList = constraintViolations.stream()
                        .filter(c -> c.getPropertyPath().toString().equals("winner")).map(v -> v.getMessage())
                        .collect(Collectors.toList());
                assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
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
        game.setRound(1);
        game.setWinner("winner");

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<Game>> constraintViolations =
    validator.validate(game);

    assertThat(constraintViolations.size()).isEqualTo(0);

    }

}
