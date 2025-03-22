package com.ajay.gurukulX.adminService;

import java.util.List;

import com.ajay.gurukulX.adminDomain.College;
import com.ajay.gurukulX.adminDomain.Faculty;
import com.ajay.gurukulX.adminDomain.RoleNavbar;

public interface AdminService {

	String saveNavbar(RoleNavbar roleNavbar);


	List<RoleNavbar> getAllNavbars();


	String saveCollege(College college);


	List<College> getAllCollege();


	String deleteCollege(String id);


	String updateCollege(String id, College college);









}
