package com.jdhb.rest.model.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class IsAdultValidator implements ConstraintValidator<IsAdult, String> {

    @Override
    public void initialize(IsAdult constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String birthdateString, ConstraintValidatorContext ctx) {
        boolean dateFormatValid = birthdateString.matches("^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$");
        if (dateFormatValid) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthdate = LocalDate.parse(birthdateString, formatter);
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthdate, currentDate);

            return birthdate.isBefore(currentDate) && period.getYears() >= 18;
        } else {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate("invalid format. Use dd/MM/yyyy")
                    .addConstraintViolation();
            return false;
        }
    }
}
