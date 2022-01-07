package org.springframework.samples.petclinic.testUserAchievements;

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
import org.springframework.samples.petclinic.achievements.Achievements;
import org.springframework.samples.petclinic.achievements.AchievementsService;
import org.springframework.samples.petclinic.achievements.UserAchievements;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;

public class testUserAchievements {

@Test
public void shouldNotValidateWhenParametersAreEmpty(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    UserAchievements userAchievements = new UserAchievements();
    userAchievements.setProgress(null);
    userAchievements.setObtainingDate(null);;
    userAchievements.setUserDwarf(null);
    userAchievements.setAchievements(null);

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<UserAchievements>> constraintViolations =
    validator.validate(userAchievements);

    assertThat(constraintViolations.size()).isEqualTo(4);

    assertAll("constrainViolations", 
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("progress")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("obtainingDate")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("userDwarf")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    },
    () -> {
        List<String> violationsList = constraintViolations.stream()
                .filter(c -> c.getPropertyPath().toString().equals("achievements")).map(v -> v.getMessage())
                .collect(Collectors.toList());
        assertThat(violationsList).containsExactlyInAnyOrder("must not be null");
    }
    );
    
    }

    @Test
    public void shouldValidate(){

    LocaleContextHolder.setLocale(Locale.ENGLISH);
    UserAchievements userAchievements = new UserAchievements();
    userAchievements.setProgress(0.4);
    userAchievements.setObtainingDate(LocalDate.of(2020, 05, 05));;
    userAchievements.setUserDwarf(new UserDwarf());
    userAchievements.setAchievements(new Achievements());

    Validator validator = validatorFunction.createValidator();
    Set<ConstraintViolation<UserAchievements>> constraintViolations =
    validator.validate(userAchievements);

    assertThat(constraintViolations.size()).isEqualTo(0);
    
    }
    
}
