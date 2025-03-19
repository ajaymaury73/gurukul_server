package com.ajay.gurukulX.adminService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajay.gurukulX.ExaminationDomain.AcademicCalendar;
import com.ajay.gurukulX.adminException.AdminException;
import com.ajay.gurukulX.adminRepository.AcademicRepository;
import com.ajay.gurukulX.adminRepository.AdminRepository;
import com.ajay.gurukulX.adminRepository.CollegeRepository;
import com.ajay.gurukulX.adminRepository.FacultyRepository;
import com.ajay.gurukulX.userRepositry.UserRepositry;

@Service
public class CollegeAdminServiceImpl implements CollegeAdminService{

	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private CollegeRepository collegeRepo;
	
	@Autowired
	private UserRepositry userRepo;
	
	@Autowired
	private FacultyRepository facultyRepo;
	
	@Autowired
	private AcademicRepository academicCalendarRepo;

	@Override
	public String createAcademicCalender(AcademicCalendar academicCalendar) {
	    try {
	        Optional<AcademicCalendar> existingCalendar = academicCalendarRepo.findByAcademicYearAndDegree_DegreeName(
	                academicCalendar.getAcademicYear(), academicCalendar.getDegree().getDegreeName());

	        if (existingCalendar.isPresent()) {
	            return "Academic calendar is already present for this Degree Name and Academic Year. Please update the term number if needed.";
	        }

	        academicCalendarRepo.save(academicCalendar);
	        return "Academic calendar saved successfully!";
	    } catch (Exception e) {
	        return "Error saving academic calendar: " + e.getMessage();
	    }
	}

	@Override
	public List<AcademicCalendar> getAllAcademicCalendar() {
		try {
			List<AcademicCalendar> calendars = academicCalendarRepo.findAll();
			if (calendars == null || calendars.isEmpty()) {
				throw new AdminException("Navbars not found");
			}
			return calendars;
		} catch (Exception e) {
			throw new AdminException("Getting error while fetching navbars" + e.getMessage(), e);

		}
	}

	@Override
	 public String updateAcademicCalendar(String id, AcademicCalendar updatedCalendar) {
	        try {
	            Optional<AcademicCalendar> existingCalendarOpt = academicCalendarRepo.findById(id);

	            if (existingCalendarOpt.isPresent()) {
	                AcademicCalendar existingCalendar = existingCalendarOpt.get();

	                // Update all fields
	                existingCalendar.setAcademicYear(updatedCalendar.getAcademicYear());
	                existingCalendar.setDegree(updatedCalendar.getDegree());

	                // Save the updated entity
	                academicCalendarRepo.save(existingCalendar);
	                return "Academic Calendar updated successfully!";
	            } else {
	                throw new RuntimeException("Academic Calendar with ID " + id + " not found.");
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to update Academic Calendar: " + e.getMessage());
	        }
	    }
       @Override
	    public String deleteAcademicCalendar(String id) {
	        try {
	            if (academicCalendarRepo.existsById(id)) {
	            	academicCalendarRepo.deleteById(id);
	                return "Academic Calendar deleted successfully!";
	            } else {
	                throw new RuntimeException("Academic Calendar with ID " + id + " not found.");
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to delete Academic Calendar: " + e.getMessage());
	        }
	    }
	
	
	
}
