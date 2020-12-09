package validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CategoryValidator.class)
public @interface CategoryAttribute {

    Class<? extends Enum<?>> enumClazz();
    String message() default "Invalid Input";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}