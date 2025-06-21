package com.mycompany.pharmacymanagementsystem;
public interface Payments {
    public abstract boolean pay(double amount);
}

class Cash implements Payments{
    private double requiredAmount;

    public Cash(double requiredAmount){
        this.requiredAmount = requiredAmount;
        System.out.println("Cash transaction created. Please finalize order with $" + this.requiredAmount);
    }

    @Override
    public boolean pay(double amount){
        
        if(amount >= this.requiredAmount){
            System.out.println("Thank You! Cash Payment Complete");
            return true;
        }
        else{
            System.out.println("Invalid Payment");
            return false;
        }
    }
}

class Card implements Payments{
    double requiredAmount;
    double cardBalance;

    public Card(double requiredAmount, double cardBalance){
        this.requiredAmount = requiredAmount;
        this.cardBalance = cardBalance;
        System.out.println("Card transaction created. Card balance is $" + this.cardBalance + ", and you need $" + this.requiredAmount + 
                            " to finalize order.");
    }
    public double getCardBalance() {
        return cardBalance;
    }
    

    @Override
    public boolean pay(double amount){
        if(this.requiredAmount>this.cardBalance){
            System.out.println("Insufficient funds. Please try another card or add balance.");
            return false;
        }
        else{
            if(amount >= this.requiredAmount){
                System.out.println("Thank You! Card Payment Complete");
                this.cardBalance -= amount;
                return true;
            }
            else{
                System.out.println("Invalid Payment.");
                return false;
            }
        }
    }
}