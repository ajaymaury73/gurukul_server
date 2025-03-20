package com.ajay.gurukulX.adminRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajay.gurukulX.adminDomain.College;

public interface CollegeRepository extends MongoRepository<College,String> {

	Optional<College> findByCollegeTenantId(String collegeTenantId);

	  

}
