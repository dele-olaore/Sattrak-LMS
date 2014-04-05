package com.iox.common.validation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target(
{
    ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EntityIdNotNullValidator.class)
@Documented
public  @interface EntityIdNotNull 
{
	/**
     * Default Message String
     * @return 
     */
    String message() default "{com.niu.common.validation.EntityIdNotNull}";

    Class<?>[] groups() default 
    {
    };

    Class<? extends Payload>[] payload() default 
    {
    };
}
