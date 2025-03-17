package com.ajay.gurukulX.adminEndpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.gurukulX.adminDomain.College;
import com.ajay.gurukulX.adminDomain.Faculty;
import com.ajay.gurukulX.adminDomain.RoleNavbar;
import com.ajay.gurukulX.adminService.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminEndpoint {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add-navbar")
	public ResponseEntity<String> saveNavbar(@RequestBody RoleNavbar roleNavbar) {

		try {
			String message = adminService.saveNavbar(roleNavbar);
			return ResponseEntity.ok(message);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}

	}

	@GetMapping("/get-navbar")
	public List<RoleNavbar> getAllNavbar() {
		return adminService.getAllNavbars();

	}

	@PostMapping("/add-college")
	public ResponseEntity<String> saveColleg(@RequestBody College college) {
		try {
			String message = adminService.saveCollege(college);
			return ResponseEntity.ok(message);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	@GetMapping("/get-college")
	public List<College> getCollege() {
		return adminService.getAllCollege();
	}

	@GetMapping("/delete-college")
	public ResponseEntity<String> deleteCollege(@RequestParam("id") String id) {
		try {
			String message = adminService.deleteCollege(id);
			return ResponseEntity.ok(message);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}
	
	 @PostMapping("/update-college")
	    public ResponseEntity<String> updateCollege(@RequestParam("id") String id, @RequestBody College college) {
	        try {
	            String message = adminService.updateCollege(id, college);
	            return ResponseEntity.ok(message);
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error: " + e.getMessage());
	        }
	    }
	 
	 
	
	 
	
	

}
