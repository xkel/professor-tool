/*
 * Name: Xavier Kelly
 * Date: Thursday, September 27, 2017
 * Course: Data Structures
 * Program: Communicator.java
 * 
 * Program Desc: Contains the collective of the program, uses student objects to populate a linkedList studentList.
 * Allows for loading of a student list into the system, displaying of students by GPA, printing
 * of all files loaded, and removal of student by student last name.
 * 
 */


package homework;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.Scanner;
import java.io.File;
import java.util.ListIterator;

public class Communicator {
	
	/**
	 * Static member logHistory is a set of text files. Stores
	 * string names of the files scanned so far.
	 */
	
	static Set<String> logHistory;
	
	/**
	 * Static member studentList is a linked list of Student objects.
	 * Stores objects that are loaded into the system.
	 */

	static LinkedList<Student> studentList;
	
	/**
	 * Acts as constructor, initializes static members.
	 * Calls subroutine displayMenu()
	 * @throws Exception
	 */
	
	public static void start() throws Exception{
		logHistory = new TreeSet<String>();
		studentList = new LinkedList<Student>();
		//The tool gives a Welcome message
		System.out.println("Hello professor. How can I help you?");
		displayMainMenu();
	}
	
	/**
	 * Displays main menu for tool. Accepts user input, and validates it.
	 * User input targets subroutine calls, 4 different options based on user
	 * input of 1, 2, 3, or 4.
	 * @throws Exception
	 */
	
	public static void displayMainMenu() throws Exception{
		String input;
		Scanner in = new Scanner(System.in);
		do {
			System.out.printf("1: Load student list.\n");
			System.out.printf("2: Display students by gpa.\n");
			System.out.printf("3: Print load log.\n");
			System.out.printf("4: Exit.\n");
			System.out.printf("5: Remove students by last name.\n");
			System.out.printf(">> ");
			input = in.next();
		} while (invalidInput(input));
	
		switch (input) {
		case "1":
			optionOne();
			break;
		case "2":
			optionTwo();
			break;
		case "3":
			printLog();
			break;
		case "4":
			exit();
			break;	
		case "5":
			optionFive();
			break;
		}
	}
	
	/*
	 * 1. The first option should prompt the user to enter a file name. 
	 * If the file is found, the students will be loaded into the system, i.e. stored inside studentList. 
	 */
	
	//Method or option one, allows the file to be read and 'loaded' into the LinkedList studentList as separate student objects.
	public static void optionOne() throws Exception{
	
		//Instantiates the student and scanner objects
		Student student;
		Scanner in = new Scanner(System.in);
		
		System.out.println("Type in the file name of the class list. Press B to return to the main menu");
		File inputFile;
		String option;
		Scanner scnr;
		//IF statement, gives the user an option to return to the main menu or enter a file name
		
			do{
				System.out.print(">> ");
				option = in.next();
				inputFile = new File(option);
				if(logHistory.contains(option)){
					System.out.println("File already loaded! Try again!");
					break;
				}
				if(!inputFile.exists() && !option.equalsIgnoreCase("B")){
					System.out.println("File not found! Please try again");
				}
			}while(!option.equalsIgnoreCase("B") && !inputFile.exists());
			
			if(!option.equalsIgnoreCase("B")){
				if(logHistory.contains(option)){
					
				}else{
					System.out.println("Adding students to LinkedList");
					scnr = new Scanner(inputFile);
					while(scnr.hasNextLine()){
				
						String firstName = scnr.next();
						String lastName = scnr.next(); 
						Float GPA = scnr.nextFloat();
					
						student = new Student(firstName, lastName, GPA);
						studentList.add(student);
						scnr.nextLine();
				}
				System.out.println("List loaded!\n");
				logHistory.add(option);
				}
			
			}
			System.out.println("");
			displayMainMenu();
	}
	
	//Option Two, allows a user to input a GPA and shows the list of students with the given GPA or higher
	public static void optionTwo() throws Exception{
		Scanner scnr;
		float studentGPA = 0;
		float gpa;
		
		int count = 0;
		do{
			if(count>0){
				System.out.println("Enter a valid input!");
			}
			System.out.println("Type in a GPA between 0.0 and 4.0 for which you would like to display your students");
			scnr = new Scanner(System.in);
			gpa = scnr.nextFloat();
			count++;
		}while(gpa<=0.0 || gpa>=4.0);
		
		//Display students by the given GPA and above using the given input
		System.out.println("Here are the following list of students with a GPA of "+gpa+" or above!\n");
		
		for(int i=0; i<studentList.size();i++){
			Student student = studentList.get(i);
			studentGPA = student.getGPA();
			
			if(gpa >= 0.0 && gpa <= 4.0){
				if(studentGPA >= gpa){
					System.out.println(""+student.toString());
				}
			}else{
				System.out.println("Invalid GPA given");
				break;
			}
		}
		System.out.println("");
		displayMainMenu();
		scnr.close();
	}
	
	//Method that is used to determine if an input is valid or not.
	public static boolean invalidInput(String input){
		if(input.equals(1) || input.equals(2) || input.equals(3) || input.equals(4) || input.equals(5)){
			return true;
		}
		return false;
	}
	
	//This method allows for the log history to be printed, in other words, it determines if the files have been loaded or not and which.
	public static void printLog() throws Exception{
		
		if(logHistory.isEmpty()){
			System.out.println("Your log list is empty.");
			
		}else{
			System.out.println("Files loaded so far:");
			System.out.println(logHistory);
		}
	
		System.out.println("");
		displayMainMenu();
	}
	
	//Exits the program.
	public static void exit(){
		System.out.println("Goodbye!");
	}

	
//Allows for last names to be given so that they are removed from the LinkedList.
	public static void optionFive() throws Exception{

		ListIterator<Student> iterator = studentList.listIterator();
		String studentName = null;
		String confirmation = null;
		String strOrFile = null;
		int count = 0;
		
		//Prompt for delete and confirmation
		//This do while loop confirms if the user wants to delete a given name
		do{
			if(count>0){
				System.out.println("Re-enter last names, then type yes to confirm.");
			}
			System.out.println("Enter last name(s) that you'd like to remove student(s)\n");
			
			Scanner in = new Scanner(System.in);
			strOrFile = in.nextLine();
			System.out.println("Are you sure you want to delete: "+strOrFile);
			confirmation = in.nextLine();
			count++;
		}while(!confirmation.equalsIgnoreCase("yes"));
		
		
		//The rest of this code handles the removing
		Scanner scan = new Scanner(strOrFile).useDelimiter(",| ");
		
			while (scan.hasNext()){
				studentName = scan.next();
				studentList.removeIf(isLastNameEqual(studentName));
			
			}
			displayMainMenu();
			
	}
	//Predicate method to use the method removeIf() on the studentList
	public static Predicate<Student> isLastNameEqual(String lastName) {
	    return p -> p.getLastName().equals(lastName);
	}
	
}
