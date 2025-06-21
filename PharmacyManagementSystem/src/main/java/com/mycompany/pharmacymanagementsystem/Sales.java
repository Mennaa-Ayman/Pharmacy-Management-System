package com.mycompany.pharmacymanagementsystem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class Sales {
    private LocalDate date;
    private static HashMap<Medicine, Integer> medicineStocksSold = new HashMap<>();
    private static HashMap<Supplements, Integer> supplementsStocksSold = new HashMap<>();
    private static HashMap<BabyCare, Integer> babyCareStocksSold = new HashMap<>();
    private static HashMap<PersonalCare, Integer> personalCareStocksSold = new HashMap<>();
    private static HashMap<Devices, Integer> devicesStocksSold = new HashMap<>();
    private double totalSalesToday;
    private static ArrayList<Sales> salesHistory = new ArrayList<>();
    
    Sales(){}

    Sales(LocalDate date){
        this.date = date;   
        this.totalSalesToday = 0;
        salesHistory.add(this);
    }
    //--------------Getters--------------

    public LocalDate getDate() {
        return date;
    }
    public HashMap<Medicine, Integer> getMedicineStocksSold() {
        return medicineStocksSold;
    }
    public HashMap<Supplements, Integer> getSupplementsStocksSold() {
        return supplementsStocksSold;
    }
    public HashMap<BabyCare, Integer> getBabyCareStocksSold() {
        return babyCareStocksSold;
    }
    public HashMap<PersonalCare, Integer> getPersonalCareStocksSold() {
        return personalCareStocksSold;
    }
    public double getTotalSalesToday() {
        return totalSalesToday;
    }
    public static ArrayList<Sales> getSalesHistory() {
        return salesHistory;
    }
    //-----------------------------------

    /*                  addToTotalSalesToday()
     * Args:
     *      Receipt receipt: receipt of current sale
     * Function:
     *      Increments totalSalesToday by recepit's total.
     *      Called by generateReceipt()
     * Returns:
     *      None
     */
    public void addToTotalSalesToday(double total){
        this.totalSalesToday += total;
    }
    
    /*                  recordSale()
     * Args:
     *      Item item : item sold
     *      int soldAmount: amount of item sold
     * Function:
     *      Increments stocksSold data fields in Sales class
     *      Called after receipt is generated, to track items sold.
     *      Throws Exceptions. 
     * Returns:
     *      None 
     */
    public static void recordSale(Item item, int soldAmount) throws NullPointerException, IllegalArgumentException{
        Integer currentAmount = 0;
        
        if(item instanceof Medicine){
            currentAmount = medicineStocksSold.getOrDefault(item,0);
            medicineStocksSold.put((Medicine)item, currentAmount + soldAmount);
        }
        else if(item instanceof Supplements){
            currentAmount = supplementsStocksSold.getOrDefault(item, 0);
            supplementsStocksSold.put((Supplements)item, currentAmount + soldAmount);
        }
        else if(item instanceof BabyCare){
            currentAmount = babyCareStocksSold.get(item);
            babyCareStocksSold.put((BabyCare)item, currentAmount + soldAmount);
        }
        else if(item instanceof PersonalCare){
            currentAmount = personalCareStocksSold.get(item);
            personalCareStocksSold.put((PersonalCare)item, currentAmount + soldAmount);
        }
        // else if(item instanceof Devices){
        //     currentAmount = devicesStocksSold.get(item);
        //     devicesStocksSold.put((Devices)item, currentAmount + soldAmount);
        // }
        else{
            throw new IllegalArgumentException("Invalid item type");
        }

    }
    public static void main(String[] args) {
        //Testing creation of Sales and saleHistory
        LocalDate today = LocalDate.of(2025,5,13);
        LocalDate yesterday = LocalDate.of(2025,5,12);
        LocalDate tomorrow = LocalDate.of(2025,5,14);
        Sales may12 = new Sales(yesterday);
        Sales may13 = new Sales(today);
        Sales may14 = new Sales(tomorrow);

        for(int i=0; i<salesHistory.size(); i++){
            if(salesHistory.get(i).getDate().isAfter(today)){
                System.out.println("Yes it is");
            }
            else{
                System.out.println("No its not");
            }

        }
        

    }
}
