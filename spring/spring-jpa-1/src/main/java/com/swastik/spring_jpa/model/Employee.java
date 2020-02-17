package com.swastik.spring_jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long emp_id;
	@Column(name="emp_name")
	String emp_name;
	@Column(name="department")
	String department;
	@Column(name="salary")
	Long salary;
	@Column(name="location")
	String location;

	public Employee() {

	}

	public Employee(long emp_id, String emp_name, String department, long salary, String location) {
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.department = department;
		this.salary = salary;
		this.location = location;
	}

	public long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(long emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
