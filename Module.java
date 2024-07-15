/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPRG_CA1;//StudentEnquirySystem.Module.java


public class Module {
    private String moduleCode;    // StudentEnquirySystem.Module code
    private String moduleName;    // StudentEnquirySystem.Module name
    private int creditUnit;       // Credit units for the module
    private double marks;         // Marks obtained in the module

    // Constructor to initialize the StudentEnquirySystem.Module object with code, name, credit units, and marks
    public Module(String moduleCode, String moduleName, int creditUnit, double marks) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.creditUnit = creditUnit;
        this.marks = marks;
    }

    // Getter for module code
    public String getModuleCode() {
        return moduleCode;
    }

    // Setter for module code
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    // Getter for module name
    public String getModuleName() {
        return moduleName;
    }

    // Setter for module name
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    // Getter for credit unit
    public int getCreditUnit() {
        return creditUnit;
    }

    // Setter for credit unit
    public void setCreditUnit(int creditUnit) {
        this.creditUnit = creditUnit;
    }

    // Getter for marks
    public double getMarks() {
        return marks;
    }

    // Setter for marks
    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Method to get the grade based on marks
    public String getGrade() {
        if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    // Method to get the grade points based on the grade
    public double getGradePoint() {
        if (marks >= 80) {
            return 4.0;
        } else if (marks >= 70) {
            return 3.0;
        } else if (marks >= 60) {
            return 2.0;
        } else if (marks >= 50) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}