package ua.km.khnu.virtual.university.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Igor Rybak
 */
@Constraint(validatedBy = AccountWithSuchDocumentNumberIsDisabledValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccountWithSuchDocumentNumberIsDisabled {
    String message() default "{ua.km.khnu.virtual.university.service.validation.AccountWithSuchDocumentNumberIsDisabled.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
