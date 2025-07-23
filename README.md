# Event-Management-System-for-Bradford-2025-in-Java-Swing
Overview

The Event Management System is a Java-based application developed to support the University of Bradford’s BD25 Bradford UK City of Culture Academic Evaluation Team. This system facilitates the management and evaluation of crowd engagement for BD25 activities, ensuring responsible data handling with respect to legal, social, ethical, and professional issues (LSEPI). The system enables senior staff members (SM), activity coordinators (AC), and Data & Information Group (DIG) members to coordinate activities, collect and process data, and develop machine learning models for crowd engagement analysis.

Project Description

The University of Bradford, as part of its BD25 Bradford UK City of Culture legacy, aims to implement a computer-based system to track and evaluate crowd engagement for community and tourist activities (e.g., The Giraffes, RISE, DRAW!). The system supports:





Activity Management: Activity coordinators (ACs) maintain a list of BD25 activities, with the ability to add or cancel activities. Each activity is assigned a Data & Information Group (DIG) led by an academic leader (DIGL), a senior staff member, who oversees live data capture using drones, cameras, microphones, and mobile devices.



Data Handling: DIG members label, preprocess, and analyze data, including internal records (marked as trusted provenance) and external records from web scraping. They use prompt engineering to create and test machine learning models for crowd head counting and engagement analysis.



Role-Based Access: The system provides a user interface for different roles (SM, AC, DIGL, DIG members) to manage activities, enroll participants, and ensure LSEPI compliance.



Demonstration Case Study: The system supports a case study showcasing data storage, labeling, AI model training, and testing for a registered activity.

Each activity, DIG, and member has a name, role, and ID number. Data records (audio, images, videos) are stored in relevant files and folders, with each DIG having at least three members involved in data collection, labeling, preprocessing, model creation, or testing.

For more details, see Bradford 2025 Stories.

Features





Role-Based UI: Tailored interfaces for senior staff, activity coordinators, and DIG members to manage activities and data.



Activity Management: Add, update, or cancel BD25 activities with associated DIG assignments.



Data Processing: Support for data collection, labeling, preprocessing, and machine learning model creation/testing for crowd engagement.



LSEPI Compliance: Ensures responsible data handling with respect to legal, social, ethical, and professional standards.



Case Study Support: Demonstrates end-to-end functionality with evidence of data storage, labeling, and AI model training.

Technologies Used





Java: Core programming language for the application logic and user interface (Java Swing).



File I/O: For managing data records (audio, images, videos) in files and folders.



Machine Learning Libraries (optional, if implemented): Libraries like Weka or Deeplearning4j for model creation and testing.



Web Scraping: Tools like Jsoup for external data collection.

Installation





Clone the Repository:

git clone https://github.com/danishzubair/danishzubair.github.io.git



Navigate to the Project Directory:

cd danishzubair.github.io/event-management-system

Note: If the project files are in a subfolder, adjust the path accordingly.



Set Up Java Environment:





Ensure Java Development Kit (JDK) 8 or higher is installed (java -version to verify).



Install an IDE like IntelliJ IDEA or Eclipse for Java development.



Compile and Run:





Open the project in your IDE and build the Java Swing application.



Alternatively, compile and run from the command line:

javac *.java
java Main

Replace Main with the entry-point class name of your application.

Usage





Launch the Application:





Run the Java application to access the role-based user interface.



Select User Role:





Log in as a Senior Staff Member (SM), Activity Coordinator (AC), DIG Leader (DIGL), or DIG Member.



Manage Activities:





ACs can add, update, or cancel BD25 activities and assign DIGs.



DIGLs coordinate data capture and assign tasks to DIG members.



Data Processing:





DIG members can label data, preprocess records, and create/test machine learning models for crowd engagement.



Case Study:





Register an activity, assign a DIG, collect data, and generate a demonstration case study with evidence of storage, labeling, and AI model training.

Repository Structure

event-management-system/
├── Main.java           # Main application class
├── *.java             # Other Java classes
├── data/              # Folder for data records (audio, images, videos)
└── README.md          # This file

Note: If the project files are not in a subfolder, adjust the structure accordingly.

Contributing

This project is a personal portfolio piece. Contributions are not expected, but feedback is welcome. Contact me at danish347291@gmail.com or via GitHub Issues.

License

This project is for demonstration purposes and not licensed for public use. All rights reserved by Danish Zubair.

Contact





Email: danish347291@gmail.com



Phone: +44 (0) 7405 877 612



GitHub: danishzubair





Built by Danish Zubair, Software Engineering Student at University of Bradford, for the BD25 Bradford UK City of Culture initiative.
