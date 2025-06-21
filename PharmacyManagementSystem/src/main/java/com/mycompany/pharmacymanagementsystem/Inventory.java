package com.mycompany.pharmacymanagementsystem;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Karim Maaty
 */

public class Inventory {
    private final Map<Medicine, Integer> medicineStock;
    private final Map<Supplements, Integer> supplementsStock;
    private final Map<BabyCare, Integer> babyCareStock;
    private final Map<PersonalCare, Integer> personalCareStock;
    private final Map<Devices, Integer> devicesStock;
    
    public Inventory() {
        medicineStock     = new HashMap<>();
        supplementsStock  = new HashMap<>();
        babyCareStock     = new HashMap<>();
        personalCareStock = new HashMap<>();
        devicesStock      = new HashMap<>();
    }

    // Adds a new item or update the stock of an existing item
    public void addStock(Item item, int quantity) {
        
        // Checks the quantity of the item to be added to the stock
        try{
            if(quantity <= 0)
                throw new IllegalArgumentException("Enter a valild number");
        }
        catch(IllegalArgumentException e){
            System.out.println("the entered quantity is negative");
            System.out.println("please enter a valid number");
        }
        
        // first Item: Medicine
        if (item instanceof Medicine) {
            Medicine med = (Medicine) item;
            medicineStock.put(med, medicineStock.getOrDefault(med, 0) + quantity);
        } 
        
        // second Item: Supplements
        else if (item instanceof Supplements) {
            Supplements sup = (Supplements) item;
            supplementsStock.put(sup, supplementsStock.getOrDefault(sup, 0) + quantity);
        } 
        
        // third Item: BabyCare
        else if (item instanceof BabyCare) {
            BabyCare baby = (BabyCare) item;
            babyCareStock.put(baby, babyCareStock.getOrDefault(baby, 0) + quantity);
        }
        
        // Fourth Item: PersonlCare
        else if (item instanceof PersonalCare) {
            PersonalCare pc = (PersonalCare) item;
            personalCareStock.put(pc, personalCareStock.getOrDefault(pc, 0) + quantity);
        } 
        
        // Null pointer handling
        else
            try{
                throw new NullPointerException();
            }
            catch(NullPointerException e){
                System.out.println("Null pointer error");
            }
    }
    
    // Removes an existing item from the inventory
    public void removeStock(Item item){
        
        // first check if the item alreeady exists in the inventory
        try{
            if(!(isAvailable(item))){
                System.out.println(item.getName()+" doesn't exist in the inventory");
                return; 
            }
        }
        catch(NullPointerException e){
            System.out.println("Error: Tried to access a null item.");
        }
        
        
        // first Item: Medicine
        if (item instanceof Medicine) {
            Medicine med = (Medicine) item; 
            medicineStock.remove(med);
        }
        
        // second Item: Supplements
        else if (item instanceof Supplements) {
            Supplements sup = (Supplements) item;
            supplementsStock.remove(sup);
        }
        
        // third Item: BabyCare products
        else if (item instanceof BabyCare) {
            BabyCare baby = (BabyCare) item;
            babyCareStock.remove(baby);
        }
        
        // forth Item: PersonalCare products
        else if (item instanceof PersonalCare) {
            PersonalCare p = (PersonalCare) item;
            personalCareStock.remove(p);
        }
    }
    
    // Checks if the item does exist in the inventory or not 
    public boolean isAvailable(Item item)
    {
        if(item instanceof Medicine)
            return medicineStock.containsKey(item);
        else if(item instanceof Supplements)
            return supplementsStock.containsKey(item);
        else if(item instanceof BabyCare)
            return babyCareStock.containsKey(item);
        else if(item instanceof PersonalCare)
            return personalCareStock.containsKey(item);
        else
            throw new NullPointerException();
    }
    
