package com.example.demo.controller;

import java.io.FileOutputStream;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


public class SalaryPdfCreateController {

	public static void main(String args[]) {
		try {
			PdfReader reader = new PdfReader("E:\\Rutik\\Salary-Slip.pdf");
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("E:\\Rutik\\Opsense-Salary-Slip.pdf"));

			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

			for (int i = 1; i <= reader.getNumberOfPages(); i++) {

				PdfContentByte over = stamper.getOverContent(i);

				over.beginText();
				over.setFontAndSize(bf, 13);

				over.setTextMatrix(370, 649);
				over.showText("June,2020");

				over.setFontAndSize(bf, 9);
				over.setTextMatrix(238, 615);
				over.showText("Rutik Thummar ");

				over.setTextMatrix(238, 599);
				over.showText("202201");

				over.setTextMatrix(238, 581);
				over.showText("202201");

				over.setTextMatrix(236, 565);
				over.showText("Development");

				over.setTextMatrix(236, 548);
				over.showText("Web Development");

				over.setTextMatrix(443, 615);
				over.showText("Rutik Thummar ");

				over.setTextMatrix(443, 599);
				over.showText("202201");

				over.setTextMatrix(443, 581);
				over.showText("202201");

				over.setTextMatrix(443, 565);
				over.showText("State Bank Of India");

				over.setTextMatrix(443, 548);
				over.showText("902311565613");

				over.setTextMatrix(238, 516);
				over.showText("31");

				over.setTextMatrix(238, 500);
				over.showText("32");

				over.setTextMatrix(443, 516);
				over.showText("30");

				over.setTextMatrix(443, 500);
				over.showText("01");

				over.setTextMatrix(238, 450);
				over.showText("305");

				over.setTextMatrix(238, 433);
				over.showText("306");

				over.setTextMatrix(238, 417);
				over.showText("307");

				over.setTextMatrix(238, 400);
				over.showText("308");

				over.setTextMatrix(238, 385);
				over.showText("309");

				over.setTextMatrix(238, 368);
				over.showText("310");

				over.setTextMatrix(443, 450);
				over.showText("3010");

				over.setTextMatrix(443, 433);
				over.showText("3011");

				over.setTextMatrix(443, 417);
				over.showText("3012");

				over.setTextMatrix(443, 400);
				over.showText("3013");

				over.setFontAndSize(bf, 10);
				over.setTextMatrix(443, 368);
				over.showText("100");

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

}
