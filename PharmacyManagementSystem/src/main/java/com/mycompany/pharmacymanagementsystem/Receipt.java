package com.mycompany.pharmacymanagementsystem;
/*
 * Some Notes on Receipt:
 * 1. No date needed.
 * 2. Class contains the final order made, which is helpful for Sales class.
 * 3. Class basically holds a string of the order. Just like an actual receipt.
 */

import java.util.ArrayList;

public class Receipt {
    
    private double total; 
    private String receiptDetails = "";
    private Order order; 
    
    Receipt (Order order){
        this.order = order;
        makeReceipt();
    }
    public double getTotal() {
        return total;
    }
    private void makeReceipt(){
        ArrayList<Item> items = this.order.getItems();
        this.receiptDetails += "------------------\nReceipt\n------------------\n";
        for(int i=0;i<items.size(); i++){
            this.total += items.get(i).getPrice();
            this.receiptDetails += items.get(i).getName() + " " + items.get(i).getPrice();
            this.receiptDetails += '\n';
        }
        this.receiptDetails += "Total " + this.total + "\n------------------";
    }
    @Override
    public String toString(){
        return this.receiptDetails;
    }
    public static void main(String[] args) {
        double t = 0;
        String s = "";
        for(int i=0;i<5; i++){
            t += 20;
            s += "Panadol" + i + " " + 20;
            s += '\n';
        }
        s += "Total" + t;

        System.out.println(s);
    }
    
}
