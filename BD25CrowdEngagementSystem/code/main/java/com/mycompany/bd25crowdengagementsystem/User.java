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

public class User {
    //Variable declaration
    private String userID;
    private String name;
    private String role; 
    private String idNumber;
    private String password; 
    private String status;

    //User constructor
    public User(String userID, String name, String role, String idNumber, String password, String status) {
        this.userID = userID;
        this.name = name;
        this.role = role;
        this.idNumber = idNumber;
        this.password = password;
        this.status = status;
    }

    // Getters
    public String getUserID() {
        return userID; 
    }
    public String getName() {
        return name; 
    }
    public String getRole() {
        return role; 
    }
    public String getIDNumber() {
        return idNumber; 
    }
    public String getPassword() {
        return password; 
    }
    public String getStatus() {
        return status; 
    }

    //Setter method for handling invalid user status
    public void setStatus(String status) {
        if (!status.equals("Active") && !status.equals("Inactive")) {
            JOptionPane.showMessageDialog(null, "Invalid status", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            this.status = status;
        }
    }
}