package com.mycompany.pharmacymanagementsystem;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.util.ArrayList;


/*
 * Some Notes:
 * 1. Inventory is set automatically (in constructor) on the first order object made.
 * 2. removeItem() should add back (reverse deductFromInventory). But it does not.
 * 3. orderNumber keeps track of how many order objects created. No need for getter.
 *
 */


/**
 *
 * @author menna
 */
public class Order {
    private String orderId;
    private ArrayList <Item> items = new ArrayList<>();
    private static Inventory inventory;
    private static int orderNumber = 0;

    public Order(){
        this.orderId = "O" + Order.orderNumber;
        Order.orderNumber++;
    }

    public Order(Inventory inventory){
        Order.inventory = inventory;
    }

    public Order (ArrayList<Item> items){
        this.items = items;
        for(int i=0;i<items.size();i++){
            inventory.deductFromInventory(items.get(i), 1);
        }
        this.orderId = "O" + Order.orderNumber;
        Order.orderNumber++;
    }
    public String getOrderId() {
        return orderId;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public void addItem(Item item, int quantity){
        if(inventory.deductFromInventory(item, quantity)){
            for(int i=0;i<quantity;i++){
                items.add(item);
            }
        }
    }
    public void removeItem (Item item, int quantity){
        items.remove(item);
    }
    public double calculateOrderTotal(){
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getPrice();
        }
        System.out.println("Your total is " + total);
        return total;
    }
    public void finalizeOrder(Payments payment, double amount){
        if(payment.pay(amount)){
            Receipt r = generateReceipt();
            System.out.println(r);
        }
        else{
            if(payment instanceof Cash)
                System.out.println( "$"+amount + " is not enough.Your cart is still saved. Finalize order with correct amount to complete transaction.");
            else
                System.out.println("Card balance is not enough. Your cart is still saved. Finalize order with new card or add balance to complete transaction.");
        }
    }
    public Receipt generateReceipt(){
        Receipt r = new Receipt(this);
        return r;      
    }   
}


