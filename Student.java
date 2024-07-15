/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPRG_CA1;//StudentEnquirySystem.Student.java


import java.util.ArrayList;
import java.util.Arrays;

public class Student {
    private String studName;        // StudentEnquirySystem.Student's name
    private String adminNum;        // StudentEnquirySystem.Student's admin number
    private String studClass;       // StudentEnquirySystem.Student's class
//    private Module[] modules = new Module[100];  // Array to hold the student's modules
    private ArrayList<Module> Modules = new ArrayList<>();
//    private int currentSize;        // Current number of modules the student is taking
    private double gpa;             // StudentEnquirySystem.Student's GPA

    // Constructor to initialize the StudentEnquirySystem.Student object with name, admin number, and class
    public Student(String studName, String adminNum, String studClass, ArrayList<Module> Modules) {
        this.studName = studName;
        this.adminNum = adminNum;
        this.studClass = studClass;
//        this.currentSize = 0;  // Initialize the current size of modules to 0
        this.Modules = Modules;
        this.gpa = 0.0; // Initialize GPA to 0.0
    }

//    public Student2(String studName, String adminNum, String studClass, ArrayList<Module> Modules) {
//        this.studName = studName;
//        this.adminNum = adminNum;
//        this.studClass = studClass;
//        this.currentSize = 0;
//        this.Modules = Modules;
//        this.gpa = 0.0;
//    }

    // Getter for student's name
    public String getStudName() {
        return studName;
    }

    // Setter for student's name
    public void setStudName(String studName) {
        this.studName = studName;
    }

    // Getter for student's admin number
    public String getAdminNum() {
        return adminNum;
    }

    // Setter for student's admin number
    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    // Getter for student's class
    public String getStudClass() {
        return studClass;
    }

    // Setter for student's class
    public void setStudClass(String studClass) {
        this.studClass = studClass;
    }

    // Getter for the array of modules the student is taking
    public ArrayList<Module> getModules() {
        // Return a copy of the modules array with the actual size
//        Module[] actualModules = new Module[currentSize];
//        System.arraycopy(modules, 0, actualModules, 0, currentSize);
//        return actualModules;
        return this.Modules;
    }

    // Setter for the array of modules the student is taking
    // Also recalculates the GPA when modules are updated
    public void setModules(Module[] modules) { // this entire thing is not required actually once we set the arrayList
//        this.modules = modules;
//        this.currentSize = modules.length; // this is not required once I use Java ArrayList
        this.Modules.addAll(Arrays.asList(modules));
        this.gpa = calculateGPA();
    }

    // Method to add a module to the student's list of modules
    public void addModule(Module module) {
//        if (currentSize < modules.length) {   // remember to ask JX what this is for although im gonna change it anyway oops
//            modules[currentSize++] = module;  // Add the module and increment current size
//            this.gpa = calculateGPA();        // Recalculate GPA after adding the module
//        }
        this.Modules.add(module);
        this.gpa = calculateGPA();
    }

    // Getter for the student's GPA
    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    // Private method to calculate the student's GPA based on their modules
    public double calculateGPA() {
        double totalGradePoints = 0;  // Total grade points accumulated
        int totalCredits = 0;         // Total credit units accumulated
        // Loop through all modules to calculate total grade points and total credits
        for (Module module : this.Modules) {
            totalGradePoints += module.getGradePoint() * module.getCreditUnit();
            totalCredits += module.getCreditUnit();
        }

        // Calculate and return GPA
        if (totalCredits == 0) {
            return 0.0;
        } else {
            return totalGradePoints / totalCredits;
        }
    }
}