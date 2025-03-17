package com.ajay.gurukulX.userRepositry;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajay.gurukulX.userDomin.User;

public interface UserRepositry extends MongoRepository<User, String>{

}
