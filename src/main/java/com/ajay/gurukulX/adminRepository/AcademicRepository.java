package com.ajay.gurukulX.adminRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajay.gurukulX.ExaminationDomain.AcademicCalendar;

public interface AcademicRepository extends MongoRepository<AcademicCalendar, String> {

	Optional<AcademicCalendar> findByAcademicYearAndDegree_DegreeName(String academicYear, String degreeName);

}
