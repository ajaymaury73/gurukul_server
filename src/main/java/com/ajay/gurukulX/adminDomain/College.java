package com.ajay.gurukulX.adminDomain;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 */
@Document(collection = "gurukul_college")
public class College {

    @Id
    private String id;
    private String collegeName;
    private String collegeLogo;
    private String address;
    private Integer pincode;
    private Long mobileNumber;
    private String principalName;
    private String tagline;
    private String accreditation;
    private String accreditationStatus;
    private String email;
    private String collegeTenantId;
    private String universityName;
    private Integer establishedYear;
    private Integer totalCourses;
    private Integer totalFaculties;
    private String websiteUrl;
    private String collegeType;
    private String collegeRanking;
    private Double campusSize;
    private Boolean hostelAvailable;
    private Integer totalStudents;
    private List<String> departments;
    private List<ClassOrCourse> classOrCourse;
  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getCollegeLogo() {
		return collegeLogo;
	}
	public void setCollegeLogo(String collegeLogo) {
		this.collegeLogo = collegeLogo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPrincipalName() {
		return principalName;
	}
	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getAccreditation() {
		return accreditation;
	}
	public void setAccreditation(String accreditation) {
		this.accreditation = accreditation;
	}
	public String getAccreditationStatus() {
		return accreditationStatus;
	}
	public void setAccreditationStatus(String accreditationStatus) {
		this.accreditationStatus = accreditationStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCollegeTenantId() {
		return collegeTenantId;
	}
	public void setCollegeTenantId(String collegeTenantId) {
		this.collegeTenantId = collegeTenantId;
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public Integer getEstablishedYear() {
		return establishedYear;
	}
	public void setEstablishedYear(Integer establishedYear) {
		this.establishedYear = establishedYear;
	}
	public Integer getTotalCourses() {
		return totalCourses;
	}
	public void setTotalCourses(Integer totalCourses) {
		this.totalCourses = totalCourses;
	}
	public Integer getTotalFaculties() {
		return totalFaculties;
	}
	public void setTotalFaculties(Integer totalFaculties) {
		this.totalFaculties = totalFaculties;
	}
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	public String getCollegeType() {
		return collegeType;
	}
	public void setCollegeType(String collegeType) {
		this.collegeType = collegeType;
	}
	public String getCollegeRanking() {
		return collegeRanking;
	}
	public void setCollegeRanking(String collegeRanking) {
		this.collegeRanking = collegeRanking;
	}
	public Double getCampusSize() {
		return campusSize;
	}
	public void setCampusSize(Double campusSize) {
		this.campusSize = campusSize;
	}
	public Boolean getHostelAvailable() {
		return hostelAvailable;
	}
	public void setHostelAvailable(Boolean hostelAvailable) {
		this.hostelAvailable = hostelAvailable;
	}
	public Integer getTotalStudents() {
		return totalStudents;
	}
	public void setTotalStudents(Integer totalStudents) {
		this.totalStudents = totalStudents;
	}
	public List<String> getDepartments() {
		return departments;
	}
	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}
	public List<ClassOrCourse> getClassOrCourse() {
		return classOrCourse;
	}
	public void setClassOrCourse(List<ClassOrCourse> classOrCourse) {
		this.classOrCourse = classOrCourse;
	}
	
}

    
    



	
	


