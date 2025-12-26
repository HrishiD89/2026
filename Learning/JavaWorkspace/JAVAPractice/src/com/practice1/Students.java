package com.practice1;

import java.util.ArrayList;
import java.util.Scanner;

class StudentsClass {
	private String name;
	private Double age;
	private String major;

	public StudentsClass(String name, Double age, String major) {
		super();
		this.name = name;
		this.age = age;
		this.major = major;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "StudentsClass [name=" + name + ", age=" + age + ", major=" + major + "]";
	}

}

public class Students {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			ArrayList<StudentsClass> students = new ArrayList<>();
			
			while(true) {
				System.out.println("""
						Enter 1 to add students.
						2 to view students.
						3 to update student info.
						4 to remove student.
						5 to exit.
						""");

						String userChoice = sc.nextLine();
						if(userChoice.equals("1")){
							System.out.println("Enter student name : ");
							String name1 = sc.nextLine();
							System.out.println("Enter student age : ");
							Double age1 = Double.parseDouble(sc.nextLine());
							System.out.println("Enter student major : ");
							String major1 = sc.nextLine();

							students.add(new StudentsClass(name1, age1, major1));
							System.out.println("The studnet info is added ");
							
						}else if(userChoice.equals("2")){
							if(students.isEmpty()) {
								System.out.println("No students!");
							}else {
								students.forEach(s-> System.out.println(s));
							}
						}else if(userChoice.equals("3")){
							
						}else if(userChoice.equals("4")){
							
						}else {
							break;
						}
						
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
