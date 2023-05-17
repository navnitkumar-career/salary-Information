package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.service.SalaryService;

@Controller
@RequestMapping("/salary")
public class SalaryController {

	@Autowired
	SalaryService salaryService;

	@GetMapping()
	public RedirectView dfl() {
		return new RedirectView("/salary/view");
	}

	@GetMapping("/view")
	public ModelAndView view() {
		List<Map<String, Object>> list = salaryService.SalaryList();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("salaryList", list);
		modelAndView.setViewName("salary-view1");
		return modelAndView;
	}

	@GetMapping("/add")
	public ModelAndView Add() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("salary-add");
		return modelAndView;
	}

	@PostMapping("/add-save")
	public RedirectView addsave(@RequestParam("basic_salary") long basicSalary,
			@RequestParam("empName") String empName) {
		Boolean flag = salaryService.addSave(basicSalary, empName);
		if (!flag) {
			return new RedirectView("/salary/add");
		} else {
			return new RedirectView("/salary");
		}

	}

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam("sId") long sId) {
		Map<String, Object> map=salaryService.edit(sId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("salary", map);
		modelAndView.setViewName("salary-edit");
		return modelAndView;
	}

	@PostMapping("/edit-save")
	public RedirectView editSave(@RequestParam("sId") long sId, @RequestParam("basicSalary") long basicSalary) {
		salaryService.editSave(sId, basicSalary);
		return new RedirectView("/salary");
	}

	@GetMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") long id) {
		salaryService.deleteById(id);
		return new RedirectView("/salary");

	}

}
