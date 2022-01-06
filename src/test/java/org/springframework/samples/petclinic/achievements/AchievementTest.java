package org.springframework.samples.petclinic.achievements;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.validatorFunction;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
public class AchievementTest {
    

@Test
public void shouldNotValidateWhenParametersAreEmpty(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    Achievements achievements = new Achievements();
    achievements.setDescription("");
    achievements.setCondition("");
    achievements.setLastChange(null);
    achievements.setPic("");

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<Achievements>> constraintViolations =
    validator.validate(achievements);

    assertThat(constraintViolations.size()).isEqualTo(6);

    assertAll("constrainViolations", 
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("description")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be empty", "size must be between 3 and 50");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("pic")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be empty");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("condition")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be empty", "size must be between 3 and 50");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("lastChange")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    }
    );
    
    }

    @Test
    public void shouldValidate(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    Achievements achievements = new Achievements();
    achievements.setDescription("roubwrnbeqw");
    achievements.setCondition("barpihwp");
    achievements.setLastChange(LocalDate.of(2022, 04, 05));
    achievements.setPic("/resources/images/picaxe.png");

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<Achievements>> constraintViolations =
    validator.validate(achievements);

    assertThat(constraintViolations.size()).isEqualTo(0);
    
    }
}

