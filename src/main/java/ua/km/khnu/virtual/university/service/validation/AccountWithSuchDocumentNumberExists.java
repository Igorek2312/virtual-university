package ua.km.khnu.virtual.university.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Igor Rybak
 */
@Constraint(validatedBy = AccountWithSuchDocumentNumberExistsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccountWithSuchDocumentNumberExists {
    String message() default "{ua.km.khnu.virtual.university.service.validation.AccountWithSuchDocumentNumberExists.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
