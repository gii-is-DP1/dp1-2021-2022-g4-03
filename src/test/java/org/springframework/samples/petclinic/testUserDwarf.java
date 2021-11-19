package org.springframework.samples.petclinic;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


public class testUserDwarf {

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean =
        new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
        }

    @Test
    public void shouldNotValidateWhenUsernameIsEmpty(){
        
        LocaleContextHolder.setLocale(Locale.ENGLISH);  
        UserDwarf userDwarf = new UserDwarf();
        userDwarf.setActive(true);
        userDwarf.setEmail("user@user.com");
        userDwarf.setUsername("");
        userDwarf.setPass("pass");

        Validator validator = createValidator();
        Set<ConstraintViolation<UserDwarf>> constraintViolations = validator.validate(userDwarf);

        assertThat(constraintViolations.size()).isEqualTo(1);
        ConstraintViolation<UserDwarf> violation =constraintViolations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).isEqualTo("username");
        assertThat(violation.getMessage()).isEqualTo("must not be empty");

    } 
    @Test
    public void shouldNotValidateWhenParametersAreEmpty(){
        
        LocaleContextHolder.setLocale(Locale.ENGLISH);  
        UserDwarf userDwarf = new UserDwarf();
        userDwarf.setEmail("");
        userDwarf.setUsername("");
        userDwarf.setPass("");

        Validator validator = createValidator();
        Set<ConstraintViolation<UserDwarf>> constraintViolations = validator.validate(userDwarf);

        assertThat(constraintViolations.size()).isEqualTo(3);
        ConstraintViolation<UserDwarf> violation =constraintViolations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).isEqualTo("username");
        assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
        assertThat(violation.getPropertyPath().toString()).isEqualTo("pass");
        assertThat(violation.getMessage()).isEqualTo("must not be empty");

    } 
    
}
