package com.ajay.gurukulX.ExaminationDomain;

import java.util.List;

public class Degree {
	private String id;
	private String degreeName;
	private String degreeType;
	private List<Terms> Terms;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}

	public List<Terms> getTerms() {
		return Terms;
	}

	public void setTerms(List<Terms> terms) {
		Terms = terms;
	}

	

}
