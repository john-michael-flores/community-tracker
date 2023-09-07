package com.membermanagement.Member.Management.validator;

import org.apache.commons.validator.GenericValidator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyDateValidator.class)
@Documented
public @interface CustomDateValidator {

    String message() default "invalid date format";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

class MyDateValidator implements ConstraintValidator<CustomDateValidator, LocalDate> {
    public void initialize(CustomDateValidator constraint) {
    }

    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        // validate the value here.


        return GenericValidator.isDate(value.toString(), "yyyy-MM-dd", true) && !value.toString().equals("1970-01-03");
    }
}
