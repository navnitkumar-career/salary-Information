package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {

	void addSave(String firstName, String lastName, String email, long mobileNumber, String address, Date doj, Date dob,
			String active);

	void editSave(long eId, String firstName, String lastName, String email, long mobileNumber, String address,
			Date doj, Date dob, String active);
	
	List<Employee> empList();
	
	Employee findById(long eId);
	
	void deleteById(long id);
	
	

}
