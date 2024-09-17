package com.inventory.inventoryservice.fileupload.model;

import lombok.*;

import javax.persistence.*;

@Data
public class FileUploadResponse {


    private String fileName;
    private String massage;

    public FileUploadResponse() {
    }

    public FileUploadResponse(String fileName, String massage) {
        this.fileName = fileName;
        this.massage = massage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
