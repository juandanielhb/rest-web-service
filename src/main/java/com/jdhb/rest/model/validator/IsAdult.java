package com.jdhb.rest.model.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Documented
@Constraint(validatedBy = IsAdultValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsAdult {
    String message() default "must be over 18";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}