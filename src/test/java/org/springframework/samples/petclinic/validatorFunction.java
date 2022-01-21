package org.springframework.samples.petclinic;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

public class validatorFunction {

    public static Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean =
        new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
        }
    
}
