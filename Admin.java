/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPRG_CA1;

public class Admin {
    private final String AdminNo;
    private String Password;

    public Admin(String AdminNo, String Password) {
        this.AdminNo = AdminNo;
        this.Password = Password;
    }

    public void ResetPassword(String Password) {
        this.Password = Password;
    }

    public String GetAdminNumber() {
        return this.AdminNo;
    }

    protected String GetPassword() {
        return this.Password;
    }

}