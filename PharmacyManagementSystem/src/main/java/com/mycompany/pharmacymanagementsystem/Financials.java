package com.mycompany.pharmacymanagementsystem;
import java.time.LocalDate;
/*
 * Class Description:
 *      An entity describing the current financial status of the branch at a specific date, specifically:
 *          - accumulated profit
 *          - accumulated revenue
 *          - accumulated expenses
 */
import java.util.ArrayList;

public class Financials {
    private double profit;
    private double revenue;
    private double expenses;
    private LocalDate date;

    Financials(LocalDate date){
        this.date = date;
        accumulateRevenue(this.date);
        accumulateExpenses(this.date);
        profit = revenue - expenses;
    }
    public double getRevenue() {
        return revenue;
    }
    public double getExpenses() {
        return expenses;
    }
    public double getProfit() {
        return profit;
    }
    private void accumulateRevenue(LocalDate date){
        ArrayList<Sales> revenueList = Sales.getSalesHistory();
        for(int i=0; i<revenueList.size(); i++){
            if(revenueList.get(i).getDate().isBefore(date)){
                revenue += revenueList.get(i).getTotalSalesToday();
            }
        } 
    }
    private void accumulateExpenses(LocalDate date){
        ArrayList<Expenses> expensesList = Expenses.getExpensesHistory();
        for(int i=0; i<expensesList.size(); i++){
            if(expensesList.get(i).getDate().isBefore(date)){
                expenses += expensesList.get(i).getTotal();
            }
        } 
    }
    @Override
    public String toString(){
        return "Revenue: " + this.revenue +
                "\nExpenses: " + this.expenses +
                "\nProfit: " + this.profit;
    }
    
    
}
