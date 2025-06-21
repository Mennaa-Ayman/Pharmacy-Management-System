package com.mycompany.pharmacymanagementsystem;

class Supplements extends Item{
    private String form;
    private String category;
    private int dose;
    
    public Supplements(String stockId, String name, String manufacturer,
            double price, int shelfLife, boolean allergyInducing, String form,
            int dose, String category){
        
        super(stockId, name, manufacturer, price, shelfLife, allergyInducing);
        this.form = form;
        this.dose = dose;
        this.category = category; 
    }
    
    @Override
    public void getDescription()
    {
        System.out.println(getName());
        System.out.println("type"+category);
        System.out.println("Manufacturer: "+getManufacturer());
        System.out.println("Allergy: "+getAllergyInducting());
        System.out.println("Expiry Date: "+getExpiryDate());
        System.out.println("form: "+form);
        System.out.println("Dose per day: "+dose);
        System.out.println("Price: "+getPrice());
    }
}