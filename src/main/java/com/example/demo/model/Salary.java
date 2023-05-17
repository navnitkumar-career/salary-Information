package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long sId;

	long eId;

	long basicSalary;

	public long getsId() {
		return sId;
	}

	public void setsId(long sId) {
		this.sId = sId;
	}


	public long geteId() {
		return eId;
	}

	public void seteId(long eId) {
		this.eId = eId;
	}

	public long getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(long basicSalary) {
		this.basicSalary = basicSalary;
	}

	@Override
	public String toString() {
		return "Salary [sId=" + sId + ", eId=" + eId + ", basicSalary=" + basicSalary + "]";
	}

	

}
