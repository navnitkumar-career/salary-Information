package com.example.demo.model;

public class PaySlip {

	long eId;
	String monthOrYear;

	public long geteId() {
		return eId;
	}

	public void seteId(long eId) {
		this.eId = eId;
	}

	public String getMonthOrYear() {
		return monthOrYear;
	}

	public void setMonthOrYear(String monthOrYear) {
		this.monthOrYear = monthOrYear;
	}

	@Override
	public String toString() {
		return "PaySlip [eId=" + eId + ", monthOrYear=" + monthOrYear + "]";
	}

}
