package com.ajay.gurukulX.adminService;

import java.util.List;

import com.ajay.gurukulX.ExaminationDomain.AcademicCalendar;

public interface CollegeAdminService {

	String createAcademicCalender(AcademicCalendar academicCalendar);

	List<AcademicCalendar> getAllAcademicCalendar();

	String updateAcademicCalendar(String id, AcademicCalendar updatedCalendar);

	String deleteAcademicCalendar(String id);

}
