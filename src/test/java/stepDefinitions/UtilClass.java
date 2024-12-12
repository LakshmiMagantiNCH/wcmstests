package stepDefinitions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.text.RandomStringGenerator;
import org.testng.annotations.Test;

public class UtilClass {

	public static String generateCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedDate = currentDate.format(formatter);
		return formattedDate;
	}


    public static String generateRandomFutureDate() {
		LocalDate currentDate = LocalDate.now();
		LocalDate futureDate = currentDate.plusYears(2);
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedFutureDate = futureDate.format(dateFormatter);
		return formattedFutureDate;
	}

	public static String generateRandomString(int length) {
		  RandomStringGenerator generator = new RandomStringGenerator
                                            .Builder()
                                            .withinRange('0','z')
                                            .filteredBy(Character::isLetterOrDigit).build();	
        return generator.generate(length);
	}
	
	
	
}
