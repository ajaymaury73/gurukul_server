package com.ajay.gurukulX.ExaminationDomain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ajay.gurukulX.adminService.DegreeDto;

@Document(collection = "gurukul_academic_calendar")
public class AcademicCalendar {
	@Id
	private String id;
	private String academicYear;
	private String degreeName;
	private String degreeType;
	private String deptId;
	private String departmentName;
	private String collegeTenantId;
	
	private List<Terms>terms;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<Terms> getTerms() {
		return terms;
	}
	public void setTerms(List<Terms> terms) {
		this.terms = terms;
	}
	public String getDegreeType() {
		return degreeType;
	}
	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	public String getCollegeTenantId() {
		return collegeTenantId;
	}
	public void setCollegeTenantId(String collegeTenantId) {
		this.collegeTenantId = collegeTenantId;
	}
	
	
	
	

	

	

}
