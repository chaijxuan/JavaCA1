/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPRG_CA1;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentManagement {
    private ArrayList<Student> StudentList = new ArrayList<>();
//    private Student[] students = new Student[100]; // Array to hold StudentEnquirySystem.Student objects
//    private int studentSize; // Current number of students

    // Constructor to initialize the StudentEnquirySystem.StudentManagement object with the array of students and its size
//    public StudentManagement(Student[] students, int studentSize) {
//        this.students = students;
//        this.studentSize = studentSize;
//    }

    public StudentManagement(ArrayList<Student> Students) {
        this.StudentList = Students;
    }

    // Method to display all students
    public void displayAllStudents() {
        StringBuilder allStudentsMessage = new StringBuilder();
        allStudentsMessage.append("<html><body>");

        // Iterating through each student using a traditional for loop
        for (int i = 0; i < StudentList.size(); i++) {
            Student student = StudentList.get(i);
            ArrayList<Module> Modules = StudentList.get(i).getModules();

            // Creating a table for each student
            allStudentsMessage.append("<table border='1' cellpadding='5' cellspacing='0' style='margin-bottom: 20px;'>");
            allStudentsMessage.append("<tr><th>Name</th><th>Admin</th><th>Class</th><th>Modules Taken</th><th>Grade</th></tr>");

            // Adding the student details in the first row with the first module
            if (!Modules.isEmpty()) { // check if modules > 0
                Module module = Modules.getFirst();
                String moduleInfo = String.format("%s/%s/%d", module.getModuleCode(), module.getModuleName(), module.getCreditUnit());
                allStudentsMessage.append("<tr>")
                        .append("<td>").append(student.getStudName()).append("</td>")
                        .append("<td>").append(student.getAdminNum()).append("</td>")
                        .append("<td>").append(student.getStudClass()).append("</td>")
                        .append("<td>").append(moduleInfo).append("</td>")
                        .append("<td>").append(module.getGrade()).append("</td>")
                        .append("</tr>");
            }

            // Adding the remaining modules in subsequent rows
            for (int j = 1; j < Modules.size(); j++) {
                Module module = Modules.get(j);
                String moduleInfo = String.format("%s/%s/%d", module.getModuleCode(), module.getModuleName(), module.getCreditUnit());
                allStudentsMessage.append("<tr>")
                        .append("<td></td>")
                        .append("<td></td>")
                        .append("<td></td>")
                        .append("<td>").append(moduleInfo).append("</td>")
                        .append("<td>").append(module.getGrade()).append("</td>")
                        .append("</tr>");
            }

            allStudentsMessage.append("</table>");
        }

        // Displaying the final formatted message
        JOptionPane.showMessageDialog(null, allStudentsMessage.toString(), "All StudentEnquirySystem.Student Report", JOptionPane.INFORMATION_MESSAGE);
    }


    // Method to get student(s) by class
    public void getStudentByClass(String classOfStud) {
        int count = 0;
        double totalGPA = 0;
        String message = "";

        for (int i = 0; i < StudentList.size(); i++) {
            Student student = StudentList.get(i);
            if (student.getStudClass().equals(classOfStud)) {
                count++;
                totalGPA += student.getGpa();
            }
        }

        if (count > 0) {
            double averageGPA = totalGPA / count;
            DecimalFormat df = new DecimalFormat("#.##");
            String formattedAverageGPA = df.format(averageGPA);
            message += "Number of student(s) in " + classOfStud + ": " + count + "\n"
                    + "Average GPA: " + formattedAverageGPA + "\n";
        } else {
            message = "No students found in class '" + classOfStud + "'";
        }

        JOptionPane.showMessageDialog(null, message, "Class Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to get student(s) by name
    public void getStudentByName(String name) {
        StringBuilder message = new StringBuilder();
        boolean found = false;

        // Iterating through each student to find the student with the specified name
        for (int i = 0; i < StudentList.size(); i++) {
            Student student = StudentList.get(i);
            if (name.equals(student.getStudName())) {
                found = true;

                // Creating a table for the found student
                message.append("<html><body>");
                message.append("<table border='1' cellpadding='5' cellspacing='0' style='margin-bottom: 20px;'>");
                message.append("<tr><th>Name</th><th>Admin Num</th><th>Class</th><th>Module Taken</th><th>Grade</th></tr>");

                ArrayList<Module> modules = student.getModules();
                for (int j = 0; j < modules.size(); j++) {
                    Module module = modules.get(j);
                    String moduleInfo = String.format("%s/%s/%d", module.getModuleCode(), module.getModuleName(), module.getCreditUnit());

                    if (j == 0) {
                        message.append("<tr>")
                                .append("<td>").append(student.getStudName()).append("</td>")
                                .append("<td>").append(student.getAdminNum()).append("</td>")
                                .append("<td>").append(student.getStudClass()).append("</td>")
                                .append("<td>").append(moduleInfo).append("</td>")
                                .append("<td>").append(module.getGrade()).append("</td>")
                                .append("</tr>");
                    } else {
                        message.append("<tr>")
                                .append("<td></td>")
                                .append("<td></td>")
                                .append("<td></td>")
                                .append("<td>").append(moduleInfo).append("</td>")
                                .append("<td>").append(module.getGrade()).append("</td>")
                                .append("</tr>");
                    }
                }

                DecimalFormat df = new DecimalFormat("#.##"); // Formatting GPA to 2 decimal places
                String formattedGPA = df.format(student.getGpa());
                message.append("</table>")
                        .append("<p>GPA: ").append(formattedGPA).append("</p>")
                        .append("</body></html>");
                break;
            }
        }

        // Displaying the appropriate message based on whether the student was found
        if (!found) {
            JOptionPane.showMessageDialog(null, "Cannot find the student \"" + name + "\"!!!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, message.toString(), "Student Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method to display all students in a JTable
    public JTable displayAllStudentsInTable() {
        // Column names for the table
        String[] columnNames = {"Name", "Admin Num", "Class", "Module", "Grade"};

        // Table model to hold the student data
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Iterating through each student and their modules to add rows to the table model
        for (int i = 0; i < StudentList.size(); i++) {
            Student student = StudentList.get(i);
            ArrayList<Module> modules = StudentList.get(i).getModules();

            for (Module module : modules) {
                String moduleInfo = String.format("%s/%s/%d", module.getModuleCode(), module.getModuleName(), module.getCreditUnit());
                tableModel.addRow(new Object[]{student.getStudName(), student.getAdminNum(), student.getStudClass(), moduleInfo, module.getGrade()});
            }
        }

        // Creating the table with the table model
        JTable studentTable = new JTable(tableModel);
        studentTable.setFillsViewportHeight(true);

        // Displaying the table in a scroll pane using JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(studentTable), "All Student Information", JOptionPane.INFORMATION_MESSAGE);
        return studentTable;
    }

    // Method to export JTable data to CSV
    public void exportToCSV(JTable table, String filePath) {
        try (FileWriter csvWriter = new FileWriter(filePath)) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Write header row
            for (int col = 0; col < model.getColumnCount(); col++) {
                csvWriter.append(model.getColumnName(col));
                if (col < model.getColumnCount() - 1) {
                    csvWriter.append(",");
                }
            }
            csvWriter.append("\n");

            // Write data rows
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    csvWriter.append(model.getValueAt(row, col).toString());
                    if (col < model.getColumnCount() - 1) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");
            }

            JOptionPane.showMessageDialog(null, "Data exported to CSV successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error exporting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Combined method to sort students by GPA first (in descending order), then by name (in ascending order)
    public void sortStudentsByGPAThenName() {
        // Sort the students array from index 0 to size of StudentList using a comparator
        StudentList.sort(Comparator
                // Create a comparator that compares students by their GPA in ascending order
                .comparingDouble(Student::getGpa)
                // Reverse the order to sort by GPA in descending order
                .reversed()
                // If two students have the same GPA, then compare by their names in ascending order
                .thenComparing(Student::getStudName));


        // After sorting, display the sorted list of students
        displaySortedStudents();
    }

    // Method to display sorted students with name, class, and GPA using HTML for proper alignment
    private void displaySortedStudents() {
        StringBuilder sortedStudentsMessage = new StringBuilder();
        sortedStudentsMessage.append("<html><body><table border='1' cellpadding='5' cellspacing='0'>");
        sortedStudentsMessage.append("<tr><th>Name</th><th>Class</th><th>GPA</th></tr>");

        // Iterating through each student to add rows to the HTML table
        for (int i = 0; i < StudentList.size(); i++) {
            Student student = StudentList.get(i);
            sortedStudentsMessage.append("<tr>")
                    .append("<td>").append(student.getStudName()).append("</td>")
                    .append("<td>").append(student.getStudClass()).append("</td>")
                    .append("<td>").append(String.format("%.2f", student.getGpa())).append("</td>")
                    .append("</tr>");
        }

        sortedStudentsMessage.append("</table></body></html>");

        // Displaying the final formatted message using JOptionPane
        JOptionPane.showMessageDialog(null, sortedStudentsMessage.toString(), "Sorted Student Report", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method for your part: add a new student
    public void AddNewStudent() {
        try {
            String Name = JOptionPane.showInputDialog(null, "Enter name: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE);
            String AdminCode = JOptionPane.showInputDialog(null, "Enter Admin: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE);
            String Class = JOptionPane.showInputDialog(null, "Enter Class: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE);
            // wow im sorry jx but this does not look nice at all and I have to submit this on Monday lol and I can't seem to make this look nicer
            int NumberOfModules = ValidateModules();
            ArrayList<Module> Modules = new ArrayList<>();
            for (int i = 0; i < NumberOfModules; i++) {
                String ModuleCode = ValidateModuleCode(i + 1, true);
                String ModuleName = ValidateModuleName(i + 1, true);
                int CreditUnit = ValidateCreditUnit(i + 1, true);
                int ModuleMarks = ValidateModuleMarks(i + 1, true);
                Module module = new Module(ModuleCode, ModuleName, CreditUnit, ModuleMarks);
                Modules.add(module);
            }
            Student student = new Student(Name, AdminCode, Class, Modules);
            student.setGpa(student.calculateGPA());
            StudentList.add(student);
            JOptionPane.showMessageDialog(null, "Successfully added all Modules Data!", "Student Admin System", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Student Admin System", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    // Validation function for No of Modules
    public int ValidateModules() {
        try {
            int NumberOfModules = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of Modules Taken: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE));
            if (NumberOfModules == -1) {
                return 0;
            }
            if (NumberOfModules <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid Input. A Student should have at least 1 module", "Student Admin System", JOptionPane.ERROR_MESSAGE); // throw exception for 0 and below
                return ValidateModules();
            }

            return NumberOfModules;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Input. Please try again. ", "Student Admin System", JOptionPane.ERROR_MESSAGE);
            return ValidateModules();
        }
    }


    // Validation function for Module Code
    public String ValidateModuleCode(int iterationNumber, boolean IterationOrNot) { // iterationNumber used for String Concatenation
        String ModuleCode = IterationOrNot ?
                JOptionPane.showInputDialog(null, "Enter Module Code for module " + iterationNumber + ": ", "Student Admin System", JOptionPane.QUESTION_MESSAGE)
                :
                JOptionPane.showInputDialog(null, "Enter Module Code for module: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE);
        if (ModuleCode.matches("^[A-Z]{2}[0-9]{4}$")) { // if you're wondering this is a regex that validates the ModuleCode to be of format two letters followed by four integers where the letters are uppercase and the integers can go from 0-9
            return ModuleCode;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Module Code.", "Student Admin System", JOptionPane.ERROR_MESSAGE);
            return ValidateModuleCode(iterationNumber, IterationOrNot);
        }
    }


    // Validation function for Module Name
    public String ValidateModuleName(int iterationNumber, boolean IterationOrNot) {
        String ModuleName = IterationOrNot ?
                JOptionPane.showInputDialog(null, "Enter Module Name for module " + iterationNumber + ": ", "Student Admin System", JOptionPane.QUESTION_MESSAGE)
                :
                JOptionPane.showInputDialog(null, "Enter Module Name for module: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE);
        if (ModuleName.matches("^[A-Za-z0-9 ]+$")) {
            return ModuleName;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Module Name.", "Student Admin System", JOptionPane.ERROR_MESSAGE);
            return ValidateModuleName(iterationNumber, IterationOrNot);
        }
    }


    // Validation function for Module Credit Unit
    public int ValidateCreditUnit(int interationNumber, boolean IterationOrNot) {
        try {
            // I assume the person won't go to absurd numbers like < 100 therefore I won't validate for that I will only validate for accepted input types just for info
            int CreditUnit = IterationOrNot ?
                    Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Credit Unit for module " + interationNumber + ": ", "Student Admin System", JOptionPane.QUESTION_MESSAGE))
                    :
                    Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Credit Unit for module: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE));
            if (CreditUnit > 0) {  // this is in case some guy clowns and puts like negative numbers
                return CreditUnit;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credit Unit.", "Student Admin System", JOptionPane.ERROR_MESSAGE);
                return ValidateCreditUnit(interationNumber, IterationOrNot);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Credit Unit.", "Student Admin System", JOptionPane.ERROR_MESSAGE);
            return ValidateCreditUnit(interationNumber, IterationOrNot);
        }
    }

    // Validation function for Module Marks
    public int ValidateModuleMarks(int iterationNumber, boolean IterationOrNot) {  // IterationOrNot parameter is for
        try {
            // sorry for this part I think it's easier to add a extra parameter rather than make a entirely new fn based on one iterationNumber String change just too mafan
            int ModuleMarks = IterationOrNot ?
                    Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Module Marks for module " + iterationNumber + ": ", "Student Admin System", JOptionPane.QUESTION_MESSAGE))
                    :
                    Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Module Marks for module: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE));
            if (ModuleMarks >= 0 && ModuleMarks <= 101) { // checks if marks are out of 100
                return ModuleMarks;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Module Marks.", "Student Admin System", JOptionPane.ERROR_MESSAGE);
                return ValidateModuleMarks(iterationNumber, IterationOrNot);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Module Marks.", "Student Admin System", JOptionPane.ERROR_MESSAGE);
            return ValidateModuleMarks(iterationNumber, IterationOrNot);
        }
    }


    // Method for your part: delete a student by admin number
    public void DeleteStudent() {
        try {
            String AdminNumber = JOptionPane.showInputDialog(null, "Enter Admin Number of Student");
            if (AdminNumber == null) {  // this is for cancellations and closing the java window
                return;
            }
            if (AdminNumber.matches("^[A-Za-z]{1}[0-9]{7}$")) {
                boolean DeletedStatus = StudentList.removeIf(student -> student.getAdminNum().equals(AdminNumber)); // this is to check if it was successful of not
                if (DeletedStatus) {
                    JOptionPane.showMessageDialog(null, "Student Deleted!", "Student Admin System", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Student Not Found!", "Student Admin System", JOptionPane.INFORMATION_MESSAGE);
                DeleteStudent();  // not sure for this jx I have no clue if they want me to move back to the Student Admin System Menu or Retry since error has been thrown
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An Error has occured. Please try again!", "Student Admin System", JOptionPane.ERROR_MESSAGE);
            DeleteStudent();
        }

    }

    // Method for your part: add a new module for a student
    public void AddNewModule() {
        try {
            String AdminNumber = JOptionPane.showInputDialog(null, "Enter admin number of student: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE);
            if (AdminNumber == null) {
                return;
            }
            for (int i = 0; i < StudentList.size(); i++) {
                if (StudentList.get(i).getAdminNum().equals(AdminNumber)) {  // just checking if admin number is the same
                    ModuleAdder(i);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Student does not exist", "Student Admin System", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An Error has occured. Please try again!", "Student Admin System", JOptionPane.ERROR_MESSAGE);
            AddNewModule();
        }
    }

    public void ModuleAdder(int i) {
        try {
            String ModuleCode = ValidateModuleCode(0, false);
            String ModuleName = ValidateModuleName(0, false);
            int CreditUnit = ValidateCreditUnit(0, false);
            int ModuleMarks = ValidateModuleMarks(0, false);
            Module module = new Module(ModuleCode, ModuleName, CreditUnit, ModuleMarks);
            StudentList.get(i).addModule(module);
            int AddMoreModules = JOptionPane.showConfirmDialog(null, "Do you want to add more Modules for this Student?", "Student Admin System", JOptionPane.YES_NO_OPTION);
            if (AddMoreModules == JOptionPane.YES_OPTION) {
                ModuleAdder(i);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An Error has occured. Please try again!", "Student Admin System", JOptionPane.ERROR_MESSAGE);
            ModuleAdder(i);
        }
    }
}