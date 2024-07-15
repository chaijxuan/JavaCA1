package JPRG_CA1;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class StudentUser {

    public static void main(String[] args) {
        // Initialize modules for students
        ArrayList<Module> modules1 = new ArrayList<>();
        modules1.add(new Module("ST0509", "JPRG", 4, 80.0));
        modules1.add(new Module("ST0503", "FOP", 5, 71.0));

        ArrayList<Module> modules2 = new ArrayList<>();
        modules2.add(new Module("ST0509", "JPRG", 4, 72.0));
        modules2.add(new Module("ST0503", "FOP", 5, 88.0));

        ArrayList<Module> modules3 = new ArrayList<>();
        modules3.add(new Module("ST0509", "JPRG", 4, 81.0));
        modules3.add(new Module("ST0503", "FOP", 5, 72.0));

        // maybe use a generator instead for the population of data for modules (advanced idea);
        // Create student objects and set their modules
        Student student1 = new Student("John Tan", "p2312333", "DIT/FT/2A/01", modules1);
        student1.setGpa(student1.calculateGPA());

        Student student2 = new Student("Samsudin", "p2312444", "DIT/FT/2A/02", modules2);
        student2.setGpa(student2.calculateGPA());

        Student student3 = new Student("WhyNoArrayListAllowed", "p2305011", "DIT/FT/2A/03", modules3);
        student3.setGpa(student3.calculateGPA());

        // maybe use a generator instead for the population of data for students (advanced idea);
        // Create Admin objects and set their modules
        Admin Admin1 = new Admin("S2323303", "q9fu0ue!");

        // Initialize StudentEnquirySystem.StudentManagement with the initial students
        ArrayList<Student> initialStudents = new ArrayList<>();
        initialStudents.add(student1);
        initialStudents.add(student2);
        initialStudents.add(student3);
        StudentManagement studentManagement = new StudentManagement(initialStudents);

        // Initialize StudentAdminSystem.AdminManagement with the initial Admins
        ArrayList<Admin> initialAdmins = new ArrayList<>();
        initialAdmins.add(Admin1);
        AdminManagement adminManagement = new AdminManagement(initialAdmins);

        // make another menu to choose between admin and student Enquiry system
        ChoiceMenu(studentManagement, adminManagement);
    }

    private static void StudentMenu(AdminManagement adminManagement, StudentManagement studentManagement) {
        // Menu for user input
        String menu = "Enter your option: \n"
                + "1. Display all students\n"
                + "2. Search student by class\n"
                + "3. Search student by name\n"
                + "4. Show all students in JTable format\n"
                + "5. Sort students by GPA then name\n"
                + "6. Back\n"
                + "7. Exit\n";

        while (true) {
            String choiceStr = JOptionPane.showInputDialog(null, menu, "Student Enquiry System", JOptionPane.QUESTION_MESSAGE);
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
                playSound("D:\\JPRG-21\\JPRG-21\\src\\JPRG_CA1_memberA\\sounds\\choice.wav");  // Absolute path for debugging
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                playSound("D:\\JPRG-21\\JPRG-21\\src\\JPRG_CA1_memberA\\sounds\\error.wav");  // Absolute path for debugging
                continue;
            }

            switch (choice) {
                case 1:
                    studentManagement.displayAllStudents();
                    break;
                case 2:
                    while (true) {
                        String classOfStud = JOptionPane.showInputDialog("Enter the class:");
                        if (classOfStud == null) {  // User clicked cancel
                            break;
                        }
                        if (validateNonEmpty(classOfStud, "Class")) {
                            studentManagement.getStudentByClass(classOfStud);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Class cannot be empty. Please enter a valid class.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        String name = JOptionPane.showInputDialog("Enter the name:");
                        if (name == null) {  // User clicked cancel
                            break;
                        }
                        if (validateNonEmpty(name, "Name")) {
                            studentManagement.getStudentByName(name);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Name cannot be empty. Please enter a valid name.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 4:
                    JTable table = studentManagement.displayAllStudentsInTable();
                    int exportOption = JOptionPane.showConfirmDialog(null, "Do you want to export the data to CSV?", "Export to CSV", JOptionPane.YES_NO_OPTION);
                    if (exportOption == JOptionPane.YES_OPTION) {
                        String defaultDir = "C:\\Users\\chaij\\Downloads";
                        String filePath = defaultDir + "\\student_data.csv";
                        studentManagement.exportToCSV(table, filePath);
                    }
                    break;
                case 5:
                    studentManagement.sortStudentsByGPAThenName();
                    break;
                case 6:
                    ChoiceMenu(studentManagement, adminManagement);
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Program terminated.\nThank You!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice", "Error", JOptionPane.ERROR_MESSAGE);
                    playSound("D:\\JPRG-21\\JPRG-21\\src\\JPRG_CA1_memberA\\sounds\\error.wav");  // Absolute path for debugging
            }
        }
    }

    // Method to run the menu for selection between admin and student memus;
    private static void ChoiceMenu(StudentManagement studentManagement, AdminManagement adminManagement) {
        try {
            int MenuChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Which interface would you like to access? \n 1. Student Enquiry System \n 2. Admin Enquiry System \n 3. Exit", "Interface Choice System", JOptionPane.QUESTION_MESSAGE));
            switch (MenuChoice) {
                case 1 ->
                    StudentMenu(adminManagement, studentManagement);
                case 2 ->
                    // Implement Admin System here
                    AdminChoiceMenu(adminManagement, studentManagement);

                case 3 ->
                    System.exit(0);
                default -> {
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please pick either 1, 2 or 3", "Error", JOptionPane.ERROR_MESSAGE);
                    ChoiceMenu(studentManagement, adminManagement);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            ChoiceMenu(studentManagement, adminManagement);
        }
    }

    private static void AdminChoiceMenu(AdminManagement adminManagement, StudentManagement studentManagement) {
        try {
            int AdminMenuChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your Choice \n\n1. Login \n2. Add New Admin \n3. Reset Password \n4. Back \n5. Logout", "Student Admin System", JOptionPane.QUESTION_MESSAGE));
            switch (AdminMenuChoice) {
                case 1 -> {
                    boolean loginStatus = adminManagement.Login();
                    if (loginStatus) {
                        AdminLoginMenu(studentManagement, adminManagement);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Credentials", "Student Admin System", JOptionPane.ERROR_MESSAGE);
                        AdminChoiceMenu(adminManagement, studentManagement);
                    }
                }
                case 2 -> {
                    adminManagement.MakeNewAdmin();
                    AdminChoiceMenu(adminManagement, studentManagement);
                }

                case 3 -> {
                    adminManagement.ResetPassword(adminManagement.ValidateAdminNumber());
                    AdminChoiceMenu(adminManagement, studentManagement);
                }

                case 4 ->
                    ChoiceMenu(studentManagement, adminManagement);

                case 5 ->
                    System.exit(0);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            AdminChoiceMenu(adminManagement, studentManagement);
        }
    }

    private static void AdminLoginMenu(StudentManagement studentManagement, AdminManagement adminManagement) {
        try {
            int AdminMenuChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your Option: \n\n1. Add New Student \n2. Add new module for student \n3. Delete Student \n4. Logout \n5. Quit", "Student Admin System", JOptionPane.QUESTION_MESSAGE));
            switch (AdminMenuChoice) {
                case 1 -> {
                    studentManagement.AddNewStudent();
                    AdminLoginMenu(studentManagement, adminManagement);
                }
                case 2 -> {
                    studentManagement.AddNewModule();
                    AdminLoginMenu(studentManagement, adminManagement);
                }
                case 3 -> {
                    studentManagement.DeleteStudent();
                    AdminLoginMenu(studentManagement, adminManagement);
                }
                case 4 ->
                    AdminChoiceMenu(adminManagement, studentManagement);

                case 5 -> {
                    JOptionPane.showMessageDialog(null, "Program Terminated.\nThank You!", "Student Admin System", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please pick from 1-4", "Student Admin System", JOptionPane.ERROR_MESSAGE);
                    AdminLoginMenu(studentManagement, adminManagement);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            AdminLoginMenu(studentManagement, adminManagement);
        }

    }

    // Method to play sound effects
    private static void playSound(String soundFileName) {
        try {
            File soundFile = new File(soundFileName);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean validateNonEmpty(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            
            return false;
        }
        return true;
    }
}
