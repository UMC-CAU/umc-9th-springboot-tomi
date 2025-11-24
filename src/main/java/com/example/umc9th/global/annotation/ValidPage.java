package com.example.umc9th.global.annotation;

import com.example.umc9th.global.validator.PageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PageValidator.class)
public @interface ValidPage {

    String message() default "page must be greater than 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
