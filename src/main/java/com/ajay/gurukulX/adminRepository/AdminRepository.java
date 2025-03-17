package com.ajay.gurukulX.adminRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajay.gurukulX.adminDomain.RoleNavbar;

public interface AdminRepository extends MongoRepository<RoleNavbar,String> {

}
