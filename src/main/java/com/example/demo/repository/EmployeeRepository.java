package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	public Employee findByFirstName(String first_name);
	
	@Query(value = "select * from employee where active='Y'", nativeQuery = true)
	public List<Employee> findByIsActive();
	
}
