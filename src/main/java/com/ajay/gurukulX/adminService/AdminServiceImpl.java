package com.ajay.gurukulX.adminService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ajay.gurukulX.ExaminationDomain.Degree;
import com.ajay.gurukulX.adminDomain.College;
import com.ajay.gurukulX.adminDomain.Faculty;
import com.ajay.gurukulX.adminDomain.RoleNavbar;
import com.ajay.gurukulX.adminException.AdminException;
import com.ajay.gurukulX.adminRepository.AdminRepository;
import com.ajay.gurukulX.adminRepository.CollegeRepository;
import com.ajay.gurukulX.adminRepository.FacultyRepository;
import com.ajay.gurukulX.userRepositry.UserRepositry;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private CollegeRepository collegeRepo;
	
	@Autowired
	private UserRepositry userRepo;
	
	@Autowired
	private FacultyRepository facultyRepo;
	

	@Override
	public String saveNavbar(RoleNavbar roleNavbar) {
		try {
			adminRepo.save(roleNavbar);
			return "Category saved successfully!";
		} catch (Exception e) {
			return "Error saving category: " + e.getMessage();
		}

	}

	@Override
	public List<RoleNavbar> getAllNavbars() {
		try {
			List<RoleNavbar> navbars = adminRepo.findAll();
			if (navbars == null || navbars.isEmpty()) {
				throw new AdminException("Navbars not found");
			}
			return navbars;
		} catch (Exception e) {
			throw new AdminException("Getting error while fetching navbars" + e.getMessage(), e);

		}
	}

	@Override
	public String saveCollege(College college) {
		try {
			 String tenantName = generateTenantName(college.getCollegeName());
		     college.setCollegeTenantId(tenantName);
			collegeRepo.save(college);
			return "College saved successfully!";
		} catch (Exception e) {
			return "Error saving category: " + e.getMessage();
		}
	}
	
	private String generateTenantName(String collegeName) {
	    if (collegeName == null || collegeName.trim().isEmpty()) {
	        throw new IllegalArgumentException("College name cannot be null or empty");
	    }

	    String[] words = collegeName.split("\\s+");
	    StringBuilder abbreviation = new StringBuilder("gurkul_");

	    for (String word : words) {
	        abbreviation.append(word.toLowerCase().charAt(0));
	    }

	    return abbreviation.toString();
	}

	@Override
	public List<College> getAllCollege() {
		try {
			List<College> colleges = collegeRepo.findAll();

			if (colleges == null || colleges.isEmpty()) {
				throw new AdminException("College not found");

			}
			return colleges;
		} catch (Exception e) {
			throw new AdminException("Getting error while fetching college details" + e.getMessage(), e);
		}

	}

	@Override
	public String deleteCollege(String id) {
		Optional<College> college = collegeRepo.findById(id);
		if (college.isPresent()) {
			collegeRepo.deleteById(id);
			return "College deleted successfully!";
		} else {
			throw new AdminException("College not found with ID: " + id);
		}
	}

	@Override
	public String updateCollege(String id, College updatedCollege) {
		Optional<College> existingCollege = collegeRepo.findById(id);
		if (existingCollege.isPresent()) {
			College college = existingCollege.get();

			college.setCollegeName(updatedCollege.getCollegeName());
			college.setCollegeLogo(updatedCollege.getCollegeLogo());
			college.setAddress(updatedCollege.getAddress());
			college.setPincode(updatedCollege.getPincode());
			college.setMobileNumber(updatedCollege.getMobileNumber());
			college.setPrincipalName(updatedCollege.getPrincipalName());
			college.setTagline(updatedCollege.getTagline());
			college.setAccreditation(updatedCollege.getAccreditation());
			college.setAccreditationStatus(updatedCollege.getAccreditationStatus());
			college.setEmail(updatedCollege.getEmail());
//			college.setCollegeTenantId(updatedCollege.getCollegeTenantId());
			college.setUniversityName(updatedCollege.getUniversityName());
			college.setEstablishedYear(updatedCollege.getEstablishedYear());
			college.setTotalCourses(updatedCollege.getTotalCourses());
			college.setTotalFaculties(updatedCollege.getTotalFaculties());
			college.setWebsiteUrl(updatedCollege.getWebsiteUrl());
			college.setCollegeType(updatedCollege.getCollegeType());
			college.setCollegeRanking(updatedCollege.getCollegeRanking());
			college.setCampusSize(updatedCollege.getCampusSize());
			college.setHostelAvailable(updatedCollege.getHostelAvailable());
			college.setTotalStudents(updatedCollege.getTotalStudents());
			
		

			collegeRepo.save(college);
			return "College updated successfully!";
		} else {
			throw new RuntimeException("College not found with ID: " + id);
		}
	}

	

}
