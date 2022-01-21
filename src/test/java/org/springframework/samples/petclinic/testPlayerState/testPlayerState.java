package org.springframework.samples.petclinic.testPlayerState;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.playerState.PlayerState;
import org.springframework.samples.petclinic.validatorFunction;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class testPlayerState {


    @Test
    public void shouldNotValidateWhenParametersAreNull(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    PlayerState playerState = new PlayerState();
    playerState.setIron(null);
    playerState.setGold(null);
    playerState.setSteel(null);
    playerState.setObject(null);
    playerState.setMedal(null);
    playerState.setWorker1(null);
    playerState.setWorker2(null);
    playerState.setWorker3(null);
    playerState.setWorker0(null);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<PlayerState>> constraintViolations =
    validator.validate(playerState);

    assertThat(constraintViolations.size()).isEqualTo(9);

    assertAll("constrainViolations",
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("iron")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("gold")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");

    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("steel")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("object")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("medal")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker1")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker2")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker3")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker0")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    }
    );

    }

    @Test
    public void shouldNotValidateWhenValueParametersAreLowerThanMin(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    PlayerState playerState = new PlayerState();
    playerState.setIron(-1);
    playerState.setGold(-1);
    playerState.setSteel(-1);
    playerState.setObject(-1);
    playerState.setMedal(-1);
    playerState.setWorker1(-22);
    playerState.setWorker2(-22);
    playerState.setWorker3(-22);
    playerState.setWorker0(-22);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<PlayerState>> constraintViolations =
    validator.validate(playerState);

    assertThat(constraintViolations.size()).isEqualTo(9);

    assertAll("constrainViolations",
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("iron")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("gold")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");

    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("steel")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("object")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("medal")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to 0");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker1")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to -13");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker2")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to -13");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker3")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to -13");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker0")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to -13");
    }
    );

    }

    @Test
    public void shouldNotValidateWhenValueParametersAreGreaterThanMax(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    PlayerState playerState = new PlayerState();
    playerState.setIron(1);
    playerState.setGold(1);
    playerState.setSteel(1);
    playerState.setObject(1);
    playerState.setMedal(1);
    playerState.setWorker0(133);
    playerState.setWorker2(133);
    playerState.setWorker3(133);
    playerState.setWorker1(133);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<PlayerState>> constraintViolations =
    validator.validate(playerState);

    assertThat(constraintViolations.size()).isEqualTo(4);

    assertAll("constrainViolations",
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker0")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be less than or equal to 12");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker2")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be less than or equal to 12");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker3")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be less than or equal to 12");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker1")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be less than or equal to 12");
    }
    );

    }

    @Test
    public void shouldValidatePlayerState(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    PlayerState playerState = new PlayerState();
    playerState.setIron(1);
    playerState.setGold(1);
    playerState.setSteel(1);
    playerState.setObject(1);
    playerState.setMedal(1);
    playerState.setWorker1(9);
    playerState.setWorker2(9);
    playerState.setWorker3(9);
    playerState.setWorker0(9);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<PlayerState>> constraintViolations =
    validator.validate(playerState);

    assertThat(constraintViolations.size()).isEqualTo(0);
    }

}

