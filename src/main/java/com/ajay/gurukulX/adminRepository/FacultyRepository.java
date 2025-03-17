package com.ajay.gurukulX.adminRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajay.gurukulX.adminDomain.Faculty;


public interface FacultyRepository  extends MongoRepository<Faculty, String>{
	
}



