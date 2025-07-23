/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bd25crowdengagementsystem;

/**
 *
 * @author dzubair2 tsarwar5 hafsar2 lmselazh sali277 nwagu, afmuritadoh
 * UB Number: 24002794, 24009649, 24009663, 24002640, 24002882, 23045806, 23045708
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.table.DefaultTableModel;

public class Main {

    private static ArrayList users = new ArrayList(); // List to store all users
    private static ArrayList activities = new ArrayList(); // List to store all activities
    private static ArrayList dataRecords = new ArrayList(); // List to store data records (like images)
    private static JFrame frame; // Main window of the program
    private static User currentUser; // Tracks the user who is currently logged in
    private static final Color DARK_GREEN = new Color(0, 102, 51); // Custom dark green color for buttons

    // Starts the program by adding some default users and showing the login window
    public static void main(String[] args) {
        users.add(new User("USR-001", "Danish Zubair", "SM", "dz2025", "Danish01012000", "Active"));
        users.add(new User("USR-002", "Siraaj Ali", "AC", "sa2025", "ac123", "Active"));
        users.add(new User("USR-003", "Talha Sarwar", "DIGM", "ts2025", "ts3301", "Active"));
        users.add(new User("USR-004", "Victor Nwagu", "SM", "vn2025", "sm123", "Active"));
        users.add(new User("USR-005", "Hamza Afsar", "AC", "hz2025", "BHWR053@3", "Active"));
        users.add(new User("USR-006", "Lazhary Elazhari", "DIGM", "le2025", "le2025", "Active"));
        users.add(new User("USR-007", "Aremu Muritadoh", "AC", "am2025", "ac123", "Active"));

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showLoginInterface();
            }
        });
    }

    // Shows the login window where users enter their ID and password
    private static void showLoginInterface() {
        frame = new JFrame("BD25 Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel userLabel = new JLabel("Username (ID Number):");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(DARK_GREEN); // Set dark green background
        loginButton.setForeground(Color.WHITE); // Set white text

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idNumber = userField.getText();
                String password = new String(passField.getPassword());
                User user = authenticate(idNumber, password);
                if (user != null && user.getStatus().equals("Active")) {
                    currentUser = user;
                    frame.dispose();
                    String role = user.getRole();
                    if (role.equals("AC")) {
                        showACInterface();
                    } else if (role.equals("DIGM")) {
                        showDIGMInterface();
                    } else if (role.equals("SM")) {
                        showSMInterface();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials or inactive user", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(new JLabel(""));
        frame.add(loginButton);
        frame.setVisible(true);
    }

    // Checks if the entered ID and password match any user in the list
    private static User authenticate(String idNumber, String password) {
        // Loop through all users to find a match
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.get(i);
            if (user.getIDNumber().equals(idNumber) && user.getPassword().equals(password)) {
                return user; // Return the matching user
            }
        }
        return null; // Return null if no match is found
    }

    // Creates a menu at the top of the window for navigation
    private static JMenuBar createNavigationMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu navigateMenu = new JMenu("Navigate");

        JMenuItem smItem = new JMenuItem("Senior Staff Interface");
        smItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentUser.getRole().equals("SM")) {
                    frame.dispose();
                    showSMInterface();
                } else {
                    JOptionPane.showMessageDialog(frame, "Access denied: Only SM can navigate here", "Permission Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JMenuItem digmItem = new JMenuItem("DIGM Interface");
        digmItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentUser.getRole().equals("SM")) {
                    frame.dispose();
                    showDIGMInterface();
                } else {
                    JOptionPane.showMessageDialog(frame, "Access denied: Only SM or DIGM can navigate here", "Permission Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JMenuItem acItem = new JMenuItem("Activity Coordinator Interface");
        acItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentUser.getRole().equals("SM")) {
                    frame.dispose();
                    showACInterface();
                } else {
                    JOptionPane.showMessageDialog(frame, "Access denied: Only SM or AC can navigate here", "Permission Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                frame.dispose();
                showLoginInterface();
            }
        });

        navigateMenu.add(smItem);
        navigateMenu.add(digmItem);
        navigateMenu.add(acItem);
        navigateMenu.addSeparator();
        navigateMenu.add(logoutItem);
        menuBar.add(navigateMenu);
        return menuBar;
    }

    // Shows the Activity Coordinator (AC) interface to add or delete events
    private static void showACInterface() {
        frame = new JFrame("Activity Coordinator Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setJMenuBar(createNavigationMenu());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JComboBox formatBox = new JComboBox(new String[]{"Workshop", "Performance", "Outdoor Activity", "Cinema Movie", "Gathering", "Other"});
        JTextField locationField = new JTextField();
        JComboBox statusBox = new JComboBox(new String[]{"Active", "Canceled"});
        JTextField digField = new JTextField();

        formPanel.add(new JLabel("Event ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name (from BD25):"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Format:"));
        formPanel.add(formatBox);
        formPanel.add(new JLabel("Location:"));
        formPanel.add(locationField);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusBox);
        formPanel.add(new JLabel("DIG ID:"));
        formPanel.add(digField);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add BD25 Event");
        addButton.setBackground(DARK_GREEN); // Set dark green background
        addButton.setForeground(Color.WHITE); // Set white text
        JButton deleteButton = new JButton("Delete Event");
        deleteButton.setBackground(DARK_GREEN); // Set dark green background
        deleteButton.setForeground(Color.WHITE); // Set white text

        String[] columns = {"ID", "Name", "Format", "Location", "Status", "DIG ID"};
        Object[][] data = new Object[activities.size()][6];
        // Loop to fill the table with activity details
        for (int i = 0; i < activities.size(); i++) {
            Activity act = (Activity) activities.get(i);
            data[i][0] = act.getActivityID();
            data[i][1] = act.getName();
            data[i][2] = act.getFormat();
            data[i][3] = act.getLocation();
            data[i][4] = act.getStatus();
            data[i][5] = act.getAssignedDIGID();
        }
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setRowHeight(25);
        JScrollPane tableScrollPane = new JScrollPane(table);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Activity act = new Activity(idField.getText(), nameField.getText(), (String) formatBox.getSelectedItem(),
                                            locationField.getText(), (String) statusBox.getSelectedItem(), digField.getText(), "Not Set");
                activities.add(act);
                JOptionPane.showMessageDialog(frame, "Event " + act.getName() + " added", "Success", JOptionPane.INFORMATION_MESSAGE);

                Object[][] newData = new Object[activities.size()][6];
                // Loop to update the table with new activity data
                for (int i = 0; i < activities.size(); i++) {
                    Activity a = (Activity) activities.get(i);
                    newData[i][0] = a.getActivityID();
                    newData[i][1] = a.getName();
                    newData[i][2] = a.getFormat();
                    newData[i][3] = a.getLocation();
                    newData[i][4] = a.getStatus();
                    newData[i][5] = a.getAssignedDIGID();
                }
                model.setDataVector(newData, columns);
                idField.setText("");
                nameField.setText("");
                formatBox.setSelectedIndex(0);
                locationField.setText("");
                statusBox.setSelectedIndex(0);
                digField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(frame, "Enter Event ID to delete:");
                // Loop to find and remove the activity by ID
                for (int i = 0; i < activities.size(); i++) {
                    Activity act = (Activity) activities.get(i);
                    if (act.getActivityID().equals(id)) {
                        activities.remove(i);
                        break; // Stop loop once found and removed
                    }
                }
                JOptionPane.showMessageDialog(frame, "Event " + id + " deleted", "Success", JOptionPane.INFORMATION_MESSAGE);

                Object[][] newData = new Object[activities.size()][6];
                // Loop to update the table after deletion
                for (int i = 0; i < activities.size(); i++) {
                    Activity a = (Activity) activities.get(i);
                    newData[i][0] = a.getActivityID();
                    newData[i][1] = a.getName();
                    newData[i][2] = a.getFormat();
                    newData[i][3] = a.getLocation();
                    newData[i][4] = a.getStatus();
                    newData[i][5] = a.getAssignedDIGID();
                }
                model.setDataVector(newData, columns);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Shows the DIGM interface to upload images for events
    private static void showDIGMInterface() {
        frame = new JFrame("DIGM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setJMenuBar(createNavigationMenu());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField idField = new JTextField();
        JTextField labelField = new JTextField();
        JTextField actField = new JTextField();
        JComboBox sourceBox = new JComboBox(new String[]{"Internal", "External"});

        formPanel.add(new JLabel("Record ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Image Label:"));
        formPanel.add(labelField);
        formPanel.add(new JLabel("Event ID:"));
        formPanel.add(actField);
        formPanel.add(new JLabel("Image Source:"));
        formPanel.add(sourceBox);

        JPanel buttonPanel = new JPanel();
        JButton uploadButton = new JButton("Upload Event Image");
        uploadButton.setBackground(DARK_GREEN); // Set dark green background
        uploadButton.setForeground(Color.WHITE); // Set white text

        String[] columns = {"ID", "Name", "Format", "Location", "Status", "DIG ID", "Image", "Image Source"};
        Object[][] data = new Object[activities.size()][8];
        // Loop to fill the table with activity data, including images
        for (int i = 0; i < activities.size(); i++) {
            Activity act = (Activity) activities.get(i);
            ImageIcon imageIcon = act.getImagePath().isEmpty() ? null : new ImageIcon(act.getImagePath());
            if (imageIcon != null) {
                Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(scaledImage);
            }
            data[i][0] = act.getActivityID();
            data[i][1] = act.getName();
            data[i][2] = act.getFormat();
            data[i][3] = act.getLocation();
            data[i][4] = act.getStatus();
            data[i][5] = act.getAssignedDIGID();
            data[i][6] = imageIcon;
            data[i][7] = act.getSource();
        }
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            public Class getColumnClass(int column) {
                if (column == 6) {
                    return ImageIcon.class;
                }
                return String.class;
            }
        };
        JTable table = new JTable(model);
        table.setRowHeight(110);
        table.getColumnModel().getColumn(6).setPreferredWidth(110);
        table.getColumnModel().getColumn(6).setCellRenderer(new javax.swing.table.TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                if (value instanceof ImageIcon) {
                    label.setIcon((ImageIcon) value);
                }
                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }
        });
        JScrollPane tableScrollPane = new JScrollPane(table);

        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String activityID = actField.getText();
                    Activity activity = findActivity(activityID);
                    if (activity != null) {
                        String folderPath = "images/" + activityID;
                        File folder = new File(folderPath);
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }
                        String newPath = folderPath + "/" + file.getName();

                        String[] options = {"Update Table Only", "Save to Folder Only", "Both"};
                        int choice = JOptionPane.showOptionDialog(frame, "Choose upload action:", "Upload Options",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);

                        try {
                            if (choice == 0 || choice == 2) {
                                activity.setImagePath(newPath);
                                activity.setSource((String) sourceBox.getSelectedItem());
                            }
                            if (choice == 1 || choice == 2) {
                                Files.copy(file.toPath(), new File(newPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            }
                            DataRecord dr = new DataRecord(idField.getText(), labelField.getText(), activityID, newPath);
                            dataRecords.add(dr);
                            JOptionPane.showMessageDialog(frame, "Image processed for " + activity.getName(), "Success", JOptionPane.INFORMATION_MESSAGE);

                            Object[][] newData = new Object[activities.size()][8];
                            // Loop to update the table with new image data
                            for (int i = 0; i < activities.size(); i++) {
                                Activity a = (Activity) activities.get(i);
                                ImageIcon imageIcon = a.getImagePath().isEmpty() ? null : new ImageIcon(a.getImagePath());
                                if (imageIcon != null) {
                                    Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                                    imageIcon = new ImageIcon(scaledImage);
                                }
                                newData[i][0] = a.getActivityID();
                                newData[i][1] = a.getName();
                                newData[i][2] = a.getFormat();
                                newData[i][3] = a.getLocation();
                                newData[i][4] = a.getStatus();
                                newData[i][5] = a.getAssignedDIGID();
                                newData[i][6] = imageIcon;
                                newData[i][7] = a.getSource();
                            }
                            model.setDataVector(newData, columns);
                            idField.setText("");
                            labelField.setText("");
                            actField.setText("");
                        } catch (java.io.IOException ex) {
                            JOptionPane.showMessageDialog(frame, "Error uploading image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Event not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        buttonPanel.add(uploadButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Shows the Senior Staff Member (SM) interface to manage users and view events
    private static void showSMInterface() {
        frame = new JFrame("Senior Staff Member Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setJMenuBar(createNavigationMenu());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JPanel userPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JComboBox roleBox = new JComboBox(new String[]{"SM", "AC", "DIGM"});
        JTextField idNumField = new JTextField();
        JTextField passField = new JTextField();
        JComboBox statusBox = new JComboBox(new String[]{"Active", "Inactive"});

        userPanel.add(new JLabel("User ID:"));
        userPanel.add(idField);
        userPanel.add(new JLabel("Name:"));
        userPanel.add(nameField);
        userPanel.add(new JLabel("Role:"));
        userPanel.add(roleBox);
        userPanel.add(new JLabel("ID Number:"));
        userPanel.add(idNumField);
        userPanel.add(new JLabel("Password:"));
        userPanel.add(passField);
        userPanel.add(new JLabel("Status:"));
        userPanel.add(statusBox);

        JPanel buttonPanel = new JPanel();
        JButton addUserButton = new JButton("Add User");
        addUserButton.setBackground(DARK_GREEN); // Set dark green background
        addUserButton.setForeground(Color.WHITE); // Set white text
        JButton removeUserButton = new JButton("Remove User");
        removeUserButton.setBackground(DARK_GREEN); // Set dark green background
        removeUserButton.setForeground(Color.WHITE); // Set white text

        String[] eventColumns = {"ID", "Name", "Format", "Location", "Status", "DIG ID", "Image Label", "Image", "Image Source"};
        Object[][] eventData = new Object[activities.size()][9];
        // Loop to fill the event table with activity details and images
        for (int i = 0; i < activities.size(); i++) {
            Activity act = (Activity) activities.get(i);
            ImageIcon imageIcon = act.getImagePath().isEmpty() ? null : new ImageIcon(act.getImagePath());
            String imageLabel = "";
            // Loop to find the image label for this activity
            for (int j = 0; j < dataRecords.size(); j++) {
                DataRecord dr = (DataRecord) dataRecords.get(j);
                if (dr.getActivityID().equals(act.getActivityID()) && dr.getImagePath().equals(act.getImagePath())) {
                    imageLabel = dr.getLabel();
                    break; // Stop once the label is found
                }
            }
            if (imageIcon != null) {
                Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(scaledImage);
            }
            eventData[i][0] = act.getActivityID();
            eventData[i][1] = act.getName();
            eventData[i][2] = act.getFormat();
            eventData[i][3] = act.getLocation();
            eventData[i][4] = act.getStatus();
            eventData[i][5] = act.getAssignedDIGID();
            eventData[i][6] = imageLabel;
            eventData[i][7] = imageIcon;
            eventData[i][8] = act.getSource();
        }
        DefaultTableModel eventModel = new DefaultTableModel(eventData, eventColumns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            public Class getColumnClass(int column) {
                if (column == 7) {
                    return ImageIcon.class;
                }
                return String.class;
            }
        };
        JTable eventTable = new JTable(eventModel);
        eventTable.setRowHeight(110);
        eventTable.getColumnModel().getColumn(7).setPreferredWidth(110);
        eventTable.getColumnModel().getColumn(7).setCellRenderer(new javax.swing.table.TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                if (value instanceof ImageIcon) {
                    label.setIcon((ImageIcon) value);
                }
                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }
        });
        JScrollPane eventScrollPane = new JScrollPane(eventTable);

        String[] userColumns = {"User ID", "Name", "Role", "ID Number", "Status"};
        Object[][] userData = new Object[users.size()][5];
        // Loop to fill the user table with user details
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.get(i);
            userData[i][0] = user.getUserID();
            userData[i][1] = user.getName();
            userData[i][2] = user.getRole();
            userData[i][3] = user.getIDNumber();
            userData[i][4] = user.getStatus();
        }
        DefaultTableModel userModel = new DefaultTableModel(userData, userColumns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable userTable = new JTable(userModel);
        userTable.setRowHeight(25);
        JScrollPane userScrollPane = new JScrollPane(userTable);

        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User(idField.getText(), nameField.getText(), (String) roleBox.getSelectedItem(),
                                     idNumField.getText(), passField.getText(), (String) statusBox.getSelectedItem());
                users.add(user);
                JOptionPane.showMessageDialog(frame, "User " + user.getName() + " added", "Success", JOptionPane.INFORMATION_MESSAGE);

                Object[][] newUserData = new Object[users.size()][5];
                // Loop to update the user table with new user data
                for (int i = 0; i < users.size(); i++) {
                    User u = (User) users.get(i);
                    newUserData[i][0] = u.getUserID();
                    newUserData[i][1] = u.getName();
                    newUserData[i][2] = u.getRole();
                    newUserData[i][3] = u.getIDNumber();
                    newUserData[i][4] = u.getStatus();
                }
                userModel.setDataVector(newUserData, userColumns);

                idField.setText("");
                nameField.setText("");
                roleBox.setSelectedIndex(0);
                idNumField.setText("");
                passField.setText("");
                statusBox.setSelectedIndex(0);
            }
        });

        removeUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(frame, "Enter User ID to remove:");
                // Loop to find and remove the user by ID
                for (int i = 0; i < users.size(); i++) {
                    User user = (User) users.get(i);
                    if (user.getUserID().equals(id)) {
                        users.remove(i);
                        break; // Stop loop once found and removed
                    }
                }
                JOptionPane.showMessageDialog(frame, "User " + id + " removed", "Success", JOptionPane.INFORMATION_MESSAGE);

                Object[][] newUserData = new Object[users.size()][5];
                // Loop to update the user table after removal
                for (int i = 0; i < users.size(); i++) {
                    User u = (User) users.get(i);
                    newUserData[i][0] = u.getUserID();
                    newUserData[i][1] = u.getName();
                    newUserData[i][2] = u.getRole();
                    newUserData[i][3] = u.getIDNumber();
                    newUserData[i][4] = u.getStatus();
                }
                userModel.setDataVector(newUserData, userColumns);
            }
        });

        buttonPanel.add(addUserButton);
        buttonPanel.add(removeUserButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(userPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel tablePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        tablePanel.add(eventScrollPane);
        tablePanel.add(userScrollPane);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Finds an activity in the list by its ID
    private static Activity findActivity(String activityID) {
        // Loop through activities to find a match by ID
        for (int i = 0; i < activities.size(); i++) {
            Activity act = (Activity) activities.get(i);
            if (act.getActivityID().equals(activityID)) {
                return act; // Return the matching activity
            }
        }
        return null; // Return null if no match is found
    }
}