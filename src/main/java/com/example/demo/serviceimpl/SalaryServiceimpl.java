package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.Salary;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SalaryRepository;
import com.example.demo.service.SalaryService;

@Service
public class SalaryServiceimpl implements SalaryService {

	@Autowired
	SalaryRepository salaryRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Boolean addSave(long basicSalary, String empName) {
		Employee emp = employeeRepository.findByFirstName(empName);
		if (emp == null) {
			return false;
		} else {
			Salary salary = new Salary();
			salary.seteId(emp.geteId());
			salary.setBasicSalary(basicSalary);
			salaryRepository.save(salary);
			return true;
		}
	}

	@Override
	public Map<String, Object> edit(long sId) {
		Salary salary = salaryRepository.findById(sId).get();
		Employee emp = employeeRepository.findById(salary.geteId()).get();
		Map<String, Object> map = new HashMap<>();
		map.put("sId", sId);
		map.put("empName", emp.getFirstName() + " " + emp.getLastName());
		map.put("basicSalary", salary.getBasicSalary());
		return map;
	}

	@Override
	public void editSave(long sId, long basicSalary) {
		Salary salary = salaryRepository.findById(sId).get();
		salary.setBasicSalary(basicSalary);
		salaryRepository.save(salary);
	}

	

	@Override
	public void deleteById(long id) {
		salaryRepository.deleteById(id);
	}

	@Override
	public List<Map<String, Object>> SalaryList() {
		List<Salary> salaryList = salaryRepository.findAll();
		List<Map<String, Object>> list = new ArrayList<>();
		for (Salary salary : salaryList) {
			Map<String, Object> map = new HashMap<>();
			Employee emp = employeeRepository.findById(salary.geteId()).get();
			map.put("sId", salary.getsId());
			map.put("empName", emp.getFirstName() + "     " + emp.getLastName());
			map.put("basicSalary", salary.getBasicSalary());
			list.add(map);
		}
		return list;
	}

}
