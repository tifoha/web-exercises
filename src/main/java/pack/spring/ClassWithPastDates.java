package pack.spring;

/**
 * Created by user on 04.11.16.
 */
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClassWithPastDates {

	@Past
	private LocalDate date;

	@Past
	private LocalDateTime dateTime;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

}