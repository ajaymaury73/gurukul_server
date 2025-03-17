package com.ajay.gurukulX.adminDomain;

import org.springframework.data.annotation.Id;

public class ClassOrCourse {
	private String name;
	private CourseType courseType;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CourseType getCourseType() {
		return courseType;
	}
	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	
	

}
