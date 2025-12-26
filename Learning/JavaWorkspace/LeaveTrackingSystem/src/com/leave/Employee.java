package com.leave;

public class Employee {
	// Properties (attributes)
	private int employeeId;
	private String name;
	private String department;
	private String email;

	// Constructor
	public Employee(int employeeId, String name, String department, String email) {
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.email = email;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}