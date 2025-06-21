package com.mycompany.pharmacymanagementsystem;


import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


/**
 *
 * @author Pc-Home
 */
public class Customer extends User {
    private static int idCountCustomer;
    private final String customerId;
    private Order order;
    private List<Order> orderHistory = new ArrayList<>();
   
    Customer(String name){
        super(name);
        this.customerId = "cs" + (++idCountCustomer);
    }
    public Order getOrder() {
        return order;
    }
   
    public void makeOrder(ArrayList<Item> items){
        this.order = new Order(items);
        orderHistory.add(this.order);
    }
    public void makeOrder(){
        this.order = new Order();
        orderHistory.add(this.order);
    }
    
    public void getOrderHistory(){
        System.out.println(this.getName());
        for(Order o : orderHistory){
            System.out.println(o.getOrderId());
            //System.out.println(o.getPayment());
            // print list of items, order attributes
        }
    }
    
    public String getCustomerId(){
        return this.customerId;
    }
    
    public void getCustomerInfo(){
        System.out.println("Customer Name: "+this.getName());
        System.out.println("ID: "+this.customerId);
        // if we want to print something of order history 
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        else if(o==null || getClass()!=o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId,customer.getCustomerId());
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(getCustomerId());
    }
}
