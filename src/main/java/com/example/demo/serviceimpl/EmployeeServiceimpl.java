package com.example.demo.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceimpl  implements EmployeeService{
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public void addSave(String firstName, String lastName, String email, long mobileNumber, String address,
			Date doj, Date dob, String active) {
		Employee emp = new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setMobileNumber(mobileNumber);
		emp.setEmail(email);
		emp.setAddress(address);
		emp.setDoj(doj);
		emp.setDob(dob);
		emp.setActive(active);
		employeeRepository.save(emp);
	}

	@Override
	public void editSave( long eId,String firstName, String lastName, String email, long mobileNumber, String address,
			Date doj, Date dob, String active) {

		Employee emp = employeeRepository.findById(eId).get();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setMobileNumber(mobileNumber);
		emp.setEmail(email);
		emp.setAddress(address);
		emp.setDoj(doj);
		emp.setDob(dob);
		emp.setActive(active);
		employeeRepository.save(emp);

	}

	@Override
	public List<Employee> empList() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(long eId) {
		return employeeRepository.findById(eId).get();
	}

	@Override
	public void deleteById(long id) {	
		 employeeRepository.deleteById(id);		
	}

}
