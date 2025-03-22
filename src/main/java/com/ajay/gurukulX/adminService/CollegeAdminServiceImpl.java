package com.ajay.gurukulX.adminService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Term;
import org.springframework.stereotype.Service;

import com.ajay.gurukulX.ExaminationDomain.AcademicCalendar;
import com.ajay.gurukulX.ExaminationDomain.Degree;
import com.ajay.gurukulX.ExaminationDomain.Department;
import com.ajay.gurukulX.ExaminationDomain.Terms;
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
	    // Check if an academic calendar already exists with the same AY, Degree, and Department
	    Optional<AcademicCalendar> existingCalendar = academicCalendarRepo.findByAcademicYearAndDegreeNameAndDegreeTypeAndDeptId(
	        academicCalendar.getAcademicYear(), 
	        academicCalendar.getDegreeName(), 
	        academicCalendar.getDegreeType(), 
	        academicCalendar.getDeptId()
	    );

	    if (existingCalendar.isPresent()) {
	        throw new AdminException("Academic Year is already created for the selected Degree and Department.");
	    }

	    try {
	        academicCalendarRepo.save(academicCalendar);
	        return "Academic calendar saved successfully!";
	    } catch (Exception e) {
	        throw new AdminException("Error saving academic calendar: " + e.getMessage());
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

	                existingCalendar.setAcademicYear(updatedCalendar.getAcademicYear());
	                existingCalendar.setCollegeTenantId(updatedCalendar.getCollegeTenantId());
	                existingCalendar.setDegreeName(updatedCalendar.getDegreeName());
	                existingCalendar.setDegreeType(updatedCalendar.getDegreeType());
	                existingCalendar.setDepartmentName(updatedCalendar.getDepartmentName());
	                existingCalendar.setDeptId(updatedCalendar.getDeptId());
	                existingCalendar.setTerms(updatedCalendar.getTerms());

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
       public ByteArrayOutputStream generateTemplate(String academicYear, String degreeName, String semester, String college, String degreeType, String department) {
           try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
               XSSFSheet sheet = workbook.createSheet("Course Details");

               XSSFCellStyle titleStyle = workbook.createCellStyle();
               titleStyle.setAlignment(HorizontalAlignment.CENTER);
               XSSFFont titleFont = workbook.createFont();
               titleFont.setBold(true);
               titleFont.setFontHeightInPoints((short) 14);
               titleStyle.setFont(titleFont);

               XSSFCellStyle headerStyle = workbook.createCellStyle();
               headerStyle.setAlignment(HorizontalAlignment.CENTER);
               headerStyle.setBorderTop(BorderStyle.THIN);
               headerStyle.setBorderBottom(BorderStyle.THIN);
               headerStyle.setBorderLeft(BorderStyle.THIN);
               headerStyle.setBorderRight(BorderStyle.THIN);
               XSSFFont headerFont = workbook.createFont();
               headerFont.setBold(true);
               headerStyle.setFont(headerFont);

               // Title
               XSSFRow titleRow = sheet.createRow(0);
               XSSFCell titleCell = titleRow.createCell(0);
               titleCell.setCellValue("Upload Course Template");
               titleCell.setCellStyle(titleStyle);
               sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

               // Metadata
               String[][] metadata = {
                   {"College", college},
                   {"Academic Year", academicYear},
                   {"Degree Type", degreeType},
                   {"Degree Name", degreeName},
                   {"Department", department},
                   {"Semester", semester}
               };

               int rowIdx = 2;
               for (String[] entry : metadata) {
                   XSSFRow metaRow = sheet.createRow(rowIdx++);
                   XSSFCell cell1 = metaRow.createCell(0);
                   cell1.setCellValue(entry[0] + ":");
                   cell1.setCellStyle(headerStyle);

                   XSSFCell cell2 = metaRow.createCell(1);
                   cell2.setCellValue(entry[1]);
                   cell2.setCellStyle(headerStyle);
               }

               // Table Headers
               XSSFRow headerRow = sheet.createRow(rowIdx + 1);
               String[] headers = {"Course Code", "Course Name", "Course Type"};

               for (int i = 0; i < headers.length; i++) {
                   XSSFCell headerCell = headerRow.createCell(i);
                   headerCell.setCellValue(headers[i]);
                   headerCell.setCellStyle(headerStyle);
               }

               // Auto-size columns
               for (int i = 0; i < headers.length; i++) {
                   sheet.autoSizeColumn(i);
               }

               workbook.write(outputStream);
               return outputStream;
           } catch (IOException e) {
               throw new RuntimeException("Error creating Excel file", e);
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

	public List<Department> getDepartments(String collegeTenantId, String courseType, String degreeName) {
	    Optional<College> college = collegeRepo.findByCollegeTenantId(collegeTenantId);
	    
	    if (college.isPresent()) {
	        return college.get().getDegree().stream()
	                .filter(degree -> courseType.equals(degree.getDegreeType()) && degreeName.equals(degree.getDegreeName()))
	                .flatMap(degree -> degree.getDepartments().stream())
	                .collect(Collectors.toList());
	    }
	    
	    return Collections.emptyList();
	}


	@Override
	public List<String> getAcademicYearBasedOnCollege(String collegeTenantId) {
	    Set<String> academicYears = new HashSet<>();

	    try {
	        List<AcademicCalendar> academicCalendars = academicCalendarRepo.findByCollegeTenantId(collegeTenantId);

	        if (academicCalendars.isEmpty()) {
	            return new ArrayList<>(); 
	        }

	        for (AcademicCalendar calendar : academicCalendars) {
	            if (calendar.getAcademicYear() != null) {
	                academicYears.add(calendar.getAcademicYear());
	            }
	        }

	    } catch (Exception e) {
	        throw new AdminException("Error while fetching academic years for collegeTenantId: " + collegeTenantId, e);
	    }

	    return new ArrayList<>(academicYears);
	}



	@Override
	public List<String> getDegreeType(String collegeTenantId, String academicYear) {
	    Set<String> degreeTypeSet = new HashSet<>(); 

	    try {
	        List<AcademicCalendar> academicCalendars = academicCalendarRepo.findByCollegeTenantIdAndAcademicYear(collegeTenantId, academicYear);

	       if(academicCalendars.isEmpty()) {
	            return new ArrayList<>(); 
	       }

	        for (AcademicCalendar calendar : academicCalendars) {
	            if (calendar.getDegreeType() != null) {
	                degreeTypeSet.add(calendar.getDegreeType());
	            }
	        }

	    } catch (Exception e) {
	        throw new AdminException("Error fetching degree types: " + e.getMessage(), e);
	    }

	    return new ArrayList<>(degreeTypeSet); 
	}


	@Override
	public List<String> getDegrees(String collegeTenantId, String academicYear, String degreeType) {
	    Set<String> degrees = new HashSet<>(); 

	    try {
	        List<AcademicCalendar> academicCalendars = academicCalendarRepo.findByCollegeTenantIdAndAcademicYearAndDegreeType(collegeTenantId,academicYear,degreeType);

	        if (academicCalendars.isEmpty()) {
	            return new ArrayList<>(); 
	        }
	        
	        for (AcademicCalendar calendar : academicCalendars) {
	            if (calendar.getDegreeName() != null) {
	            	degrees.add(calendar.getDegreeName());
	            }
	        }
		} catch (Exception e) {
	        throw new AdminException("Error fetching degrees: " + e.getMessage(), e);
		}
	    return new ArrayList<>(degrees); 

	}


	@Override
	public List<String> getDepartments(String collegeTenantId, String academicYear, String degreeType,
			String degreeName) {
		 Set<String> deptIds = new HashSet<>();
		try {
			
	        List<AcademicCalendar> academicCalendars = academicCalendarRepo.findByCollegeTenantIdAndAcademicYearAndDegreeType(collegeTenantId,academicYear,degreeType);

			 if (academicCalendars.isEmpty()) {
		            return new ArrayList<>(); 

		        }
		        
		        for (AcademicCalendar calendar : academicCalendars) {
		            if (calendar.getDegreeName() != null) {
		            	deptIds.add(calendar.getDeptId());
		            }
		        }
		} catch (Exception e) {
	        throw new AdminException("Error fetching degrees: " + e.getMessage(), e);
		}
		return new ArrayList<>(deptIds);
	}


	@Override
	public List<Terms> getTerms(String collegeTenantId, String academicYear, String degreeType, String degreeName, String deptId) {
	    List<Terms> terms = new ArrayList<>();
	    try {
	        List<AcademicCalendar> academicCalendars = academicCalendarRepo.findByCollegeTenantIdAndAcademicYearAndDegreeTypeAndDeptId(
	                collegeTenantId, academicYear, degreeType, deptId
	        );

	        if (academicCalendars.isEmpty()) {
	            return new ArrayList<>(); 
	        }

	        for (AcademicCalendar calendar : academicCalendars) {
	            if (calendar.getTerms() != null) {
	                terms.addAll(calendar.getTerms());
	            }
	        }
	    } catch (Exception e) {
	        throw new AdminException("Error fetching degrees: " + e.getMessage(), e);

	    }

	    return terms;
	}



	

	  





	


	
	}
	
	

    
	
	
	
	

