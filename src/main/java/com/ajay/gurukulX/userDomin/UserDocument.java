package com.ajay.gurukulX.userDomin;

import org.springframework.data.annotation.Id;

public class UserDocument {
    @Id
    private String id;
    private String documentName;
    private String documentNumber;
    private byte[] fileData; 
    private String fileType; 

    // Constructors
    public UserDocument() {}

    public UserDocument(String documentName, String documentNumber, byte[] fileData, String fileType) {
        this.documentName = documentName;
        this.documentNumber = documentNumber;
        this.fileData = fileData;
        this.fileType = fileType;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
