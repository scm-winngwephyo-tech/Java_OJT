package TestPackage;

import java.text.Format;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestDate {

	public static void main(String[] args) {
		 LocalDate myObj = LocalDate.now(); // Create a date object
		    System.out.println(myObj); // Display the current date
		 
		    LocalDateTime myObj1 = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		    String formattedDate = myObj1.format(myFormatObj);
		    System.out.println(myObj1);
	}

}
