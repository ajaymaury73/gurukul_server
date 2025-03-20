package com.ajay.gurukulX.adminService;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.ajay.gurukulX.ExaminationDomain.AcademicCalendar;
import com.ajay.gurukulX.ExaminationDomain.Degree;

public interface CollegeAdminService {

	String createAcademicCalender(AcademicCalendar academicCalendar);

	List<AcademicCalendar> getAllAcademicCalendar();

	String updateAcademicCalendar(String id, AcademicCalendar updatedCalendar);

	String deleteAcademicCalendar(String id);

	ByteArrayOutputStream generateTemplate(String academicYear, String degree, String semester);

	String saveOrUpdate(Degree degree);

	List<Degree> getAllDegree();

	String deleteDegree(String degreeId, String collegeTenantId);

}
