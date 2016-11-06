package pack.spring;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.Temporal;

/**
 * Created by user on 04.11.16.
 */

public class PastValidator implements ConstraintValidator<Past, Temporal> {

	@Override
	public void initialize(Past constraintAnnotation) {
	}

	@Override
	public boolean isValid(Temporal value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		LocalDate ld = LocalDate.from(value);
		if (ld.isBefore(LocalDate.now())) {
			return true;
		}
		return false;
	}

}