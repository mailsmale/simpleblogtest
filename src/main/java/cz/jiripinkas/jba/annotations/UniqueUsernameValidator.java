package cz.jiripinkas.jba.annotations;

import cz.jiripinkas.jba.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUserName, String> {

    @Autowired
    UserRepository userRepository;


    @Override
    public void initialize(UniqueUserName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        if(userRepository == null) {
            return true;
        }
        return userRepository.findByName(userName) == null;
    }
}
