/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bd25crowdengagementsystem;

/**
 *
 * @author dzubair2
 */
import java.util.Date;

public class DataRecord {
    private String recordID;
    private String label;
    private String activityID;
    private String imagePath;
    private Date timestamp;
    
    //DataRecord constructor
    public DataRecord(String recordID, String label, String activityID, String imagePath) {
        this.recordID = recordID;
        this.label = label;
        this.activityID = activityID;
        this.imagePath = imagePath;
        this.timestamp = new Date();
    }

    // Getter methods
    public String getRecordID() {
        return recordID; 
    }
    public String getLabel() {
        return label; 
    }
    public String getActivityID() {
        return activityID; 
    }
    public String getImagePath() {
        return imagePath; 
    }
    public Date getTimestamp() {
        return timestamp; 
    }
}
