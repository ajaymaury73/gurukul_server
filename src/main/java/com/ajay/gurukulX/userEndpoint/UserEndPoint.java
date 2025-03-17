package com.ajay.gurukulX.userEndpoint;

import java.util.List;

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
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		try {
			String message = userService.saveUser(user);
			return ResponseEntity.ok(message);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}
	
	@PostMapping("/update-user")
	public ResponseEntity<String> UpdateUser(@RequestParam("id") String id,@RequestBody User user) {
		try {
			String message = userService.UpdateUser(id,user);
			return ResponseEntity.ok(message);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
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
	

}
