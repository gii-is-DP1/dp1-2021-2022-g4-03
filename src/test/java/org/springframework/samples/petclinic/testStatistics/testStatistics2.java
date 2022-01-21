package org.springframework.samples.petclinic.testStatistics;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.statistics.Statistics;
import org.springframework.samples.petclinic.validatorFunction;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class testStatistics2 {
    @Test
    public void shouldNotValidateWhenParametersAreEmpty(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    Statistics statistics = new Statistics();
    statistics.setTimePlayed(null);
    statistics.setGamesPlayed(-1);
    statistics.setGamesWon(-2);
    statistics.setTotalIron(-3);
    statistics.setTotalGold(-4);
    statistics.setTotalSteel(-5);
    statistics.setTotalObject(-6);
    statistics.setTotalMedal(-7);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<Statistics>> constraintViolations =
    validator.validate(statistics);

    assertThat(constraintViolations.size()).isEqualTo(8);

    assertAll("constrainViolations",
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("timePlayed")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("gamesPlayed")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("gamesWon")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("totalIron")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("totalGold")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("totalSteel")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("totalObject")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("totalMedal")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");
    }
    );
    
    }

    @Test
    public void shouldValidate(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    Statistics statistics = new Statistics();
    statistics.setTimePlayed(Duration.ofHours(8));
    statistics.setGamesPlayed(0);
    statistics.setGamesWon(0);
    statistics.setTotalIron(0);
    statistics.setTotalGold(0);
    statistics.setTotalSteel(0);
    statistics.setTotalObject(0);
    statistics.setTotalMedal(0);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<Statistics>> constraintViolations =
    validator.validate(statistics);

    assertThat(constraintViolations.size()).isEqualTo(0);
    
    }

    
}