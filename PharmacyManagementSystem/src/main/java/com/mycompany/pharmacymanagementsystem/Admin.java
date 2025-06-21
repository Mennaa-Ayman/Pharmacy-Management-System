package com.mycompany.pharmacymanagementsystem;
//package pharmacymanagementsystem_;


import java.io.IOException;
import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Pc-Home
 */
public class Admin extends User {
    private static int idCountAdmin;
    private final String adminId;
    private String password;
    private double salary;
    public Admin(String name, double salary){
        super(name);
        this.adminId = "ad" + ++idCountAdmin ;
        this.salary = salary;
        this.setPassword();
    }
    
    private void setPassword(){
        System.out.println("Set New Admin ("+this.getName()+") Password: ");
        String inputPassword = readPassword();
        if(isValidPassword(inputPassword)){
            this.password = inputPassword;
            System.out.println("\nPassword set successfully");
        }
        else{
            System.out.println("\nInvalid Password. It must be at least 6 characters and contain letters and numbers.");
            setPassword();
        }
    }
    private boolean isValidPassword(String password){
        if(password.length() < 6) return false;
        
        boolean hasLetter = false;
        boolean hasDigit = false;
        
        for(char c : password.toCharArray()){
            if(Character.isLetter(c)) hasLetter = true;
            if(Character.isDigit(c)) hasDigit = true;
        }
        return hasLetter && hasDigit;
    }
    private String readPassword(){
        StringBuilder password = new StringBuilder();
        try{
            while(true){
                char c = (char) System.in.read();
                if(c =='\n' || c=='\r') break;
                if(c==8 || c==127){
                    if(password.length()>0){
                        password.deleteCharAt(password.length()-1);
                        System.out.println("\b \b");
                    }
                }
                else{
                    password.append(c);
                    System.out.print("*");
                }
            }
        }
        catch(IOException e){
            System.out.println("\nError reading input. " + e.getMessage());
            return "";
        }
        return password.toString();
    }
    
    public void logIn(){
        System.out.println("Login password: ");
        while(true){
            if(this.password.equals(readPassword())){
                System.out.println("Logged in successfully");
                break;
            }
            else{
                System.out.println("\nIncorrect password. Try again.");
            }
        }
    }
    
    public void setSalary(double newSalary){
        this.salary = newSalary;
    }
    
    public void getAdminInfo(){
        System.out.println("Admin Name: "+this.getName());
        System.out.println("ID: "+adminId);
        System.out.println("Salary: "+salary);
    }
    
    public String getAdminId(){
        return this.adminId;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        else if(o==null || getClass()!=o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(adminId,admin.getAdminId());
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(getAdminId());
    }
}
