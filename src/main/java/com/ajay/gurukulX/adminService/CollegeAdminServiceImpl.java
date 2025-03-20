package com.ajay.gurukulX.adminService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajay.gurukulX.ExaminationDomain.AcademicCalendar;
import com.ajay.gurukulX.ExaminationDomain.Degree;
import com.ajay.gurukulX.adminDomain.College;
import com.ajay.gurukulX.adminException.AdminException;
import com.ajay.gurukulX.adminRepository.AcademicRepository;
import com.ajay.gurukulX.adminRepository.AdminRepository;
import com.ajay.gurukulX.adminRepository.CollegeRepository;
import com.ajay.gurukulX.adminRepository.FacultyRepository;
import com.ajay.gurukulX.userRepositry.UserRepositry;

@Service
public class CollegeAdminServiceImpl implements CollegeAdminService{

	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private CollegeRepository collegeRepo;
	
	@Autowired
	private UserRepositry userRepo;
	
	@Autowired
	private FacultyRepository facultyRepo;
	
	@Autowired
	private AcademicRepository academicCalendarRepo;

	@Override
	public String createAcademicCalender(AcademicCalendar academicCalendar) {
	    try {
	        Optional<AcademicCalendar> existingCalendar = academicCalendarRepo.findByAcademicYearAndDegree_DegreeName(
	                academicCalendar.getAcademicYear(), academicCalendar.getDegree().getDegreeName());

	        if (existingCalendar.isPresent()) {
	            return "Academic calendar is already present for this Degree Name and Academic Year. Please update the term number if needed.";
	        }

	        academicCalendarRepo.save(academicCalendar);
	        return "Academic calendar saved successfully!";
	    } catch (Exception e) {
	        return "Error saving academic calendar: " + e.getMessage();
	    }
	}

	@Override
	public List<AcademicCalendar> getAllAcademicCalendar() {
		try {
			List<AcademicCalendar> calendars = academicCalendarRepo.findAll();
			if (calendars == null || calendars.isEmpty()) {
				throw new AdminException("Navbars not found");
			}
			return calendars;
		} catch (Exception e) {
			throw new AdminException("Getting error while fetching navbars" + e.getMessage(), e);

		}
	}

