package com.mycompany.pharmacymanagementsystem;

import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Pc-Home
 */
public class Branch extends Pharmacy {
    private String location;
    private List <Admin> admins = new ArrayList<>();
    private List <Customer> customers = new ArrayList<>();
    private Inventory inventory;
    private List <Financials> financials = new ArrayList <>();
    
    public Branch(String location){
        this.location = location;
    }
    public String getLocation() {
            return location;
        }
    public void addAdmin(Admin admin){
        if(!admins.contains(admin)){
            admins.add(admin);
            System.out.println("Admin added.");
        }
        else{
            System.out.println("Admin already exists.");
        }
    }
    public void removeAdmin(Admin admin){
        for(Admin a : admins){
            if(a.equals(admin)){
                admins.remove(a);
            }
        }
    }
    public void addCustomer(Customer customer){
        if(!customers.contains(customer)){
            customers.add(customer);
            System.out.println("Customer added.");
        }
        else
            System.out.println("Customer already exists.");
    }
    public void addFinancials(Financials financial){
        financials.add(financial);
    }
    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    
    
    public void printAdmins(){
        for(Admin admin : admins){
            admin.getAdminInfo();
        }
    }
    public void printCustomers(){
        for(Customer customer : customers){
            System.out.println(customer.getName());
            /* if we want to print customers order history*/
        }
    }
    public List<Financials> getFinancials(){
        return financials;
    }
    public void printInventory(){
        /* print inventory stocks, which are all maps */
    }
}
