package org.springframework.samples.petclinic;
import javax.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
public class validatorFunction {
    
    public static Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean =
        new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
        }
}
