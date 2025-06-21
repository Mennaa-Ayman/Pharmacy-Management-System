package com.mycompany.pharmacymanagementsystem;
import java.time.LocalDate;
import java.util.ArrayList;


public class Expenses {
    private LocalDate date;
    private double operatingExpenses;
    private double wages;
    private double total;
    private static ArrayList<Expenses> expensesHistory = new ArrayList<>();
    
    public Expenses(LocalDate date, double operatingExpenses, double wages){
        this.operatingExpenses = operatingExpenses;
        this.wages = wages;
        this.date = date;
        this.total = operatingExpenses + wages;
        expensesHistory.add(this);

        
    }
    public LocalDate getDate(){
        return date;
    }
    public double getOperatingExpenses(){
        return operatingExpenses;
    }
    public double getWages(){
        return wages;
    }
    public double getTotal() {
        return total;
    }
    
    public static ArrayList<Expenses> getExpensesHistory() {
        return expensesHistory;
    }

    @Override
    public String toString(){
        return "\n--------\n" +
                date.getMonth() + " " + date.getDayOfMonth() +
                "\n--------" +
                "\nOperating Expenses: " + operatingExpenses +
                "\nWages: " + wages +
                "\nTotal: " + total;
                
    }
    // @Override
    // public String toString(){
    //     return "Today is "+ date.getMonth() + " " + 
    //             date.getDayOfMonth(); // Returns Today is {Month} {Day}
    // }

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        Expenses exp = new Expenses(today, 21567.843, 10000);
        System.out.println(exp);
        /*
         * Main Testing Results: Work as expected
         */
    }
    
}
