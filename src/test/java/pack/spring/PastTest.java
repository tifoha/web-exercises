package pack.spring;


import static org.junit.Assert.assertEquals;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class PastTest {

	private ClassWithPastDates classUnderTest;

	@Before
	public void setup() {
		classUnderTest = new ClassWithPastDates();
	}

	@Test
	public void thatNullIsValid() {
		Set<ConstraintViolation<ClassWithPastDates>> violations = validateClass(classUnderTest);
		assertEquals(violations.size(), 0);
	}

	@Test
	public void thatYesterdayIsValid() throws Exception {
		classUnderTest.setDate(LocalDate.now().minusDays(1));
		classUnderTest.setDateTime(LocalDateTime.now().minusDays(1));
		Set<ConstraintViolation<ClassWithPastDates>> violations = validateClass(classUnderTest);
		assertEquals(violations.size(), 0);
	}

	@Test
	public void thatTodayIsInvalid() throws Exception {
		classUnderTest.setDate(LocalDate.now());
		classUnderTest.setDateTime(LocalDateTime.now());
		Set<ConstraintViolation<ClassWithPastDates>> violations = validateClass(classUnderTest);
		assertEquals(violations.size(), 2);
	}

	@Test
	public void thatTomorrowIsInvalid() throws Exception {
		classUnderTest.setDate(LocalDate.now().plusDays(1));
		classUnderTest.setDateTime(LocalDateTime.now().plusDays(1));
		Set<ConstraintViolation<ClassWithPastDates>> violations = validateClass(classUnderTest);
		assertEquals(violations.size(), 2);
	}

	private Set<ConstraintViolation<ClassWithPastDates>> validateClass(ClassWithPastDates myClass) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ClassWithPastDates>> violations = validator.validate(myClass);
		return violations;
	}

}