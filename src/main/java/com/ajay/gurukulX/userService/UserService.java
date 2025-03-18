package com.ajay.gurukulX.userService;

import java.util.List;

import com.ajay.gurukulX.userDomin.User;

public interface UserService {



	String saveUser(User user);

	List<User> getAllUser();

	String UpdateUser(String id, User user);

	String deleteUser(String id);

	List<String> getAllTenant();

	List<String> getCoursesByType(List<String> courseType);



}
