/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPRG_CA1;

import javax.swing.*;
import java.util.ArrayList;

public class AdminManagement {
    private ArrayList<Admin> Admins = new ArrayList<>();

    public AdminManagement(ArrayList<Admin> Admins) {
        this.Admins = Admins;
    }

    public void ResetPassword(String AdminNumber) {
        for (Admin Admin : Admins) {
            if (Admin.GetAdminNumber().equals(AdminNumber)) {
                String Password = JOptionPane.showInputDialog(null, "Enter Password: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE);
                if (Password == null) {
                    return;
                }
                // Add Validation for Password Later and do a JOptionPane ReEnter
                Admin.ResetPassword(Password);
                return;
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid Password", "Student Admin System", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void MakeNewAdmin() {
        String AdminNumber = ValidateAdminNumber();
        for (Admin admin : Admins) {
            System.out.println(AdminNumber);
            System.out.println(admin.GetAdminNumber());
            if (AdminNumber.equals(admin.GetAdminNumber())) {
                JOptionPane.showMessageDialog(null, "Admin Already Exists", "Student Admin System", JOptionPane.ERROR_MESSAGE);
                MakeNewAdmin();
                return;
            }
        }
        String Password = ValidateAdminPassword();

        Admin Admin = new Admin(AdminNumber, Password);
        Admins.add(Admin);
    }

    public String ValidateAdminNumber() {
        String AdminNumber = JOptionPane.showInputDialog(null, "Enter Admin Number: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE);
        if (AdminNumber.matches("^[A-Za-z][0-9]{7}$")) {
            return AdminNumber;
        }
        else {
            JOptionPane.showMessageDialog(null, "Invalid Admin Number", "Student Admin System", JOptionPane.ERROR_MESSAGE);
            return ValidateAdminNumber();
        }
    }

    public String ValidateAdminPassword() {
        String AdminPassword = JOptionPane.showInputDialog(null, "Enter Admin Password: ", "Student Admin System", JOptionPane.QUESTION_MESSAGE);
        if (AdminPassword.length() < 6 ) {
            JOptionPane.showMessageDialog(null, "Password Length Must be at least 7 characters long", "Student Admin System", JOptionPane.ERROR_MESSAGE);
            return ValidateAdminPassword();
        }
        return AdminPassword;
    }

    public boolean Login() {
        String AdminNumber = ValidateAdminNumber();
        String Password = ValidateAdminPassword();
        for (Admin admin : this.Admins) {
            if (admin.GetAdminNumber().equals(AdminNumber) || admin.GetPassword().equals(Password)) {
                return true;
            }
        }
        return false;
    }







}