package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface SalaryService {

	Boolean addSave(long basicSalary, String empName);

	Map<String, Object> edit(long eId);

	void editSave(long sId,long basicSalary);

	List<Map<String, Object>> SalaryList();

	void deleteById(long id);
}
