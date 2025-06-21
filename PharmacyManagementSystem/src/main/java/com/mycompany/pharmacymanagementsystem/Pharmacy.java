package com.mycompany.pharmacymanagementsystem;
//package pharmacymanagementsystem_;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Pc-Home
 */
public abstract class Pharmacy {
    private final String name = "Pharmacy";
    public String getName() {
        return name;
    }
    private final String ownerName = "Marina";
    public void printPharmacyName(){
        System.out.println(name);
    }
    public void printOwnerName(){
        System.out.println(ownerName);
    }
}