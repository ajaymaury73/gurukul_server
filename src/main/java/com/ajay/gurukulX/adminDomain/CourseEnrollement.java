package com.ajay.gurukulX.adminDomain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "gurukul_course_enrollement")
public class CourseEnrollement {
	@Id
	private String id;
	private String academicYear;
	private String degreeType;
	private String degreeName;
	private String deptId;
	private String departmentName;
	private String termNumber;
	private String termName;
	private List<Course> courses;
	private String collegeTenantId;
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
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
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
