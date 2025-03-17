package com.ajay.gurukulX.adminDomain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gurukul_navbars")
public class RoleNavbar {
    @Id
    private String id;
    private String role;
    private List<NavItem> navItems;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<NavItem> getNavItems() {
		return navItems;
	}
	public void setNavItems(List<NavItem> navItems) {
		this.navItems = navItems;
	}
    
    

  
}
