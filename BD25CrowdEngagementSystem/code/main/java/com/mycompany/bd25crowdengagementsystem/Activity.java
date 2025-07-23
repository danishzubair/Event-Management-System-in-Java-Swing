/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bd25crowdengagementsystem;

/**
 *
 * @author dzubair2
 */
import javax.swing.JOptionPane;

public class Activity {
    // Variable declaration
    private String activityID;
    private String name;
    private String format;
    private String location;
    private String status;
    private String assignedDIGID;
    private String imagePath;
    private String source;

    // Activity contructor
    public Activity(String activityID, String name, String format, String location, String status, String assignedDIGID, String source) {
        this.activityID = activityID;
        this.name = name;
        this.format = format;
        this.location = location;
        this.status = status;
        this.assignedDIGID = assignedDIGID;
        this.imagePath = ""; // Default (no image)
        this.source = source; // Initialize source
    }

    // Getter methods
    public String getActivityID() {
        return activityID; 
    }
    public String getName() {
        return name; 
    }
    public String getFormat() {
        return format; 
    }
    public String getLocation() {
        return location; 
    }
    public String getStatus() {
        return status; 
    }
    public String getAssignedDIGID() {
        return assignedDIGID; 
    }
    public String getImagePath() {
        return imagePath; 
    }
    public String getSource() {
        return source; 
    }

    // Setter method for handling invalid image path
    public void setImagePath(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid image path", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            this.imagePath = imagePath;
        }
    }

    public void setAssignedDIGID(String assignedDIGID) {
        this.assignedDIGID = assignedDIGID;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
