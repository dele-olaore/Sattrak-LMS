package com.iox.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object>
{
	/*
	 *  @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation) 
     */
    @Override
    public void initialize(Unique a)
    {
    }
    /**
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext) 
     */
    @Override
    public boolean isValid(Object t, ConstraintValidatorContext cvc)
    {
        return true;
    }
}
