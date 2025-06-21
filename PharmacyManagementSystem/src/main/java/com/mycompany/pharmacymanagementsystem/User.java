package com.mycompany.pharmacymanagementsystem;
//package pharmacymanagementsystem_;

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Pc-Home
 */
    public abstract class User {
        private String name;
        protected User(String name){
            Scanner input = new Scanner(System.in);
            while(true){
                if(!(name.matches("[a-zA-Z\\s]+"))){
                    System.out.println("Invalid name. Only letters and spaces are allowed");
                    System.out.println("Please enter a valid name: ");
                    name = input.nextLine();
                }
                else{
                    this.name = name;
                    break;
                }
            }
        }
        public String getName(){
            return name;
        }
    }
