package com.mycompany.pharmacymanagementsystem;

public class Devices{
    private String name;
    private String brand;
    private String usage;
    private boolean isDigital;
    
    Devices(String name, String brand, String usage, boolean isDigital){
        this.name = name;
        this.usage = usage;
        this.isDigital = isDigital;
    }
    
    public void getDescription(){
        System.out.println(name);
        System.out.println("brand: "+brand);
        System.out.println("usage: "+usage);
        System.out.println("type: "+isDigital);
    }
    
    public String getName(){
        return name;
    }
    
    public String getBrand(){
        return brand;
    }
}