	@Override
	 public String updateAcademicCalendar(String id, AcademicCalendar updatedCalendar) {
	        try {
	            Optional<AcademicCalendar> existingCalendarOpt = academicCalendarRepo.findById(id);

	            if (existingCalendarOpt.isPresent()) {
	                AcademicCalendar existingCalendar = existingCalendarOpt.get();

	                // Update all fields
	                existingCalendar.setAcademicYear(updatedCalendar.getAcademicYear());
	                existingCalendar.setDegree(updatedCalendar.getDegree());

	                // Save the updated entity
	                academicCalendarRepo.save(existingCalendar);
	                return "Academic Calendar updated successfully!";
	            } else {
	                throw new RuntimeException("Academic Calendar with ID " + id + " not found.");
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to update Academic Calendar: " + e.getMessage());
	        }
	    }
       @Override
	    public String deleteAcademicCalendar(String id) {
	        try {
	            if (academicCalendarRepo.existsById(id)) {
	            	academicCalendarRepo.deleteById(id);
	                return "Academic Calendar deleted successfully!";
	            } else {
	                throw new RuntimeException("Academic Calendar with ID " + id + " not found.");
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to delete Academic Calendar: " + e.getMessage());
	        }
	    }
      
       @Override
       public ByteArrayOutputStream generateTemplate(String academicYear, String degreeName, String semester) {
           try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
               XSSFSheet sheet = workbook.createSheet("Course Details");

               // Create Cell Styles
               XSSFCellStyle titleStyle = workbook.createCellStyle();
               titleStyle.setAlignment(HorizontalAlignment.CENTER);
//               titleStyle.setAlignment(VerticalAlignment.MIDDLE);
               XSSFFont titleFont = workbook.createFont();
               titleFont.setBold(true);
               titleFont.setFontHeightInPoints((short) 14);
               titleStyle.setFont(titleFont);

               XSSFCellStyle headerStyle = workbook.createCellStyle();
               headerStyle.setAlignment(HorizontalAlignment.CENTER);
//               headerStyle.setAlignment(VerticalAlignment.MIDDLE);
               headerStyle.setBorderTop(BorderStyle.THIN);
               headerStyle.setBorderBottom(BorderStyle.THIN);
               headerStyle.setBorderLeft(BorderStyle.THIN);
               headerStyle.setBorderRight(BorderStyle.THIN);
               XSSFFont headerFont = workbook.createFont();
               headerFont.setBold(true);
               headerStyle.setFont(headerFont);

               XSSFCellStyle normalStyle = workbook.createCellStyle();
               normalStyle.setBorderTop(BorderStyle.THIN);
               normalStyle.setBorderBottom(BorderStyle.THIN);
               normalStyle.setBorderLeft(BorderStyle.THIN);
               normalStyle.setBorderRight(BorderStyle.THIN);

               // Title Row (Merged Across 3 Columns)
               XSSFRow titleRow = sheet.createRow(0);
               XSSFCell titleCell = titleRow.createCell(0);
               titleCell.setCellValue("Upload Course Template");
               titleCell.setCellStyle(titleStyle);
               sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));  // Merge 3 columns for title

          

               // Metadata (Academic Year, Degree, Semester)
               XSSFRow metaRow1 = sheet.createRow(2);
               XSSFCell cell1 = metaRow1.createCell(0);
               cell1.setCellValue("Academic Year:");
               cell1.setCellStyle(headerStyle);

               XSSFCell cell2 = metaRow1.createCell(1);
               cell2.setCellValue(academicYear);
               cell2.setCellStyle(headerStyle);

               XSSFRow metaRow2 = sheet.createRow(3);
               XSSFCell cell3 = metaRow2.createCell(0);
               cell3.setCellValue("Degree:");
               cell3.setCellStyle(headerStyle);

               XSSFCell cell4 = metaRow2.createCell(1);
               cell4.setCellValue(degreeName);
               cell4.setCellStyle(headerStyle);

               XSSFRow metaRow3 = sheet.createRow(4);
               XSSFCell cell5 = metaRow3.createCell(0);
               cell5.setCellValue("Semester:");
               cell5.setCellStyle(headerStyle);

               XSSFCell cell6 = metaRow3.createCell(1);
               cell6.setCellValue(semester);
               cell6.setCellStyle(headerStyle);
               


               // Course Details Header
               XSSFRow headerRow = sheet.createRow(6);
               XSSFCell headerRowcell1 = headerRow.createCell(0);
               XSSFCell headerRowcell2 = headerRow.createCell(1);
               XSSFCell headerRowcell3 = headerRow.createCell(2);
               headerRowcell1.setCellValue("Course Code");
               headerRowcell2.setCellValue("Course Name ");
               headerRowcell3.setCellValue("Course Type");
               headerRowcell1.setCellStyle(headerStyle);
               headerRowcell2.setCellStyle(headerStyle);
               headerRowcell3.setCellStyle(headerStyle);

               // Auto-size columns
               for (int i = 0; i <= 5; i++) {
                   sheet.autoSizeColumn(i);
               }

               // Write workbook to output stream
               workbook.write(outputStream);
               return outputStream;
           } catch (IOException e) {
               throw new RuntimeException("Error generating template", e);
           }
       }

      

       @Override
       public String saveOrUpdate(Degree degree) {
           try {
               // Fetch the college based on collegeTenantId
               Optional<College> optionalCollege = collegeRepo.findByCollegeTenantId(degree.getCollegeTenantId());

               if (!optionalCollege.isPresent()) {
                   throw new AdminException("College not found with provided collegeTenantId: " + degree.getCollegeTenantId());
               }

               College college = optionalCollege.get();
               List<Degree> existingDegrees = college.getDegree();

               // ✅ Fix: Initialize the degree list if it's null
               if (existingDegrees == null) {
                   existingDegrees = new ArrayList<>();
                   college.setDegree(existingDegrees);
               }

               // Check if this is an update or a new save
               Optional<Degree> existingDegreeOpt = existingDegrees.stream()
                   .filter(d -> d.getId().equals(degree.getId()))
                   .findFirst();

               if (existingDegreeOpt.isPresent()) {
                   // Update existing degree
                   Degree existingDegree = existingDegreeOpt.get();
                   existingDegree.setDegreeName(degree.getDegreeName());
                   existingDegree.setDepartments(degree.getDepartments());
               } else {
                   // Generate a new degreeId and add a new degree
                   String prefix = degree.getDegreeName().substring(0, Math.min(2, degree.getDegreeName().length())).toLowerCase();
                   int randomNumber = new Random().nextInt(9000) + 1000; 
                   prefix = prefix + randomNumber;
                   degree.setId(prefix);

                   existingDegrees.add(degree);
               }

               // Save the updated college object
               collegeRepo.save(college);

               return "Degree saved/updated successfully with ID: " + degree.getId();

           } catch (AdminException e) {
               throw e; // Rethrow custom exception
           } catch (Exception e) {
               throw new AdminException("An error occurred while saving/updating the degree: " + e.getMessage(), e);
           }
       }



	@Override
	public List<Degree> getAllDegree() {
	    try {
	        List<College> colleges = collegeRepo.findAll();

	        if (colleges == null || colleges.isEmpty()) {
	            throw new AdminException("No colleges found.");
	        }

	        // Extract all degrees from colleges
	        List<Degree> degrees = colleges.stream()
	            .flatMap(college -> college.getDegree().stream())
	            .collect(Collectors.toList());

	        if (degrees.isEmpty()) {
	            throw new AdminException("No degrees found.");
	        }

	        return degrees;

	    } catch (Exception e) {
	        throw new AdminException("Error while fetching degrees: " + e.getMessage(), e);
	    }
	}

	@Override
	public String deleteDegree(String degreeId, String collegeTenantId) {
	    try {
	        Optional<College> collegeOpt = collegeRepo.findByCollegeTenantId(collegeTenantId);

	        if (collegeOpt.isPresent()) {
	            College college = collegeOpt.get();
	            List<Degree> degrees = college.getDegree();

	            boolean removed = degrees.removeIf(degree -> degree.getId().equals(degreeId));

	            if (!removed) {
	                return "Degree with ID " + degreeId + " not found in college.";
	            }

	            college.setDegree(degrees); // Update the degree list
	            collegeRepo.save(college); // Save the updated college document

	            return "Degree deleted successfully!";
	        } else {
	            return "College with tenant ID " + collegeTenantId + " not found.";
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to delete degree: " + e.getMessage());
	    }
	}



	
	
	
}
