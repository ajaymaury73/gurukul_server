package com.ajay.gurukulX.userDomin;

public class UserQualification {
    private String qualificationLevel; 
    private String boardOrUniversity;
    private int maxMarks;
    private int obtainedMarks;
    private double percentage;
    private double cgpa; 

    // Constructors
    public UserQualification() {}

    public UserQualification(String qualificationLevel, String boardOrUniversity, int maxMarks, int obtainedMarks) {
        this.qualificationLevel = qualificationLevel;
        this.boardOrUniversity = boardOrUniversity;
        this.maxMarks = maxMarks;
        this.obtainedMarks = obtainedMarks;
        calculatePercentageAndCGPA();
    }

    // Method to calculate percentage and CGPA
    private void calculatePercentageAndCGPA() {
        if (maxMarks > 0) {
            this.percentage = (obtainedMarks * 100.0) / maxMarks;
            this.cgpa = this.percentage / 9.5; // Approximate CGPA formula
        }
    }

    // Getters and Setters
    public String getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }

    public String getBoardOrUniversity() {
        return boardOrUniversity;
    }

    public void setBoardOrUniversity(String boardOrUniversity) {
        this.boardOrUniversity = boardOrUniversity;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
        calculatePercentageAndCGPA();
    }

    public int getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(int obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
        calculatePercentageAndCGPA();
    }

    public double getPercentage() {
        return percentage;
    }

    public double getCgpa() {
        return cgpa;
    }
}

