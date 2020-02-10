package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * 
 * @author >>RanaSiroosian<<
 *
 */
public class Task {
	
	protected String name;
	protected LocalDate date;
	protected String description;
	protected String markTaskComplete;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(Scanner scnr) {
		boolean exit = false;
		String dueDate = "";

		do{

			try{

				dueDate = scnr.next(); 

				scnr.nextLine(); 
				DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yy");

				LocalDate setDueDate = LocalDate.parse(dueDate, format);
				// Date should be in the future

				if (setDueDate.compareTo(LocalDate.now()) >= 0) {

					this.date = setDueDate;
					exit = true;
				} else {

					throw new IllegalArgumentException("Make sure the date is valid and It's not in the past!\n"
							+ "Enter a valid date: ");
					
				}

			} catch (DateTimeParseException dtpe) {
				// Catches invalid date format exceptions
				System.out.println("Please enter a valid date (M/D/YY).");

			} catch (IllegalArgumentException e) {

				// Catches thrown exception if date entered has already past

				System.out.println(e.getMessage());

			}

		} while (!exit);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getMarkTaskComplete() {
		return markTaskComplete;
	}
	
	public void setMarkTaskComplete(String markTaskComplete) {
		this.markTaskComplete = markTaskComplete;
	}
	
	public Task() {
		this.markTaskComplete = "not completed";
	}
	
	public Task(Task task , String markTaskComplete) {
		name = task.getName();
		date = task.getDate();
		description = task.getDescription();
		this.markTaskComplete = markTaskComplete;
	}
	
	public Task(LocalDate date, String name, String description) {
		this.date = date;
		this.description = description;
		this.name = name;
		this.markTaskComplete = "Not completed";
		
	}
	
	@Override
	public String toString() {

		return String.format("%-10s %-13s %-19s %s", name, date, markTaskComplete,description  );	}
	
}
	