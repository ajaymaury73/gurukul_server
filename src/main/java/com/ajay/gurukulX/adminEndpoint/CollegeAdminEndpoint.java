package com.ajay.gurukulX.adminEndpoint;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.gurukulX.ExaminationDomain.AcademicCalendar;
import com.ajay.gurukulX.ExaminationDomain.Degree;
import com.ajay.gurukulX.adminException.AdminException;
import com.ajay.gurukulX.adminService.AdminService;
import com.ajay.gurukulX.adminService.CollegeAdminService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/college-admin")
public class CollegeAdminEndpoint {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CollegeAdminService collegeAdminService;

	@PostMapping("/create-academic-calendar")
	public Response createAcademicCalender(@RequestBody AcademicCalendar academicCalendar) {
		try {
			String message = collegeAdminService.createAcademicCalender(academicCalendar);
			return Response.ok(message).build();
		} catch (Exception e) {
			return Response.status(500).entity("Error: " + e.getMessage()).build();
		}
	}

	@GetMapping("/get-academic-calendar")
	public List<AcademicCalendar> getAllAcademicCalendar() {
		return collegeAdminService.getAllAcademicCalendar();
	}

	@PostMapping("/update-academic-calendar")
	public Response updateAcademicCalendar(@RequestParam("id") String id,
			@RequestBody AcademicCalendar updatedCalendar) {
		try {
			String message = collegeAdminService.updateAcademicCalendar(id, updatedCalendar);
			return Response.ok(message).build();
		} catch (RuntimeException e) {
			return Response.status(404).entity("Error: " + e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(500).entity("Internal Server Error: " + e.getMessage()).build();
		}
	}

	@GetMapping("/delete-academic-calendar")
	public Response deleteAcademicCalendar(@RequestParam("id") String id) {
		try {
			String message = collegeAdminService.deleteAcademicCalendar(id);
			return Response.status(Response.Status.CREATED).entity(message).build();
		} catch (RuntimeException e) {
			return ((ResponseBuilder) ResponseEntity.status(404)).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	
	
	@PostMapping("/save-update-degree")
	public Response saveOrUpdate(@RequestBody Degree degree) {
		try {
			String message = collegeAdminService.saveOrUpdate(degree);
			return Response.ok(message).build();
		} catch (Exception e) {
			return Response.status(500).entity("Error: " + e.getMessage()).build();
		}
	}
	
	
	 @GetMapping("/template")
	    public ResponseEntity<byte[]> downloadTemplate(@RequestParam String academicYear,
	                                                   @RequestParam String degreeName,
	                                                   @RequestParam String semester) {
	        try {
	            ByteArrayOutputStream templateStream = collegeAdminService.generateTemplate(academicYear, degreeName, semester);
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	            headers.setContentDispositionFormData("attachment", "Course_Template.xlsx");
	            return new ResponseEntity<>(templateStream.toByteArray(), headers, HttpStatus.OK);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(null);
	        }
	    }
	
	    @GetMapping("/get-all-degree")
		public List<Degree> getAllDegree() {
			return collegeAdminService.getAllDegree();
		}
	    

	    
		@GetMapping("/delete-degree")
		public Response deleteDegree(@RequestParam("id") String degreeId,
				@RequestParam("collegeTenantId") String collegeTenantId) {
			try {
				String message = collegeAdminService.deleteDegree(degreeId, collegeTenantId);
				return Response.status(Response.Status.CREATED).entity(message).build();

			} catch (RuntimeException e) {
				return ((ResponseBuilder) ResponseEntity.status(404)).entity(e.getMessage()).build();
			} catch (Exception e) {
				return Response.status(500).entity(e.getMessage()).build();
			}

		}


	
}


