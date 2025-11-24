package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {

        if (page == null) return true;   // null은 기본값 적용됨 (1)

        return page > 0;  // 1보다 커야 함
    }
}
