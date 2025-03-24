package com.ajay.gurukulX.adminService;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.data.mongodb.core.query.Term;
import org.springframework.web.multipart.MultipartFile;

import com.ajay.gurukulX.ExaminationDomain.AcademicCalendar;
import com.ajay.gurukulX.ExaminationDomain.Degree;
import com.ajay.gurukulX.ExaminationDomain.Department;
import com.ajay.gurukulX.ExaminationDomain.Terms;
import com.ajay.gurukulX.adminDomain.CourseEnrollement;

public interface CollegeAdminService {

	String createAcademicCalender(AcademicCalendar academicCalendar);

	List<AcademicCalendar> getAllAcademicCalendar();

	String updateAcademicCalendar(String id, AcademicCalendar updatedCalendar);

	String deleteAcademicCalendar(String id);

	ByteArrayOutputStream generateTemplate(String academicYear, String degree, String semester, String college, String degreeType, String department);

	String saveOrUpdate(Degree degree);

	List<Degree> getAllDegree();

	String deleteDegree(String degreeId, String collegeTenantId);

	List<Department> getDepartments(String collegeTenantId, String courseType, String degreeName);

	List<String> getAcademicYearBasedOnCollege(String collegeTenantId);

	List<String> getDegreeType(String collegeTenantId, String academicYear);

	List<String> getDegrees(String collegeTenantId, String academicYear, String degreeType);

	List<String> getDepartments(String collegeTenantId, String academicYear, String degreeType, String degreeName);

	List<Terms> getTerms(String collegeTenantId, String academicYear, String degreeType, String degreeName,
			String deptId);

	String uploadCourseTemplate(MultipartFile file);

	List<CourseEnrollement> getAllCourses();







}
