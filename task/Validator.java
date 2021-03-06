package task;

import java.util.Scanner;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class Validator {

	/**
	 * Get any valid integer.
	 */
	public static int getInt(Scanner scnr, String prompt) {
		// This approach use "hasNext" look ahead.
		System.out.print(prompt);
		while (!scnr.hasNextInt()) {
			scnr.nextLine(); // clear bad line.
			System.out.println("Sorry, I can't read that. Enter a whole number, using digits.");
			System.out.println(prompt);
		}
		int result = scnr.nextInt();
		scnr.nextLine(); // clear for next line of input.
		return result;
	}

	/**
	 * Get any valid double.
	 */
	public static double getDouble(Scanner scnr, String prompt) {
		// This approach use "hasNext" look ahead.
		boolean isValid = false;
		do {
			System.out.print(prompt);
			isValid = scnr.hasNextDouble();
			if (!isValid) {
				scnr.nextLine(); // clear bad line.
				System.out.println("Sorry, I can't read that. Enter a number, using digits.");
			}
		} while (!isValid);

		double number = scnr.nextDouble();
		scnr.nextLine(); // clear for next line of input.
		return number;
	}

	/**
	 * Get any string.
	 */
	public static String getString(Scanner scnr, String prompt) {
		// This approach uses exception handling.
		System.out.print(prompt);
		return scnr.nextLine();
	}

	/**
	 * Get any valid integer between min and max.
	 */
	public static int getInt(Scanner scnr, String prompt, int min, int max) {
		boolean isValid = false;
		int number;
		do {
			number = getInt(scnr, prompt);

			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}

	/**
	 * Get any valid double between min and max.
	 */
	public static double getDouble(Scanner scnr, String prompt, double min, double max) {
		boolean isValid = false;
		double number;
		do {
			number = getDouble(scnr, prompt);

			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}
	/**get date
	 * 
	 */
	public static LocalDate getDate(Scanner scnr) {

		boolean exit = false;

		do {

			try {

				System.out.println("Enter Due Date again:");

				DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yy");
				String userDate = scnr.nextLine();
				LocalDate validDate = LocalDate.parse(userDate, format);
				if (validDate.compareTo(LocalDate.now()) >= 0) {
					return validDate;
				} else {

					throw new IllegalArgumentException("Date must be after today.\nEnter date:");
				}
			} catch (DateTimeParseException dtpe) {
				System.out.println("Please enter a valid date (M/D/YYYY).");
			} catch (IllegalArgumentException iae) {
				// Catches thrown exception 
				System.out.println(iae.getMessage());
			}

		} while (!exit);

		return null;

	}
	/**
	 * Get a string of input from the user that must match the given regex.
	 */
	public static String getStringMatchingRegex(Scanner scnr, String prompt, String regex) {
		boolean isValid = false;
		String input;
		do {
			input = getString(scnr, prompt);

			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.out.println("Input must match the appropriate format.");
				isValid = false;
			}

		} while (!isValid);
		return input;
	}
	
	/*
	 * 
	 * get a boolean value from the user
	 * 
	 */

		public static String yesOrNo(Scanner scnr) {
			boolean loop = false;
			do {

				String userInput = scnr.nextLine();

				if (userInput.toUpperCase().startsWith("Y")) {
					loop =true;
					return "y";

				} else if (userInput.toUpperCase().startsWith("N")) {
					loop = true;
					return "n";

				} else {
					loop = false;
					System.out.println("Please enter Y or N.");

				}

			} while (true);

		}
	

	
}