package com.ajay.gurukulX.adminRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajay.gurukulX.adminDomain.CourseEnrollement;

public interface CourseEnrollementRepository extends MongoRepository<CourseEnrollement, String> {

	boolean existsByAcademicYearAndDegreeTypeAndDegreeNameAndDeptIdAndTermName(String academicYear, String degreeType,
			String degreeName, String deptId, String termName);

}
