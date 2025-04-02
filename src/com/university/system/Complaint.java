package com.university.system;

public class Complaint {
    String description;
    String status;
    public Complaint(String description) {
        this.description = description;
        this.status = "Pending";
    }

    public String getDescription(){
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void printComplaint(){
        System.out.println("Complaint: " + description + ", Status: " + status);
    }
}
