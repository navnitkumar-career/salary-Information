package com.example.demo.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.PaySlip;

public interface PaySlipService {

	void createPDF(PaySlip paySlip);
	
	ResponseEntity<Resource> downloadPdf(PaySlip paySlip) throws IOException;

//	ResponseEntity<Resource> downloadPdf1(long eId,String monthOrYear) throws IOException;

}
