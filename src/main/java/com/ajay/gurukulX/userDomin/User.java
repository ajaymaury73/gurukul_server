package com.ajay.gurukulX.userDomin;

import java.time.LocalDate;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Document(collection = "gurukul_users")
public class User {
	@Id
	private String id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password; 
	private String userImageType;
    private String section;
	private String rollNumber;
	private String employeeId;
	private String applicationNumber;
	private String gender;
	private List<YearDetails>yearDetails;
	private String bloodGroup;
	private List<Role>roles;
	private List<UserAddress>userAddressDetails;
	private List<UserQualification>qualificationDetails;
	private String mobileNumber;
	private String fatherName;
	private String fatherMobileNumber;
	private String motherName;
	private LocalDate dob;
	private LocalDate joiningDate;
	private String collegeTenantId;
    
	  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	    public void setPassword(String password) {
	        this.password = passwordEncoder.encode(password);
	    }

	    // Verify password
	    public boolean verifyPassword(String rawPassword) {
	        return passwordEncoder.matches(rawPassword, this.password);
	    }

	    public String getPassword() {
	        return password;
	    }
	
	public String getCollegeTenantId() {
		return collegeTenantId;
	}
	public void setCollegeTenantId(String collegeTenantId) {
		this.collegeTenantId = collegeTenantId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherMobileNumber() {
		return fatherMobileNumber;
	}
	public void setFatherMobileNumber(String fatherMobileNumber) {
		this.fatherMobileNumber = fatherMobileNumber;
	}

	
	
	
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	
	public List<YearDetails> getYearDetails() {
		return yearDetails;
	}
	public void setYearDetails(List<YearDetails> yearDetails) {
		this.yearDetails = yearDetails;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getUserImageType() {
		return userImageType;
	}

	public void setUserImageType(String userImageType) {
		this.userImageType = userImageType;
	}

	public List<UserAddress> getUserAddressDetails() {
		return userAddressDetails;
	}

	public void setUserAddressDetails(List<UserAddress> userAddressDetails) {
		this.userAddressDetails = userAddressDetails;
	}

	public List<UserQualification> getQualificationDetails() {
		return qualificationDetails;
	}

	public void setQualificationDetails(List<UserQualification> qualificationDetails) {
		this.qualificationDetails = qualificationDetails;
	}

	
	
	
	
	
	
	
	
}
