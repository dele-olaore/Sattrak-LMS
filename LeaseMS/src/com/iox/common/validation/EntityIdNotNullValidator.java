package com.iox.common.validation;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.iox.common.util.Util;
import com.iox.lms.model.common.ReferenceEntityData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EntityIdNotNullValidator implements ConstraintValidator<EntityIdNotNull, ReferenceEntityData>
{
    /**
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation) 
     */
    @Override
    public void initialize(EntityIdNotNull a)
    {
        
    }
    /**
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext) 
     */
    @Override
    public boolean isValid(ReferenceEntityData t, ConstraintValidatorContext cvc)
    {
        if(t == null || Util.isNullOrZero(t.getId()))
        {
            return false;
        }
        return true;
    }
}

