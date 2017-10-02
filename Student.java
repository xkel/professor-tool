/*
 * Name: Xavier Kelly
 * Date: Thursday, September 27, 2017
 * Course: Data Structures
 * Program: Student.java
 * 
 */


package homework;

public class Student {
	
	//Attributes: for first name, a last name and a GPA for every given student
	String firstName;
	String lastName;
	float gpa;
	
	/**
	 * 
	 * @param fN argument for a first Name
	 * @param lN argument for a last name
	 * @param g argument for a GPA of float
	 * 
	 * Student class constructor.
	 */
	Student(String fN, String lN, float g){
		firstName = fN; lastName = lN; gpa = g;
	}
	
	//The following methods serve as getters and setters for the Student class.
	
	public String getFirstName(){
		return firstName;
	}
	
	void setFirstName(String x){
		firstName = x;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	void setLastName(String x){
		lastName = x;
	}
	
	public float getGPA(){
		return gpa;
	}
	
	void setGPA(float x){
		gpa = x;
	}
	
	/**
	 * toString() Function
	 *@return String; in form of <lastName>, <firstName> :: <gpa>
	 *Allows a string interpretation of all the attributes of the student class.
	 */
	
	public String toString(){
		
		String x = (getLastName()+", "+getFirstName()+" :: "+""+getGPA());
		return x;
	}
}

