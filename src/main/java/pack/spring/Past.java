package pack.spring;

/**
 * Created by user on 04.11.16.
 */

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastValidator.class)
@Documented
public @interface Past {

	String message() default "it.jdev.example.jsr310.validator.Past.message";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}