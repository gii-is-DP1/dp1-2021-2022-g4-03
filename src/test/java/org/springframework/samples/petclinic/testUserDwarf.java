package org.springframework.samples.petclinic;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;


public class testUserDwarf {
 
    @Test
    public void shouldNotValidateWhenUsernameIsEmpty(){
        
        LocaleContextHolder.setLocale(Locale.ENGLISH);  
        UserDwarf userDwarf = new UserDwarf();
        userDwarf.setActive(true);
        userDwarf.setEmail("user@user.com");
        userDwarf.setUsername("");
        userDwarf.setPass("Password123");

        Validator validator = validatorFunction.createValidator();
        Set<ConstraintViolation<UserDwarf>> constraintViolations = validator.validate(userDwarf);

        assertThat(constraintViolations.size()).isEqualTo(2);
        Iterator <ConstraintViolation<UserDwarf>> var = constraintViolations.iterator();
        ConstraintViolation<UserDwarf> violation = var.next();

        System.out.println(constraintViolations);

        assertThat(violation.getPropertyPath().toString()).isEqualTo("username");
        assertThat(violation.getMessage()).isEqualTo("must not be empty");
        violation = var.next();
        assertThat(violation.getMessage()).isEqualTo("size must be between 4 and 20");

    } 

    @Test
    public void shouldNotValidateWhenEmailIsEmpty(){
        
        LocaleContextHolder.setLocale(Locale.ENGLISH);  
        UserDwarf userDwarf = new UserDwarf();
        userDwarf.setActive(true);
        userDwarf.setEmail("");
        userDwarf.setUsername("Anon");
        userDwarf.setPass("Password123");

        Validator validator = validatorFunction.createValidator();
        Set<ConstraintViolation<UserDwarf>> constraintViolations = validator.validate(userDwarf);

        assertThat(constraintViolations.size()).isEqualTo(1);
        Iterator <ConstraintViolation<UserDwarf>> var = constraintViolations.iterator();
        ConstraintViolation<UserDwarf> violation = var.next();

        assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
        assertThat(violation.getMessage()).isEqualTo("must not be empty");
    } 

    @Test
    public void shouldNotValidateWhenPassIsEmpty(){
        
        LocaleContextHolder.setLocale(Locale.ENGLISH);  
        UserDwarf userDwarf = new UserDwarf();
        userDwarf.setActive(true);
        userDwarf.setEmail("test@test.com");
        userDwarf.setUsername("Anon");
        userDwarf.setPass("");

        Validator validator = validatorFunction.createValidator();
        Set<ConstraintViolation<UserDwarf>> constraintViolations = validator.validate(userDwarf);

        assertThat(constraintViolations.size()).isEqualTo(2);
        Iterator <ConstraintViolation<UserDwarf>> var = constraintViolations.iterator();
        ConstraintViolation<UserDwarf> violation = var.next();

        assertThat(violation.getPropertyPath().toString()).isEqualTo("pass");
        
        assertThat(violation.getMessage()).isEqualTo("must contain 8 characters, one uppercase and numbers");
        violation = var.next();
        assertThat(violation.getMessage()).isEqualTo("must not be empty");
        
    } 

    // @Test
    // public void shouldNotValidateWhenParametersAreEmpty(){
        
    //     LocaleContextHolder.setLocale(Locale.ENGLISH);  
    //     UserDwarf userDwarf = new UserDwarf();
    //     userDwarf.setEmail("");
    //     userDwarf.setUsername("");
    //     userDwarf.setPass("");

    //     Validator validator = validatorFunction.createValidator();
    //     Set<ConstraintViolation<UserDwarf>> constraintViolations = validator.validate(userDwarf);

    //     assertThat(constraintViolations.size()).isEqualTo(5);
    //     Iterator <ConstraintViolation<UserDwarf>> var = constraintViolations.iterator();
    //     ConstraintViolation<UserDwarf> violation = var.next();

    //     assertThat(violation.getPropertyPath().toString()).isEqualTo("pass");
    //     assertThat(violation.getMessage()).isEqualTo("must not be empty");
    //     violation = var.next();

    //     assertThat(violation.getMessage()).isEqualTo("must contain 8 characters, one uppercase and numbers");
    //     violation = var.next();

    //     assertThat(violation.getPropertyPath().toString()).isEqualTo("username");
    //     assertThat(violation.getMessage()).isEqualTo("must not be empty");
    //     violation = var.next();

    //     assertThat(violation.getMessage()).isEqualTo("size must be between 4 and 20");
    //     violation = var.next();
                
    //     assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
    //     assertThat(violation.getMessage()).isEqualTo("must not be empty");
    // } 
    
}
