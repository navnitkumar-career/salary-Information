package com.example.demo.serviceimpl;

import org.springframework.http.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.PaySlip;
import com.example.demo.model.Salary;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SalaryRepository;
import com.example.demo.service.PaySlipService;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class PaySlipServiceimpl implements PaySlipService {

	@Autowired
	SalaryRepository salaryRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void createPDF(PaySlip payslip) {
		payslip.geteId();
		try {
			PdfReader reader = new PdfReader("E:\\Rutik\\Salary-Slip.pdf");
			Employee emp = employeeRepository.findById(payslip.geteId()).get();
			Salary salary = salaryRepository.findByeId(payslip.geteId());

			String selectedYear = payslip.getMonthOrYear().substring(0, 4);
			String selectedMonth = payslip.getMonthOrYear().substring(5, 7);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("E:\\Rutik\\salary-pdf\\"
					+ emp.getFirstName() + "_" + selectedMonth + "_" + selectedYear + ".pdf"));

			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

			for (int i = 1; i <= reader.getNumberOfPages(); i++) {

				PdfContentByte over = stamper.getOverContent(i);

				over.beginText();
				over.setFontAndSize(bf, 13);
//month
				over.setTextMatrix(370, 649);
				over.showText(selectedMonth + "/" + selectedYear);
//Name of the Employee 
				over.setFontAndSize(bf, 9);
				over.setTextMatrix(238, 615);
				over.showText(emp.getFirstName() + "  " + emp.getLastName());
//Employee ID 
				over.setTextMatrix(238, 599);
				over.showText("202201");
//Designation
				over.setTextMatrix(238, 581);
				over.showText("202201");
//Department
				over.setTextMatrix(236, 565);
				over.showText("Development");

// DOJ
				SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
				String formattedDate = sf.format(emp.getDoj());
				over.setTextMatrix(236, 548);
				over.showText(formattedDate);
//UAN
				over.setTextMatrix(443, 615);
				over.showText("Rutik Thummar ");
//PF No
				over.setTextMatrix(443, 599);
				over.showText("202201");
//ESI No
				over.setTextMatrix(443, 581);
				over.showText("202201");
//Bank Name
				over.setTextMatrix(443, 565);
				over.showText("State Bank Of India");
//Bank A/C No
				over.setTextMatrix(443, 548);
				over.showText("902311565613");
//Total Working Days 
				over.setTextMatrix(238, 516);
				over.showText("31");
//LOP days
				over.setTextMatrix(238, 500);
				over.showText("32");
//Paid Days
				over.setTextMatrix(443, 516);
				over.showText("30");
//Leaves Taken
				over.setTextMatrix(443, 500);
				over.showText("01");
//Basic Wage
				over.setTextMatrix(238, 450);
				over.showText("305");
//HRA
				over.setTextMatrix(238, 433);
				over.showText("306");
//Conveyance Allowances
				over.setTextMatrix(238, 417);
				over.showText("307");
//Medical Allowances
				over.setTextMatrix(238, 400);
				over.showText("308");
//Other Allowances
				over.setTextMatrix(238, 385);
				over.showText("309");
//Total Earnings 
				if (salary == null) {
					over.setTextMatrix(238, 368);
					over.showText(" - ");
				} else {
					String str = String.valueOf(salary.getBasicSalary());
					over.setTextMatrix(238, 368);
					over.showText(str);
				}

//				over.setTextMatrix(238, 368);
//				over.showText(str);
//EPF
				over.setTextMatrix(443, 450);
				over.showText("3010");
//Professional Tax
				over.setTextMatrix(443, 433);
				over.showText("3011");
//TDS
				over.setTextMatrix(443, 417);
				over.showText("3012");
//Loan Recovery
				over.setTextMatrix(443, 400);
				over.showText("3013");
//Total Deductions
				over.setFontAndSize(bf, 10);
				over.setTextMatrix(443, 368);
				over.showText("100");
//Net Salary
				over.setFontAndSize(bf, 11);
				over.setTextMatrix(443, 351);
				over.showText("10000/-");

				over.endText();
			}
			stamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResponseEntity<Resource> downloadPdf(PaySlip payslip) throws IOException {
		Employee emp = employeeRepository.findById(payslip.geteId()).get();
		String selectedYear = payslip.getMonthOrYear().substring(0, 4);
		String selectedMonth = payslip.getMonthOrYear().substring(5, 7);
		System.out.println("employee=>>>  " + emp);
		System.out.println("selectedYear=>>> " + selectedYear);
		System.out.println("selectedMonth =>>> " + selectedMonth);

		String serverPath = "E:\\Rutik\\salary-pdf\\" + emp.getFirstName() + "_" + selectedMonth + "_" + selectedYear
				+ ".pdf";
		System.out.println("serverpath=>>>  " + serverPath);

		File file = new File(serverPath);
		if (file.exists()) {
			System.out.println("file exists");
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=" + emp.getFirstName() + "_" + selectedMonth + "_" + selectedYear + ".pdf");
			header.add("Cache-Control", "no-cache, no-store, must-revalidate");
			header.add("Pragma", "no-cache");
			header.add("Expires", "0");
			Path path = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

			return ResponseEntity.ok().headers(header).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
		} else {
			return null;
		}
	}
//	@Override
//	public ResponseEntity<Resource> downloadPdf1(long eId,String monthOrYear) throws IOException {
//		Employee emp = employeeRepository.findById(eId).get();
//		String selectedYear = monthOrYear.substring(0, 4);
//		String selectedMonth = monthOrYear.substring(5, 7);
//		System.out.println("employee=>>>  " + emp);
//		System.out.println("selectedYear=>>> " + selectedYear);
//		System.out.println("selectedMonth =>>> " + selectedMonth);
//		System.out.println();
//
//		String serverPath = "E:\\Rutik\\salary-pdf\\" + emp.getFirstName() + "_" + selectedMonth + "_" + selectedYear
//				+ ".pdf";
//		System.out.println("serverpath=>>>  " + serverPath);
//
//		File file = new File(serverPath);
//		if (file.exists()) {
//			System.out.println("rutik123");
//			HttpHeaders header = new HttpHeaders();
//			header.add(HttpHeaders.CONTENT_DISPOSITION,
//					"attachment; filename=" + emp.getFirstName() + "_" + selectedMonth + "_" + selectedYear + ".pdf");
//			header.add("Cache-Control", "no-cache, no-store, must-revalidate");
//			header.add("Pragma", "no-cache");
//			header.add("Expires", "0");
//			Path path = Paths.get(file.getAbsolutePath());
//			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//			return ResponseEntity.ok().headers(header).contentLength(file.length())
//					.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
//		} else {
//
//			return null;
//		}
//	}

}
