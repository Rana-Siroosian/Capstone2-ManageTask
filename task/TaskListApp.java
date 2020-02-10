package task;
import java.time.LocalDate;
/**
 * 
 * @author >>RanaSiroosian<<
 * 
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskListApp {
	
	public static void main (String [] args) {
		Scanner scnr = new Scanner (System.in);

		
		System.out.println("| Welcome to the Task Manager! |\n");
		System.out.println("--------------------------------\n");
		Map<Integer , Task> tasks = new HashMap <Integer , Task>();
		printMenu(scnr, tasks);
				
	}

	public static void printMenu(Scanner scnr ,Map<Integer,Task> tasks) {
		String[] menu = {"List task", "Add task","Delete task",
				"Mark task compelete","Edit Task","Display By Name", "Quit"};
		for (int i=0; i <menu.length; i++) {
			System.out.println((i+1) + ". " + menu[i]);
		}
		System.out.println("--------------------------------");
		int choice = Validator.getInt(scnr, "\nWhat would you like to do? ", 1,7);
		System.out.println("--------------------------------");

		switch(choice) {
		
		case 1 : listTask(tasks );
					break;
		case 2 : addTask(tasks , scnr);
					break;
		
		case 3 : deleteTask(tasks, scnr);
					break;
		
		case 4 : markTaskComplete(tasks, scnr);
					break;
					
		case 5: editTask(tasks,scnr);
					break;
		
		case 6 : displayOne(tasks,scnr);
		break;
		case 7 : 
				System.out.print("Are you sure you want to leave the application? (y/n): ");
				String question = Validator.yesOrNo(scnr);
				if (question.equalsIgnoreCase("y")) {

					System.out.println("\nThank you, have a great day!");
					break;
				}
				if (question.equalsIgnoreCase("n")) {
					System.out.println("--------------------------------");
					printMenu(scnr, tasks);

				}
					
		}

	}	
	
	public static void addTask(Map<Integer, Task> tasks, Scanner scnr ) {
		Task newTask = new Task();

		String name = Validator.getString(scnr, "Team Member Name: ");
		newTask.setName(name);
		String description = Validator.getString(scnr, "Task Description: ");
		newTask.setDescription(description);
		System.out.print("Due Date: ");
		newTask.setDate(scnr);;
		tasks.put(tasks.size()+1, newTask);
		System.out.println("\nTask entered! ");
		System.out.println("--------------------------------");

		printMenu(scnr , tasks);
	}
	
	public static void listTask(Map<Integer,Task> tasks) {
		Scanner scnr = new Scanner (System.in);
		System.out.println();
		if (tasks.size() == 0) {
			System.out.println("\nNo stored tasks at this moment! \n");
		}
		else {
		
				System.out.println(String.format("%-5s%-11s%-15s%-15s%-15s", " # ","NAME","DUE DATE","STATUS","DESCRIPTION"));

				System.out.println("***********************************************************");
				
				for(int i = 1; i <= tasks.size(); ++i) {
					System.out.println(String.format("%-5s", " " + i ) + tasks.get(i));					
					System.out.println();
				}
			}
		System.out.println("--------------------------------");

		printMenu(scnr, tasks);

	}

	public static void deleteTask(Map<Integer,Task> tasks, Scanner scnr) {
		
		int taskDel = Validator.getInt(scnr, "Which task you want to delete? (choose by number) ");
		
		System.out.print("Are you sure you want to delete " + tasks.get(taskDel).getName() + " from the list? (y/n): ");
		String question = Validator.yesOrNo(scnr);
		if (question.equalsIgnoreCase("y")) {
		
			System.out.println(tasks.get(taskDel).getName() + " is removed from the list");
			tasks.replace(taskDel, tasks.get(taskDel+1));
			
			for(int i = 1; i <= tasks.size(); ++i) {

				if(i == (tasks.size())) {
					tasks.remove(i);
					break;			
				}else {
					tasks.replace((taskDel + i), tasks.get(taskDel + i + 1));
				}
			}
		}
		if (question.equalsIgnoreCase("n")) {
			System.out.println("Ok , next time make up your mind before pressing delete.");
		}
		System.out.println("--------------------------------");
		printMenu(scnr, tasks);

	}
	
	public static void markTaskComplete(Map<Integer,Task> tasks, Scanner scnr) {
		
		int num = Validator.getInt(scnr, "Which task would you like to mark as completed? ", 1, tasks.size());
		System.out.print("Are you sure you want to mark " + tasks.get(num).getName() + " as completed? (y/n): ");

		String question = Validator.yesOrNo(scnr);
		if (question.equalsIgnoreCase("y")) {
			Task complete = new Task (tasks.get(num),"Completed");
			tasks.replace(num, tasks.get(num), complete);
			System.out.println();
			System.out.println(tasks.get(num).getName() + " marked as completed.");
		}
		if (question.equalsIgnoreCase("n")) {
			System.out.println("Ok , next time make up your mind before pressing delete.");
		}
		System.out.println("--------------------------------");
		printMenu(scnr, tasks);
	}
	
	public static void editTask(Map<Integer,Task> tasks, Scanner scnr) {
		System.out.println();

		int editTasks = Validator.getInt(scnr, "Which task you want to edit? (choose by number) ");

		if(tasks.size()==0) {
			System.out.println("Sorry there is no tasks entered yet!");

		}
		else {
		System.out.print(tasks.get(editTasks).getName() + "\nAre you sure you want to edit this task? (y/n): " );
		String question = Validator.yesOrNo(scnr);

		if (question.equalsIgnoreCase("y")) {
			System.out.println();
			int answer = Validator.getInt(scnr, "What would you like to change? 1- Name / 2- Task Description / "
					+ "3- Due Date (choose by number): ", 1, 3);
			if (answer == 1) {
				String name = Validator.getString(scnr, "Please enter a new Team Member Name: ");
				tasks.get(editTasks).setName(name);
				System.out.println("Team member name has been changed!");

			}
			if(answer == 2) {
				String description = Validator.getString(scnr, "Please enter a new Task Description: ");
				tasks.get(editTasks).setDescription(description);
				System.out.println("Task description has been changed!");

			}
			if(answer == 3) {
				System.out.print("Due Date: ");
				tasks.get(editTasks).setDate(scnr);
				System.out.println("Due date has been changed!");

			}
		}
		if (question.equalsIgnoreCase("n")) {
			System.out.println("Ok , next time make up your mind before.");
		}
		}
		System.out.println("--------------------------------");
		System.out.println();
		printMenu(scnr, tasks);
	}
	
	public static void displayOne(Map<Integer,Task> tasks, Scanner scnr) {
		
		String name = Validator.getString(scnr, "Which team member you would like to search? (enter the name) ");
		System.out.println();
		System.out.println(String.format("%-5s%-11s%-15s%-15s%-15s", " # ","NAME","DUE DATE","STATUS","DESCRIPTION"));

		System.out.println("***********************************************************");
		for (int i = 1; i<= tasks.size();i++) {
			 
			if(tasks.get(i).getName().equalsIgnoreCase(name)) {
				
				
				
					System.out.println(String.format("%-5s", " " + i ) + tasks.get(i));					
					System.out.println();
				
			}
			
		}
		System.out.println("--------------------------------");
		printMenu(scnr, tasks);
	}
	
}
