package com.junior.modules.validation;

import com.junior.modules.validation.implementation.SexoImplementation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SexoImplementation.class)
public @interface SexoValid {

    String message() default "Sexo invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
