package org.springframework.samples.petclinic.testPlayerState;

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
import org.springframework.samples.petclinic.playerState.PlayerState;

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
    //playerState.setActive(null);
    playerState.setWorker1(null);
    playerState.setWorker2(null);
    playerState.setWorker3(null);
    playerState.setWorker4(null);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<PlayerState>> constraintViolations =
    validator.validate(playerState);

    assertThat(constraintViolations.size()).isEqualTo(10);

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
                .filter(c -> c.getPropertyPath().toString().equals("active")).map(v -> v.getMessage())
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
                .filter(c -> c.getPropertyPath().toString().equals("worker4")).map(v -> v.getMessage())
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
    //playerState.setActive(null);
    playerState.setWorker1(-2);
    playerState.setWorker2(-2);
    playerState.setWorker3(-2);
    playerState.setWorker4(-2);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<PlayerState>> constraintViolations =
    validator.validate(playerState);

    assertThat(constraintViolations.size()).isEqualTo(10);

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
                .filter(c -> c.getPropertyPath().toString().equals("active")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker1")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to -1");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker2")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to -1");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker3")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to -1");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker4")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must be greater than or equal to -1");
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
    //playerState.setActive(true);
    playerState.setWorker1(13);
    playerState.setWorker2(13);
    playerState.setWorker3(13);
    playerState.setWorker4(13);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<PlayerState>> constraintViolations =
    validator.validate(playerState);

    assertThat(constraintViolations.size()).isEqualTo(4);

    assertAll("constrainViolations",
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("worker1")).map(v -> v.getMessage())
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
                .filter(c -> c.getPropertyPath().toString().equals("worker4")).map(v -> v.getMessage())
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
    //playerState.setActive(true);
    playerState.setWorker1(9);
    playerState.setWorker2(9);
    playerState.setWorker3(9);
    playerState.setWorker4(9);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<PlayerState>> constraintViolations =
    validator.validate(playerState);

    assertThat(constraintViolations.size()).isEqualTo(0);
    }

}
