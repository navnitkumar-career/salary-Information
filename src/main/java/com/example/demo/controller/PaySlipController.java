package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.Employee;
import com.example.demo.model.PaySlip;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.PaySlipService;

@Controller
@RequestMapping("/payslip")
public class PaySlipController {

	@Autowired
	PaySlipService paySlipService;

	@Autowired
	EmployeeRepository employeeRepository;;

	@GetMapping()
	public RedirectView dfl() {
		return new RedirectView("/payslip/view");
	}

	@GetMapping("/view")
	public ModelAndView paySlip() {
		ModelAndView modelAndView = new ModelAndView();
		List<Employee> list = employeeRepository.findByIsActive();
		modelAndView.addObject("employeeList", list);
		modelAndView.setViewName("payslip-view");
		return modelAndView;
	}

	@RequestMapping(value = "/generatepdf", method = RequestMethod.POST)
	public @ResponseBody RedirectView createPDF(@RequestBody PaySlip paySlip) {
		paySlipService.createPDF(paySlip);
		return new RedirectView("/payslip");
	}

	@RequestMapping(value = "/downloadpdf", method = RequestMethod.POST)
	@ResponseBody
	public void downloadPDF(@RequestBody PaySlip paySlip) throws IOException {
		System.out.println("download pdf");
		paySlipService.downloadPdf(paySlip);
	}

//	@RequestMapping(value = "/downloadpdf1", method = RequestMethod.POST)
//	public  ResponseEntity<Resource> downloadPDF(@RequestParam("eId") long eId ,@RequestParam("monthOrYear") String monthOrYear) throws IOException {
//		return paySlipService.downloadPdf1(eId,monthOrYear);
//	}
}