    // Subtract the quantity of the sold items from the stock in the inventory if it exists
    // Also checks if the desired quantity is availabe in the inventory
    public boolean deductFromInventory(Item item, int quantity) {
        // first check if the item alreeady exists in the inventory
        try{
            if(!(isAvailable(item))){
                System.out.println(item.getName()+" doesn't exist in the inventory");
                return false; 
            }
        }
        catch(NullPointerException e){
            System.out.println("Error: Tried to access a null item.");
        }
        
        try
        {
            
        if(quantity <= 0)
            throw new IllegalArgumentException();
        }
        catch(IllegalArgumentException e){
            System.out.println("please, enter a valid number.");
        }
                
        // first Item: Medicine
        if (item instanceof Medicine) {
            Medicine med = (Medicine) item;
            // check if the branch has the sufficient quantity
            if(medicineStock.get(med) >= quantity) {
                medicineStock.put(med, medicineStock.getOrDefault(med, 0) - quantity);
                return true;
            }
            else 
                System.out.println("Sorry can't add that many "+med.getName()+", we only have "+medicineStock.get(med));
                return false;
            }
        
        // second Item: Supplements
        else if (item instanceof Supplements) {
            Supplements sup = (Supplements) item;
            if(supplementsStock.get(sup) >= quantity) {
                supplementsStock.put(sup, supplementsStock.getOrDefault(sup, 0) - quantity);
                return true;
            }
            else 
                System.out.println("Sorry can't add that many "+sup.getName()+", we only have "+supplementsStock.get(sup));
                return false;
            }
        
        // third Item: BabyCare products
        else if (item instanceof BabyCare) {
            BabyCare baby = (BabyCare) item;
            if(babyCareStock.get(baby) >= quantity) {
                babyCareStock.put(baby, babyCareStock.getOrDefault(baby, 0) - quantity);
                return true;
            }
            else 
                System.out.println("Sorry can't add that many "+baby.getName()+", we only have "+babyCareStock.get(baby));
                return false;
            }
        
        // forth Item: PersonalCare products
        else if (item instanceof PersonalCare) {
            PersonalCare p = (PersonalCare) item;
            if(personalCareStock.get(p) >= quantity){
                personalCareStock.put(p, personalCareStock.getOrDefault(p, 0) - quantity);
                return true;
            }
            else 
                System.out.println("Sorry can't add that many "+p.getName()+", we only have "+personalCareStock.get(p));
                return false;
            }
        return true;
    }
    
    // Shows the quantity of a specified item 
    public void showItemStock(Item item){
        
        if(!(isAvailable(item))) {
            System.out.println(item.getName()+" doesn't exist in the inventory");
        }
        
        System.out.println(item.getName());    
        if(item instanceof Medicine)
            System.out.println("quantity: "+medicineStock.get(item));
        else if(item instanceof Supplements)
            System.out.println("quantity: "+supplementsStock.get(item));
        else if(item instanceof BabyCare)
            System.out.println("quantity: "+babyCareStock.get(item));
        else if(item instanceof PersonalCare)
            System.out.println("quantity: "+personalCareStock.get(item));
        else 
            System.out.println("this item isn't available");
    }
    
    
    // Shows the whole item stock in the inventory
    // each method is for a specific map
    public void showMedStock(){
        for(Medicine m: medicineStock.keySet()){
            System.out.println(m.getName()+"  "+medicineStock.get(m));
        }
    }
    
    public void showSupStock(){
        for(Supplements m: supplementsStock.keySet()){
            System.out.println(m.getName()+"  "+supplementsStock.get(m));
        }
    }
    
    public void showBabyStock(){
        for(BabyCare m: babyCareStock.keySet()){
            System.out.println(m.getName()+"  "+babyCareStock.get(m));
        }
    }
    
    public void showPersonalStock(){
        for(PersonalCare m: personalCareStock.keySet()){
            System.out.println(m.getName()+"  "+personalCareStock.get(m));
        }
    }
    public void addDevice(Devices dev, int quantity){
        for (Devices d : devicesStock.keySet()) {
            if (d.getBrand().equalsIgnoreCase(dev.getBrand())) {
                System.out.println("This device already exists in stock.");
                return;
            }
            
        // Adds the new quantity to the current quantity (or 0 if the device wasn't in the map yet).
        devicesStock.put(dev, devicesStock.getOrDefault(dev, 0) + quantity);
        }
    }
    
    
    public void decreaseDevices(Devices dev, int quantity)
    {
        if(devicesStock.containsKey(dev)){
            if(devicesStock.get(dev) >= quantity)
                devicesStock.put(dev, medicineStock.getOrDefault(dev, 0) - quantity);
            else
                System.out.println("the required quantity isn't available");
        }
        else {
            System.out.println(dev+" doesn't exist in the inventory");
        }
    }
    
    public void removeDevice(Devices dev){
        if(devicesStock.containsKey(dev))
            devicesStock.remove(dev);
        else 
            System.out.println(dev+" doesn't exist in the inventory");
    }
    
    public void showDevicesStock(){
        for(Devices d: devicesStock.keySet()){
            System.out.println(d.getName()+"  "+supplementsStock.get(d));
        }
    }
}