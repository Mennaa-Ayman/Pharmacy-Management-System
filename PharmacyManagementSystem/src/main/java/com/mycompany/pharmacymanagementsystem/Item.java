package com.mycompany.pharmacymanagementsystem;
import java.time.LocalDate;

/**
 *
 * @author Karim Maaty
 */
public abstract class Item implements Comparable<Item> {
    
    private String stockId;
    private String name;
    private String manufacturer;
    private LocalDate expiryDate;
    private double price;
    private boolean allergyInducing;
    
    public Item(String stockId, String name, String manufacturer,
            double price, int shelfLife, boolean allergyInducing)
    {
        this.stockId = stockId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.allergyInducing = allergyInducing;
        
        this.expiryDate = LocalDate.now();
        this.expiryDate = this.expiryDate.plusYears(shelfLife);
    }
    
    public String getstockId()
    {
        return this.stockId;
    }
    
    public String getName()
    {
        return this.name;
    }
    public String getManufacturer()
    {
        return this.manufacturer;
    }
    
       
    public LocalDate getExpiryDate()
    {
        return this.expiryDate;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public boolean getAllergyInducting()
    {
        return this.allergyInducing;
    }
    
    public abstract void getDescription();
    
    @Override
    public int compareTo(Item item){
        if(this.price > item.getPrice())
            return 1;
        else if(this.price == item.getPrice())
            return 0;
        else
            return -1;
    }
    
    public void compare(Item item){
        if(compareTo(item) > 0)
            System.out.println(name+"is more expensive than "+item.getName());
        
        else if(compareTo(item) == 0)
            System.out.println(name+" is as expensive than "+item.getName());
        
        if(compareTo(item) > 1)
            System.out.println(name+" is more expensive than "+item.getName());
        
    }
    @Override
    public String toString() {
        return "Item{\'"+ name + "\'}";
    }
}