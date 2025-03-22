package com.ajay.gurukulX.adminRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajay.gurukulX.ExaminationDomain.AcademicCalendar;

public interface AcademicRepository extends MongoRepository<AcademicCalendar, String> {

	Optional<AcademicCalendar> findByAcademicYearAndDegreeNameAndDegreeTypeAndDeptId(String academicYear,
			String degreeName, String degreeType, String deptId);

	List<AcademicCalendar> findByCollegeTenantId(String collegeTenantId);


	List<AcademicCalendar> findByCollegeTenantIdAndAcademicYear(String collegeTenantId, String academicYear);

	List<AcademicCalendar> findByCollegeTenantIdAndAcademicYearAndDegreeType(String collegeTenantId,
			String academicYear, String degreeType);

	List<AcademicCalendar> findByCollegeTenantIdAndAcademicYearAndDegreeTypeAndDeptId(String collegeTenantId,
			String academicYear, String degreeType, String deptId);

	

	








}
