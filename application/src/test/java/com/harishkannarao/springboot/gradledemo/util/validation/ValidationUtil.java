package com.harishkannarao.springboot.gradledemo.util.validation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtil {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> Set<Violation> validate(T object, Class<?>... groups) {
        return validator.validate(object, groups)
                .stream()
                .map(violation -> Violation.builder()
                        .propertyPath(violation.getPropertyPath().toString())
                        .message(violation.getMessage())
                        .build())
                .collect(Collectors.toSet());
    }
}
