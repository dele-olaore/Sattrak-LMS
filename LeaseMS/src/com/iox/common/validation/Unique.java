package com.iox.common.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Responsible for enforcing uniqueness in a column or property. We would have
 * used
 * <code>@Column(unique=true)</code> but this will fail in our case since we are
 * running the same database for different clients or organizations. So as a
 * workaround, we use this annotation instead on unique fields and evaluate it
 * at persistence level.
 * @author Nwanyanwu I. Uchenna
 * @created May 14, 2013 11:47:00 AM
 */
@Target(
{
    ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface Unique
{

    /**
     * Default Message String
     *
     * @return
     */
    String message() default "{com.iox.common.validation.Unique}";

    Class<?>[] groups() default 
    {
    };

    Class<? extends Payload>[] payload() default 
    {
    };
}
