package com.mycompany.pharmacymanagementsystem;
class PersonalCare extends Item{
    private String category;
    
    public PersonalCare(String stockId, String name, String manufacturer,
            double price, int shelfLife,boolean allergyInducing, String category){
        
        super(stockId, name, manufacturer, price, shelfLife, 
              allergyInducing);
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
        System.out.println("Price: "+getPrice());
    }
}
