package com.ajay.gurukulX.adminRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajay.gurukulX.adminDomain.College;

public interface CollegeRepository extends MongoRepository<College,String> {

}
