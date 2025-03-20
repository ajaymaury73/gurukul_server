package com.ajay.gurukulX.ExaminationDomain;

import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * 
 */
public class Degree {
	@Id
	private String id;
	private String degreeName;
	private String degreeType;
	private List<Department>departments;
	private String collegeTenantId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String getCollegeTenantId() {
		return collegeTenantId;
	}

	public void setCollegeTenantId(String collegeTenantId) {
		this.collegeTenantId = collegeTenantId;
	}
   
	
	
	

	

}
