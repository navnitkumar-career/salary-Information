package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long bId;

	long eId;
	String bankName;
	String ifsc;
	long accountNumber;
	String branchName;

	public long getbId() {
		return bId;
	}

	public void setbId(long bId) {
		this.bId = bId;
	}

	public long geteId() {
		return eId;
	}

	public void seteId(long eId) {
		this.eId = eId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Override
	public String toString() {
		return "BankDetails [bId=" + bId + ", eId=" + eId + ", bankName=" + bankName + ", ifsc=" + ifsc
				+ ", accountNumber=" + accountNumber + ", branchName=" + branchName + "]";
	}

}
