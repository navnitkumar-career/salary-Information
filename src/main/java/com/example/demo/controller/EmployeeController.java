package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping()
	public RedirectView dfl() {
		return new RedirectView("/emp/view");
	}

	@GetMapping("/view")
	public ModelAndView view() {
		List<Employee> empList = employeeService.empList();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("empList", empList);
		modelAndView.setViewName("employee-view1");
		return modelAndView;
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employee-add");
		return modelAndView;
	}

	@PostMapping("/add-save")
	public RedirectView addsave(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("mobileNumber") long mobileNumber,
			@RequestParam("address") String address, @RequestParam("doj") Date doj, @RequestParam("dob") Date dob,
			@RequestParam("active") String active) {
		employeeService.addSave(firstName, lastName, email, mobileNumber, address, doj, dob, active);
		return new RedirectView("/emp");

	}

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam("eId") long eId) {
		Employee emp = employeeService.findById(eId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("emp", emp);
		modelAndView.setViewName("employee-edit");
		return modelAndView;
	}

	@PostMapping("/edit-save")
	public RedirectView editsave(@RequestParam("eId") long eId, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("mobileNumber") long mobileNumber, @RequestParam("address") String address,
			@RequestParam("doj") Date doj, @RequestParam("dob") Date dob, @RequestParam("active") String active) {
		employeeService.editSave(eId, firstName, lastName, email, mobileNumber, address, doj, dob, active);
		return new RedirectView("/emp");
	}

	@GetMapping("/delete/{id}")
	@ResponseBody
	public RedirectView delete(@PathVariable("id") long id) {
		try {
			employeeService.deleteById(id);
			return new RedirectView("/emp");
		} catch (Exception e) {
			return new RedirectView("/emp/error");
		}
	}

	@GetMapping("/error")
	public ModelAndView error() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error-page");
		return modelAndView;
	}
}
