package com.example.demo.pb2UserSystem.annotations;

import javax.validation.ConstraintValidatorContext;

public class AnnotationMessageSetter {

    public static void serAnnotationErrorMessage(ConstraintValidatorContext context,String message){
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }

}
