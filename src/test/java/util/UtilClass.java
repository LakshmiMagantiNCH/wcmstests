package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import config.WebDriverFactory;

public class UtilClass {
	

	public static String generateCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		 DateTimeFormatter formatter =  WebDriverFactory.isFirefox
		            ? DateTimeFormatter.ofPattern("yyyy-MM-dd") // Firefox format
		            : DateTimeFormatter.ofPattern("MM/dd/yyyy"); // Default format (e.g., Chrome)
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedDate = currentDate.format(formatter);
		return formattedDate;
	}


    public static String generateRandomFutureDate() {
		LocalDate currentDate = LocalDate.now();
		LocalDate futureDate = currentDate.plusYears(2);
		 DateTimeFormatter dateFormatter = WebDriverFactory.isFirefox
		            ? DateTimeFormatter.ofPattern("yyyy-MM-dd") // Firefox format
		            : DateTimeFormatter.ofPattern("MM/dd/yyyy"); // Default format (e.g., Chrome)
//		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedFutureDate = futureDate.format(dateFormatter);
		return formattedFutureDate;
	}

	public static String generateRandomString(int length) {
//		  RandomStringGenerator generator = new RandomStringGenerator
//                                            .Builder()
//                                            .withinRange('0','z')
//                                            .filteredBy(Character::isLetterOrDigit).build();	
//        return generator.generate(length);
		
		 Random random = new Random();
	        StringBuilder name = new StringBuilder();

	        // Ensure the first character is uppercase
	        name.append((char) ('A' + random.nextInt(26)));

	        // Generate the remaining characters as lowercase
	        for (int i = 1; i < length; i++) {
	            name.append((char) ('a' + random.nextInt(26)));
	        }

	        return name.toString();
	}
	
	
	 public static int getRandomIntWithLength(int length) {
	        if (length < 1) {
	            throw new IllegalArgumentException("Length must be at least 1.");
	        }

	        int min = (int) Math.pow(10, length - 1); // Minimum value with the specified length
	        int max = (int) Math.pow(10, length) - 1; // Maximum value with the specified length

	        return ThreadLocalRandom.current().nextInt(min, max + 1);
	    }
	 
	  public static String captureScreenshot(WebDriver driver, String screenshotName) {
	        try {
	            String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            String dest = "capturedscreenshots/screenshots/" + screenshotName + timestamp + ".png";
	            File destination = new File(dest);
	            
	            FileUtils.copyFile(source, destination);
	            return dest;
	        } catch (IOException e) {
	            System.out.println("Exception while taking screenshot " + e.getMessage());
	            return null;
	        }
	    }
	  
	 

}
