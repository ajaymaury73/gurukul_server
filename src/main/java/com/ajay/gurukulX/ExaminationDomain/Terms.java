package com.ajay.gurukulX.ExaminationDomain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Terms {

	@Id
	private String id;
	private String termName;
	private String termNumber;
	private LocalDate startDate;
	private LocalDate endDate;
	private List<Course>courses;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getTermNumber() {
		return termNumber;
	}

	public void setTermNumber(String termNumber) {
		this.termNumber = termNumber;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}
	
	

	
}
