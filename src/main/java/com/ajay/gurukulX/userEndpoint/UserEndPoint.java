package com.ajay.gurukulX.userEndpoint;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.gurukulX.userDomin.User;
import com.ajay.gurukulX.userService.UserService;

@RestController
@RequestMapping("/user")
public class UserEndPoint {

	@Autowired
	private UserService userService;

	@PostMapping("/add-user")
	public Response saveUser(@RequestBody User user) {
		try {
			String message = userService.saveUser(user);
	    	return Response.status(Response.Status.CREATED).entity(message).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@PostMapping("/update-user")
	public Response UpdateUser(@RequestParam("id") String id,@RequestBody User user) {
		try {
			String message = userService.UpdateUser(id,user);
	    	return Response.status(Response.Status.CREATED).entity(message).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@GetMapping("/get-users")
	private List<User>getAllUser(){
		return userService.getAllUser() ;
		
	}
	
	@GetMapping("/delete-user")
	public ResponseEntity<String> deleteUser(@RequestParam("id") String id) {
		try {
			String message = userService.deleteUser(id);
			return ResponseEntity.ok(message);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}
	
	@GetMapping("/get-tenantId")
	public List<String>getTenantName(){
		return userService.getAllTenant() ;

	}
	
	@GetMapping("get-courses")
	public List<String> getCoursesBasedOnCourseType(
	        @RequestParam String collegeTenantId,
	        @RequestParam String courseType) {  // Change from List<String> to String
	    return userService.getCoursesByType(courseType, collegeTenantId);
	}



	

}
