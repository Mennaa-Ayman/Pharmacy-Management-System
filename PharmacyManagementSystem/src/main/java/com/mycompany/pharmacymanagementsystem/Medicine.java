package com.mycompany.pharmacymanagementsystem;


public class Medicine extends Item{
    private String form;
    private int dose;
    
    public Medicine(String stockId, String name, String manufacturer,
            double price, int shelfLife, boolean allergyInducing, String form,
            int dose){
        
        super(stockId, name, manufacturer, price, shelfLife, allergyInducing);
        this.form = form;
        this.dose = dose;
    }
    
    @Override
    public void getDescription()
    {
        System.out.println(getName());
        System.out.println("Manufacturer: "+getManufacturer());
        System.out.println("Allergy: "+getAllergyInducting());
        System.out.println("Expiry Date: "+getExpiryDate());
        System.out.println("form: "+form);
        System.out.println("Dose per day: "+dose);
        System.out.println("Price: "+getPrice());
    }
}