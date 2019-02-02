package com.example.demo.pb2UserSystem.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password,String> {
    private int minLength;
    private int maxLength;
    private boolean containsDigit;
    private boolean containsLowerCase;
    private boolean containsUpperCase;
    private boolean containsSpecialSymbols;

    @Override
    public void initialize(Password passwordAnnotation) {
        this.minLength=passwordAnnotation.minLength();
        this.maxLength=passwordAnnotation.maxLength();
        this.containsDigit=passwordAnnotation.containsDigit();
        this.containsLowerCase =passwordAnnotation.containsLowerCase();
        this.containsUpperCase=passwordAnnotation.containsUpperCase();
        this.containsSpecialSymbols=passwordAnnotation.containsSpecialSymbols();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if(password==null){
            AnnotationMessageSetter.serAnnotationErrorMessage(context,"The password is missing!");
            return false;
        }
        if(password.length()<this.minLength){
            AnnotationMessageSetter.serAnnotationErrorMessage(context,
                    String.format("The password must have at least %d symbols!",this.minLength));
            return false;
        }
        if(password.length()>this.maxLength){
            AnnotationMessageSetter.serAnnotationErrorMessage(context,
                    String.format("The password must not exceed %d symbols!",this.maxLength));
            return false;
        }
        boolean containsDigit=password.matches("^.*\\d+.*$");
        if(!containsDigit && this.containsDigit){
            AnnotationMessageSetter.serAnnotationErrorMessage(context,
                    "The password must contain at least one digit!");
            return false;
        }
        boolean containsLowercase=password.matches("^.*[a-z].*$");
        if(!containsLowercase && this.containsLowerCase){
            AnnotationMessageSetter.serAnnotationErrorMessage(context,
                    "The password must contain at least one lowercase letter!");
            return false;
        }
        boolean containsUpperCase=password.matches("^.*[A-Z].*$");
        if(!containsUpperCase && this.containsUpperCase){
            AnnotationMessageSetter.serAnnotationErrorMessage(context,
                    "The password must contain at least one uppercase letter!");
            return false;
        }
        boolean notContainsSpecialChars=password.matches("^[\\w-]+$");
        if(notContainsSpecialChars&&this.containsSpecialSymbols){
            AnnotationMessageSetter.serAnnotationErrorMessage(context,
                    "The password must contain at least one special symbol");
            return false;
        }
        return true;
    }
}
