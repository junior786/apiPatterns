package com.junior.modules.validation.implementation;

import com.junior.modules.validation.SexoValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class SexoImplementation implements ConstraintValidator<SexoValid, String> {
    @Override
    public void initialize(SexoValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        value = value.toLowerCase(Locale.ROOT);
        return value.equals("masculino") || value.equals("feminino");
    }
}
