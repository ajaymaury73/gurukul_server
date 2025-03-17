package com.ajay.gurukulX.adminRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajay.gurukulX.adminDomain.Faculty;
import com.ajay.gurukulX.userDomin.User;

public interface FacultyRepository  extends MongoRepository<Faculty, String>{
	
}



